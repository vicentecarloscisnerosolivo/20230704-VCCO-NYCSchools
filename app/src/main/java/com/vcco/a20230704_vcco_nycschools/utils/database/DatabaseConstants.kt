package com.vcco.a20230704_vcco_nycschools.utils.database

class DatabaseConstants {
    companion object {
        //COMMONS
        const val dbName = "school_database"
        const val dbnColumn = "dbn"
        const val schoolNameColumn = "school_name"
        const val NA = "No Applicable"
        const val NI = "No Info"

        //Table SchoolSATScores
        const val schoolSAtScoresTable = "TSchoolSAtScores"
        const val numOfSatTestTakersColumn = "num_of_sat_test_takers"
        const val satCriticalReadingAvgScoreColumn = "sat_critical_reading_avg_score"
        const val satMathAvgScoreColumn = "sat_math_avg_score"
        const val satWritingAvgScoreColumn = "sat_writing_avg_score"

        //Table School
        const val schoolTable = "TSchool"
        const val overviewParagraphColumn = "overview_paragraph"
        const val seatsColumn = "school_10th_seats"
        const val neighborhoodColumn = "neighborhood"
        const val buildingCodeColumn = "building_code"
        const val locationColumn = "location"
        const val phoneNumberColumn = "phone_number"
        const val faxNumberColumn = "fax_number"
        const val schoolEmailColumn = "school_email"
        const val websiteColumn = "website"
        const val subwayColumn = "subway"
        const val busColumn = "bus"
        const val totalStudentsColumn = "total_students"
        const val cityColumn = "city"
        const val zipCodeColumn = "zip"
        const val stateColumn = "state_code"
        const val latitudeColumn = "latitude"
        const val longitudeColumn = "longitude"
    }
}