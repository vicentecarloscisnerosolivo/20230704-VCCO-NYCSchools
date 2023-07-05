package com.vcco.a20230704_vcco_nycschools.utils.permission

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService


class PermissionsUtils {
    companion object {
        /**
         * Check if given permission is granted
         * @param context
         * @param name
         * @return Boolean
         */
        fun isPermissionGranted(context: Context, name: String) =
            ContextCompat.checkSelfPermission(
                context,
                name
            ) == PackageManager.PERMISSION_GRANTED

        /**
         * Check if gps is enabled
         */
        fun isGPSEnabled(context: Context): Boolean {
            val locationManager =
                getSystemService(context, LocationManager::class.java) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }
    }
}