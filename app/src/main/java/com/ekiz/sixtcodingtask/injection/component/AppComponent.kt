package com.ekiz.sixtcodingtask.injection.component

import com.ekiz.sixtcodingtask.application.CodingTaskApplication
import com.ekiz.sixtcodingtask.injection.module.ActivitiesModule
import com.ekiz.sixtcodingtask.injection.module.AppModule
import com.ekiz.sixtcodingtask.injection.module.FragmentsModule
import com.ekiz.sixtcodingtask.injection.module.NetworkModule
import com.ekiz.sixtcodingtask.injection.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        FragmentsModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)

internal interface AppComponent : AndroidInjector<CodingTaskApplication> {

    override fun inject(application: CodingTaskApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CodingTaskApplication): Builder

        fun build(): AppComponent
    }
}