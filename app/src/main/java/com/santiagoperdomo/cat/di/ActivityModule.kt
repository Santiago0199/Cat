package com.santiagoperdomo.cat.di

import com.santiagoperdomo.cat.ui.DashboardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeDashboardActivity(): DashboardActivity?

}