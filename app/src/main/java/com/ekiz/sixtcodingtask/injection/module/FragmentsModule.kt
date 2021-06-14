package com.ekiz.sixtcodingtask.injection.module

import com.ekiz.sixtcodingtask.injection.scope.CarDetailScope
import com.ekiz.sixtcodingtask.injection.scope.CarsOverviewScope
import com.ekiz.sixtcodingtask.scenes.cardetail.CarDetailFragment
import com.ekiz.sixtcodingtask.scenes.cardetail.CarDetailModule
import com.ekiz.sixtcodingtask.scenes.carsoverview.CarsOverviewFragment
import com.ekiz.sixtcodingtask.scenes.carsoverview.CarsOverviewModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentsModule {

    @CarsOverviewScope
    @ContributesAndroidInjector(modules = [CarsOverviewModule::class])
    internal abstract fun contributesCarsOverviewFragment(): CarsOverviewFragment

    @CarDetailScope
    @ContributesAndroidInjector(modules = [CarDetailModule::class])
    internal abstract fun contributesCarDetailFragment(): CarDetailFragment

}