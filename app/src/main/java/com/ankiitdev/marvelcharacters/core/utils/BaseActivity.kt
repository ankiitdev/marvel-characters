package com.ankiitdev.marvelcharacters.core.utils

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.ankiitdev.marvelcharacters.core.resolvers.IntentResolver
import javax.inject.Inject

abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity() {

    private var _binding: Binding? = null
    val binding get() = _binding!!

    @Inject
    lateinit var intentResolver: IntentResolver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
    }

    abstract fun inflateLayout(inflater: LayoutInflater): Binding

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}