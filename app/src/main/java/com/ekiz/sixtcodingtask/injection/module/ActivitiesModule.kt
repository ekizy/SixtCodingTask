package com.ekiz.sixtcodingtask.injection.module

import com.ekiz.sixtcodingtask.injection.scope.MainScope
import com.ekiz.sixtcodingtask.scenes.main.MainActivity
import com.ekiz.sixtcodingtask.scenes.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivitiesModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

}