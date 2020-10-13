package com.example.voaenglish.model

data class NewsResponse(
        val Message: String,
        val Data: List<News>
)

data class News(
        val id: Int, val TieuDe: String, val TrichDan: String, val NoiDung: String,
        val NhomTinTuc: String, val TrangThai: Boolean, val NgayTao: String, val TrangThaiId: Int,
        val NhomTinTucId: Int, val LoaiTinTuc: Int, val LoaiNoiDung: Int
)