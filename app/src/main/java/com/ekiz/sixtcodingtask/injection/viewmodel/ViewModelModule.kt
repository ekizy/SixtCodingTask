package com.ekiz.sixtcodingtask.injection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ekiz.sixtcodingtask.scenes.cardetail.CarDetailViewModel
import com.ekiz.sixtcodingtask.scenes.carsoverview.CarsOverviewViewModel
import com.ekiz.sixtcodingtask.scenes.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CarsOverviewViewModel::class)
    abstract fun bindsCarsOverviewViewModel(carsOverviewViewModel: CarsOverviewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CarDetailViewModel::class)
    abstract fun bindsCarDetailViewModel(carDetailViewModel: CarDetailViewModel): ViewModel

}