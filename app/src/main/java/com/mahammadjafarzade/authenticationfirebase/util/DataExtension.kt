package com.mahammadjafarzade.authenticationfirebase.util

import java.text.SimpleDateFormat
import java.util.Date
fun Date.format(format : String = "yyyy MMM EEE") : String {
        val dateFormat = SimpleDateFormat(format)
        return dateFormat.format(this)
    }
