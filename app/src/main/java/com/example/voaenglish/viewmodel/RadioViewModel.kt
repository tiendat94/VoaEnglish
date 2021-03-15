package com.example.voaenglish.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.voaenglish.base.BaseViewModelKotlin
import com.example.voaenglish.model.OffersModel

class RadioViewModel : BaseViewModelKotlin() {

    val radioLiveData = MutableLiveData<ArrayList<OffersModel>>()

    val offerList: ArrayList<OffersModel> = java.util.ArrayList()

    fun getOffers() {
        offerList?.add(OffersModel("tien dat", 2222))
        offerList?.add(OffersModel("tien dat", 2222))
        offerList?.add(OffersModel("tien dat", 2222))
        offerList?.add(OffersModel("tien dat", 2222))
        offerList?.add(OffersModel("tien dat", 2222))
        offerList?.add(OffersModel("tien dat", 2222))
        offerList?.add(OffersModel("tien dat", 2222))
        offerList?.add(OffersModel("tien dat", 2222))
        offerList?.add(OffersModel("tien dat", 2222))
        offerList?.add(OffersModel("tien dat", 2222))

        radioLiveData.value = offerList
    }
}