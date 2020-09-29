package com.example.voaenglish.utils

import android.provider.MediaStore

data class SongInfo(var songURL: String?, var songAuth: String?, var songName: String?)
data class DirInfo(var dir: String?, var songInfo: ArrayList<SongInfo>?)

class SongMusic {
    companion object {

    }
}