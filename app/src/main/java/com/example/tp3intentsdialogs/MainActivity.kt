package com.example.tp3intentsdialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

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
    }

    fun listenOnInput(field: EditText) {
        field.setOnFocusChangeListener { _,_->
            validate(field)
        }
        field.addTextChangedListener {
            validate(field)
        }
    }

    fun validate(field: EditText) {
        val fieldLayout = field.parent.parent as TextInputLayout
        println(field.text)
        if (field.text.isBlank()) {
            fieldLayout.error = "Ce champ est obligatoire"
        } else {
            fieldLayout.error = null
        }
        if (field == adresse_email) {
            if (!Patterns.EMAIL_ADDRESS.matcher(field.text.toString()).matches()) {
                fieldLayout.error = "Adresse email invalide"
            } else {
                fieldLayout.error = null
            }
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