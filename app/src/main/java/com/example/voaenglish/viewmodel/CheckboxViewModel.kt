package com.example.voaenglish.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.voaenglish.base.BaseViewModelKotlin
import com.example.voaenglish.model.FilterModel

class CheckboxViewModel : BaseViewModelKotlin() {

    val checkModelLive = MutableLiveData<ArrayList<FilterModel>>()
    val filterList: ArrayList<FilterModel> = ArrayList()

    fun makeLoginRequest(username : String, token : String){

    }

    fun getListCheckbox() {
        filterList.add(FilterModel("Adidas", 123, true))
        filterList.add(FilterModel("Nike", 2321, false))
        filterList.add(FilterModel("Reebok", 222, true))
        filterList.add(FilterModel("Reebok", 222, true))
        filterList.add(FilterModel("Reebok", 222, true))
        filterList.add(FilterModel("Reebok", 222, true))
        filterList.add(FilterModel("Reebok", 222, true))
        filterList.add(FilterModel("Reebok", 222, true))
        filterList.add(FilterModel("Reebok", 222, true))
        filterList.add(FilterModel("Reebok", 222, true))
        filterList.add(FilterModel("Reebok", 222, true))
        filterList.add(FilterModel("Reebok", 222, true))
        checkModelLive.value = filterList
    }

}