package com.iegabrielamistral.mentalcare.usuario

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
// esta clase es para la función del calendario

// Clase personalizada 'DatePickerFragment' que extiende 'DialogFragment' y implementa 'OnDateSetListener' para manejar la selección de fecha
class DatePickerFragment(val listener: (day: Int, month: Int, year: Int) -> Unit) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    // Este método se llama cuando el usuario selecciona una fecha en el DatePicker
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Llama al listener pasado al fragmento, pasando la fecha seleccionada (día, mes, año)
        listener(dayOfMonth, month, year)
    }


    // Método sobrecargado para crear el diálogo de selección de fecha (DatePickerDialog)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Obtiene la instancia actual del calendario
        val calendar: Calendar = Calendar.getInstance()

        // Obtiene el día, mes y año actuales desde el calendario
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)   // Día del mes
        val month: Int =
            calendar.get(Calendar.MONTH)        // Mes (de 0 a 11, por eso no se suma 1)
        val year: Int = calendar.get(Calendar.YEAR)          // Año actual

        // Crea una instancia de DatePickerDialog, pasándole la actividad (contexto), el listener (this),
        // y la fecha actual (año, mes, día)
        val picker = DatePickerDialog(activity as Context, this, year, month, day)

        // Retorna el DatePickerDialog para ser mostrado
        return picker
    }
}

