package com.vcco.a20230704_vcco_nycschools.utils.database

class NetworkConstants {
    companion object {
        //NetworkCall
        const val getSATScore = "f9bf-2cp4"
        const val getSchoolInfo = "s3k6-pzi2"

        //Commons
        const val dbnResponse = "dbn"
        const val schoolNameResponse = "school_name"

        //SchoolSATScoresResponse
        const val satCriticalReadingAvgScoreResponse = "sat_critical_reading_avg_score"
        const val satMathAvgScoreResponse = "sat_math_avg_score"
        const val satWritingAvgScoreResponse = "sat_writing_avg_score"

        //SchoolResponse
        const val overviewParagraphResponse = "overview_paragraph"
        const val seatsResponse = "school_10th_seats"
        const val neighborhoodResponse = "neighborhood"
        const val buildingCodeResponse = "building_code"
        const val locationResponse = "location"
        const val phoneNumberResponse = "phone_number"
        const val faxNumberResponse = "fax_number"
        const val schoolEmailResponse = "school_email"
        const val websiteResponse = "website"
        const val subwayResponse = "subway"
        const val busResponse = "bus"
        const val totalStudentsResponse = "total_students"
        const val cityResponse = "city"
        const val zipCodeResponse = "zip"
        const val stateResponse = "state_code"
        const val latitudeResponse = "latitude"
        const val longitudeResponse = "longitude"
    }
}