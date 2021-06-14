package com.ekiz.sixtcodingtask.base

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, B : androidx.databinding.ViewDataBinding> :
    DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Suppress("UNCHECKED_CAST")
    protected val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
        return@lazy ViewModelProviders.of(this, viewModelFactory).get(persistentViewModelClass)
    }

    protected val binder by lazy<B>(LazyThreadSafetyMode.NONE) {
        return@lazy DataBindingUtil.setContentView(this, layoutId())
    }

    abstract fun layoutId(): Int

}