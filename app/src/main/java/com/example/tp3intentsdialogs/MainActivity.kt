package com.example.tp3intentsdialogs

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var NomPrenom: TextInputLayout
    lateinit var nom_prenom: EditText
    lateinit var DateNaissance: TextInputLayout
    lateinit var date_naissance: EditText
    lateinit var AdresseEmail: TextInputLayout
    lateinit var adresse_email: EditText
    lateinit var Classe: TextInputLayout
    lateinit var classe: EditText
    lateinit var AddUser: Button
    lateinit var items: Array<String>

    fun init() {
        NomPrenom = findViewById(R.id.NomPrenom)
        nom_prenom = findViewById(R.id.nom_prenom)
        DateNaissance = findViewById(R.id.DateNaissance)
        date_naissance = findViewById(R.id.date_naissance)
        AdresseEmail = findViewById(R.id.AdresseEmail)
        adresse_email = findViewById(R.id.adresse_email)
        Classe = findViewById(R.id.Classe)
        classe = findViewById(R.id.classe)
        AddUser = findViewById(R.id.AddUser)
        items = resources.getStringArray(R.array.classe)
        val adapter = ArrayAdapter(applicationContext, R.layout.class_item, items)
        (classe as? AutoCompleteTextView)?.setAdapter(adapter)
        this.initDatePicker()
    }

    private fun initDatePicker() {
        date_naissance.setOnClickListener {
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    date_naissance.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }
    }

    fun listenOnInput(field: EditText) {
        field.setOnFocusChangeListener { _, _ ->
            validate(field)
        }
        field.addTextChangedListener {
            validate(field)
        }
    }

    fun validate(field: EditText) {
        val fieldLayout = field.parent.parent as TextInputLayout
        var error = false;
        println(field.text)
        if (field == date_naissance) {
            try {

                val sdf = SimpleDateFormat("dd-MM-yyyy")
                val date = sdf.parse(field.text.toString())
                if (date.after(Date())) {
                    fieldLayout.error = "Date doit être inférieur à la date d'aujourd'hui"
                    error = true
                }
            } catch (e: Exception) {
                fieldLayout.error = "Date doit être au format dd-MM-yyyy"
                error = true
            }
        } else if (field == classe) {
            var found = false
            items.forEach { item ->
                if (item == field.text.toString()) {
                    found = true
                }
            }
            println(found)
            if (!found) {
                fieldLayout.error = "Classe invalide"
                error = true
            }
        } else if (field == adresse_email) {
            if (!Patterns.EMAIL_ADDRESS.matcher(field.text.toString()).matches()) {
                fieldLayout.error = "Adresse email invalide"
                error = true
            }
        } else if (field.text.isEmpty()) {
            fieldLayout.error = "Ce champ est obligatoire"
            error = true
        } else {
            fieldLayout.error = null
        }
        if (!error) {
            fieldLayout.error = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        val listOfInputs = listOf(nom_prenom, date_naissance, adresse_email, classe)
        listOfInputs.forEach { listenOnInput(it) }

    }
}