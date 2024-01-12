package com.mahammadjafarzade.authenticationfirebase.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceRepository @Inject constructor(@ApplicationContext val appContext: Context) {
    fun toString(resourceId : Int) : String {
        return appContext.getString(resourceId)
    }
}