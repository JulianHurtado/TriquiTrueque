package com.example.triquitrueque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    var firebaseAuth: FirebaseAuth? = null

    lateinit var ivProfilePicture: ImageView
    lateinit var tvName: TextView
    lateinit var tvEmail: TextView
    lateinit var tvUserId: TextView

    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
        ivProfilePicture = findViewById<View>(R.id.iv_profile) as ImageView
        tvName = findViewById<View>(R.id.tv_name) as TextView
        tvEmail = findViewById<View>(R.id.tv_email) as TextView
        tvUserId = findViewById<View>(R.id.tv_id)as TextView
        val user = firebaseAuth?.currentUser
        Log.i(TAG, "User account ID ${user?.uid}")
        Log.i(TAG, "Display Name : ${user?.displayName}")
        Log.i(TAG, "Email : ${user?.email}")
        Log.i(TAG, "Photo URL : ${user?.photoUrl}")
        Log.i(TAG, "Provider ID : ${user?.providerId}")
        tvName.text = user?.displayName
        tvEmail.text = user?.email
        tvUserId.text = user?.uid

        Picasso.with(this@MainActivity)
            .load(user?.photoUrl)
            .into(ivProfilePicture)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        //return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        logOutMenuOnClicked()
        return super.onOptionsItemSelected(item)
    }

    private fun logOutMenuOnClicked() {
        mAuth?.signOut()
        firebaseAuth?.signOut()
        goToLogin()
    }

    private fun goToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {

        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
