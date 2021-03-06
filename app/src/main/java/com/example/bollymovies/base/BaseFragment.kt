package com.example.bollymovies.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.bollymovies.utils.Command

abstract class BaseFragment: Fragment() {

    abstract var command: MutableLiveData<Command>
}