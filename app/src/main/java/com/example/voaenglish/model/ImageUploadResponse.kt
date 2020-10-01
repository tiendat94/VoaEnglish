package com.example.voaenglish.model

data class ImageUploadResponse(private val result: Boolean?, private val code: Int?, private val message: String?, private var baseUrl: String?, private var data: ArrayList<String>)