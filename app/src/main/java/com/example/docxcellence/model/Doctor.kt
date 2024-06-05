package com.example.docxcellence.model

data class Doctor(
    val available_timings: List<AvailableTiming>,
    val clinic_address: String,
    val consultation_fee: Int,
    val contact_info: ContactInfo,
    val experience: Int,
    val id: Int,
    val image_url: String,
    val name: String,
    val qualifications: List<String>,
    val specialty: String
)