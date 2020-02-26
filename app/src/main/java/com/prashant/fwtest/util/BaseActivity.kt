package com.prashant.fwtest.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // Activity should not know about how it is injected. So generic.
        // All the dependencies will be injected automatically
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
    }
}