package com.example.triquitrueque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class Login : AppCompatActivity() {

    private val mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        //mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }
}
