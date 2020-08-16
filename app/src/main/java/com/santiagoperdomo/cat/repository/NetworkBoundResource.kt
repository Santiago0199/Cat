package com.santiagoperdomo.cat.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.santiagoperdomo.cat.AppExecutors
import com.santiagoperdomo.cat.api.ApiResponse
import java.util.*

abstract class NetworkBoundResource<ResultType: Any, RequestType: Any> @MainThread constructor(private var appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (!Objects.equals(result.value, newValue)) {
            result.value = newValue
        }
    }

    fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            if (response.isSuccessful()) {
                appExecutors.diskIO.execute {
                    saveCallResult(processResponse(response))
                    appExecutors.mainThread.execute {
                        result.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
            } else {
                onFetchFailed()
                result.addSource(dbSource) { newData ->
                    setValue(Resource.error(response.errorMessage!!, newData))
                }
            }
        }
    }

    @MainThread
    abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    @WorkerThread
    abstract fun saveCallResult(item: RequestType)

    @WorkerThread
    protected open fun processResponse(response: ApiResponse<RequestType>): RequestType {
        return response.body!!
    }

    protected open fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }
}