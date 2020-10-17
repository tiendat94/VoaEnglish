package com.example.voaenglish.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.voaenglish.adapter.FlagsAdapter
import com.example.voaenglish.base.BaseViewModelKotlin
import kotlinx.android.synthetic.main.activity_flag.*
import java.util.*
import kotlin.collections.ArrayList

class FlagViewModel : BaseViewModelKotlin() {

    val flagLiveData = MutableLiveData<ArrayList<String>>()

    fun getListOfCountries() {
        val isoCountryCodes = Locale.getISOCountries()
        val countryListWithEmojis = ArrayList<String>()
        for (countryCode in isoCountryCodes) {
            val locale = Locale("", countryCode)
            val countryName = locale.displayCountry
            val flagOffset = 0x1F1E6
            val asciiOffset = 0x41
            val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
            val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
            val flag =
                    (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
            countryListWithEmojis.add("$countryName $flag")
            flagLiveData.value = countryListWithEmojis
        }
    }


}