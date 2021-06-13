package com.ekiz.sixtcodingtask.scenes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.ekiz.sixtcodingtask.R
import com.ekiz.sixtcodingtask.base.BaseActivity
import com.ekiz.sixtcodingtask.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder.viewModel = viewModel
    }

}