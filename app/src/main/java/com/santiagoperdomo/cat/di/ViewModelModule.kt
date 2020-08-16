package com.santiagoperdomo.cat.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.santiagoperdomo.cat.ui.breed.BreedViewModel
import com.santiagoperdomo.cat.ui.breed_detail.BreedDetailViewModel
import com.santiagoperdomo.cat.ui.image.ImageViewModel
import com.santiagoperdomo.cat.ui.vote_image.VoteImageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel::class)
    internal abstract fun bindImageViewModel(imageViewModel: ImageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BreedViewModel::class)
    internal abstract fun bindBreedViewModel(breedViewModel: BreedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BreedDetailViewModel::class)
    internal abstract fun bindBreedetailViewModel(breedDetailViewModel: BreedDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VoteImageViewModel::class)
    internal abstract fun bindVoteImageViewModel(VoteImageViewModel: VoteImageViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}