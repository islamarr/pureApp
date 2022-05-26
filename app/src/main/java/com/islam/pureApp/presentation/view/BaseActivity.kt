package com.islam.pureApp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<viewBinding : ViewBinding> : AppCompatActivity() {

    private var _binding: viewBinding? = null
    val binding: viewBinding
        get() {
            return _binding ?: throw IllegalStateException(
                "data binding should not be requested before onCreate is called"
            )
        }

    abstract val bindingInflater: (LayoutInflater) -> viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(_binding?.root)

        setupOnCreate()
    }

    abstract fun setupOnCreate()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}