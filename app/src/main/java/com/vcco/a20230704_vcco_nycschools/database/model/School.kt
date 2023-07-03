package com.vcco.a20230704_vcco_nycschools.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TSchool")
data class School(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "dbn")
    val dbn: String,
    @ColumnInfo(name = "school_name")
    val schoolName: String,
    @ColumnInfo(name = "overview_paragraph")
    val overview: String?,
    @ColumnInfo(name = "school_10th_seats")
    val seats: Int?,
    @ColumnInfo(name = "neighborhood")
    val neighborhood: String?,
    @ColumnInfo(name = "building_code")
    val buildingCode: String?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String?,
    @ColumnInfo(name = "fax_number")
    val faxNumber: String,
    @ColumnInfo(name = "school_email")
    val schoolEmail: String,
    @ColumnInfo(name = "website")
    val webSite: String?,
    @ColumnInfo(name = "subway")
    val subwayLines: String?,
    @ColumnInfo(name = "bus")
    val busLines: String?,
    @ColumnInfo(name = "total_students")
    val totalStudents: Int?,
    @ColumnInfo(name = "city")
    val city: String?,
    @ColumnInfo(name = "zip")
    val zipCode: String?,
    @ColumnInfo(name = "state_code")
    val state: String?,
    @ColumnInfo(name = "latitude")
    val latitude: Double?,
    @ColumnInfo(name = "longitude")
    val longitude: Double?
)
