package ru.israpil.cbrcurrency.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T: Any> MutableLiveData<T>.asLiveData() : LiveData<T> = this
