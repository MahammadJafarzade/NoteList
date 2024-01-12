package com.mahammadjafarzade.authenticationfirebase.util

import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showDatePicker(onDateSelected : (Long)->Unit ) {
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select note date")
        .build()

    datePicker.addOnPositiveButtonClickListener {
        onDateSelected(it)
    }

    datePicker.addOnCancelListener {

    }

    datePicker.addOnDismissListener {

    }

    datePicker.show(childFragmentManager, "date_picker")
}

fun Fragment.showMessage() {
    MaterialAlertDialogBuilder(requireContext())
        .setTitle("Başlık")
        .setMessage("Detay içerik mesjaı")
        .setNeutralButton("Vazgeç") { dialog, which->

        }
        .setNegativeButton("Reddet") { dialog, which->

        }
        .setPositiveButton("Kabul")  { dialog, which->

        }
        .show()
}