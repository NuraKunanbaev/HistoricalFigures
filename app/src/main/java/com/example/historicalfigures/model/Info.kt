package com.example.historicalfigures.model

data class Info(
    val born: String,
    val died: String,
    val years: String,
    val awards: String,
    val office: List<String>,
    val parents: List<String>,
    val children: List<String>,
    val partners: String,
    val conflicts: List<String>,
    val occupation: List<String>,
    val notable_work: List<String>,
    val resting_place: String,
    val cause_of_death: String
)