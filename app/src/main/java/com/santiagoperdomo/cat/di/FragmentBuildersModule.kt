package com.santiagoperdomo.cat.di

import com.santiagoperdomo.cat.ui.breed.BreedFragment
import com.santiagoperdomo.cat.ui.breed_detail.BreedDetailFragment
import com.santiagoperdomo.cat.ui.image.ImageFragment
import com.santiagoperdomo.cat.ui.vote_image.VoteImageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeBreedFragment(): BreedFragment?

    @ContributesAndroidInjector
    abstract fun contributeBreedDetailFragment(): BreedDetailFragment?

    @ContributesAndroidInjector
    abstract fun contributeImageFragment(): ImageFragment?

    @ContributesAndroidInjector
    abstract fun contributeVoteImageFragment(): VoteImageFragment?

}