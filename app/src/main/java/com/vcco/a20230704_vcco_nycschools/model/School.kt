package com.vcco.a20230704_vcco_nycschools.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vcco.a20230704_vcco_nycschools.utils.database.DatabaseConstants
import com.vcco.a20230704_vcco_nycschools.utils.database.NetworkConstants
import java.lang.annotation.ElementType
/**
 * Create School Model, using same object for database and Network Call
 */
@Entity(tableName = DatabaseConstants.schoolTable)
data class School(
    @SerializedName(NetworkConstants.dbnResponse)
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = DatabaseConstants.dbnColumn)
    val dbn: String,
    @SerializedName(NetworkConstants.schoolNameResponse)
    @ColumnInfo(name = DatabaseConstants.schoolNameColumn)
    val schoolName: String,
    @SerializedName(NetworkConstants.overviewParagraphResponse)
    @ColumnInfo(name = DatabaseConstants.overviewParagraphColumn)
    val overview: String?,
    @SerializedName(NetworkConstants.seatsResponse)
    @ColumnInfo(name = DatabaseConstants.seatsColumn)
    val seats: Int?,
    @SerializedName(NetworkConstants.neighborhoodResponse)
    @ColumnInfo(name = DatabaseConstants.neighborhoodColumn)
    val neighborhood: String?,
    @SerializedName(NetworkConstants.buildingCodeResponse)
    @ColumnInfo(name = DatabaseConstants.buildingCodeColumn)
    val buildingCode: String?,
    @SerializedName(NetworkConstants.locationResponse)
    @ColumnInfo(name = DatabaseConstants.locationColumn)
    val location: String?,
    @SerializedName(NetworkConstants.phoneNumberResponse)
    @ColumnInfo(name = DatabaseConstants.phoneNumberColumn)
    val phoneNumber: String?,
    @SerializedName(NetworkConstants.faxNumberResponse)
    @ColumnInfo(name = DatabaseConstants.faxNumberColumn)
    val faxNumber: String?,
    @SerializedName(NetworkConstants.schoolEmailResponse)
    @ColumnInfo(name = DatabaseConstants.schoolEmailColumn)
    val schoolEmail: String?,
    @SerializedName(NetworkConstants.websiteResponse)
    @ColumnInfo(name = DatabaseConstants.websiteColumn)
    val webSite: String?,
    @SerializedName(NetworkConstants.subwayResponse)
    @ColumnInfo(name = DatabaseConstants.subwayColumn)
    val subwayLines: String?,
    @SerializedName(NetworkConstants.busResponse)
    @ColumnInfo(name = DatabaseConstants.busColumn)
    val busLines: String?,
    @SerializedName(NetworkConstants.totalStudentsResponse)
    @ColumnInfo(name = DatabaseConstants.totalStudentsColumn)
    val totalStudents: Int?,
    @SerializedName(NetworkConstants.cityResponse)
    @ColumnInfo(name = DatabaseConstants.cityColumn)
    val city: String?,
    @SerializedName(NetworkConstants.zipCodeResponse)
    @ColumnInfo(name = DatabaseConstants.zipCodeColumn)
    val zipCode: String?,
    @SerializedName(NetworkConstants.stateResponse)
    @ColumnInfo(name = DatabaseConstants.stateColumn)
    val state: String?,
    @SerializedName(NetworkConstants.latitudeResponse)
    @ColumnInfo(name = DatabaseConstants.latitudeColumn)
    val latitude: Double?,
    @SerializedName(NetworkConstants.longitudeResponse)
    @ColumnInfo(name = DatabaseConstants.longitudeColumn)
    val longitude: Double?
)
