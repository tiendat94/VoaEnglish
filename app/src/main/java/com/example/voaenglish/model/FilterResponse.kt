package com.example.voaenglish.model

data class FilterResponse(
        val data: List<FilterSchedule>
)

data class FilterSchedule(
        val id: String,
        val name: String,
        val description: String
)