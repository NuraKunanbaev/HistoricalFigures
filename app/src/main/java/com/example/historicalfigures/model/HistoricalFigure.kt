package com.example.historicalfigures.model

data class HistoricalFigure(
    val name: String,
    val title: String,
    val info: Info?,
    val occupation: List<String>,
    val notable_work: List<String>,
    val resting_place: String,
    val cause_of_death: String
)
