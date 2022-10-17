package com.example.tp3intentsdialogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class share : AppCompatActivity() {
    lateinit var text: TextView
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        val nom_prenom = intent.getStringExtra("nom_prenom")
        val date_naissance = intent.getStringExtra("date_naissance")
        val adresse_email = intent.getStringExtra("adresse_email")
        val classe = intent.getStringExtra("classe")

        text = findViewById(R.id.text)
        button = findViewById(R.id.button)

        text.text = """
            Nom et prénom : $nom_prenom 
            Date de naissance : $date_naissance 
            Adresse email : $adresse_email 
            Classe : $classe            
        """.trimIndent()

        button.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, text.text)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, null))
        }


    }
}