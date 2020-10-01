package com.example.voaenglish.ui.login.slider

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.voaenglish.model.SliderItem

class SliderImageViewModel : ViewModel() {

    val sliderImageLive: MutableLiveData<List<SliderItem>>? = null

    fun getImageList(): MutableLiveData<List<SliderItem>>? {
        return sliderImageLive
    }

    fun setImageList(imageList: List<SliderItem>) {
        sliderImageLive?.value = imageList
    }

    override fun onCleared() {
        super.onCleared()
        sliderImageLive?.value = null
    }
}