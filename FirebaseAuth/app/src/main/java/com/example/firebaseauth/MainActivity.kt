package com.example.firebaseauth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.style.UpdateLayout
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var authstatelistener: FirebaseAuth.AuthStateListener
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        authstatelistener=FirebaseAuth.AuthStateListener{
            updateUI()
        }
        binding.pbar.visibility = View.GONE
        auth = Firebase.auth
        binding.signin.setOnClickListener() {
            val email = binding.email.text.toString().trim()
            val pass = binding.pass.text.toString().trim()
            if (isvalidinput(email, pass)) {
                binding.pbar.visibility = View.VISIBLE
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener() {
                    if (it.isSuccessful) {
                        binding.pbar.visibility = View.GONE
                        Log.d(TAG, "SignInWithEmail:success")
                        //updateUI()
                    } else {
                        binding.pbar.visibility = View.GONE
                        Log.w(TAG, "SignInWithEmail:failure", it.exception)
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                       // updateUI()
                    }
                }
            }
        }

        binding.register.setOnClickListener() {
            val email = binding.email.text.toString().trim()
            val pass = binding.pass.text.toString().trim()
            if (isvalidinput(email, pass)) {
                binding.pbar.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener() {
                    if (it.isSuccessful) {
                        binding.pbar.visibility = View.GONE
                        Log.d(TAG, "CreateUserWithEmail:success")
                        Toast.makeText(this, "user created", Toast.LENGTH_SHORT).show()
                        //updateUI()
                    } else {
                        binding.pbar.visibility = View.GONE
                        Log.w(TAG, "CreateUserWithEmail:failure", it.exception)
                        Toast.makeText(this, "Error occurred: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                        //updateUI()
                    }
                }
            }
        }
        binding.signout.setOnClickListener(){
            val user=auth.currentUser
            if(user!=null){
                auth.signOut()
                //updateUI()
            }
            else{
                Toast.makeText(this,"user not logged in",Toast.LENGTH_SHORT).show()
            }
        }
        val googleesigninoptions : GoogleSignInOptions= GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.default_android_id))
            .build()

        googleSignInClient= GoogleSignIn.getClient(this,googleesigninoptions)
    }


    override fun onStart() {
        super.onStart()
        //updateUI()
    }

    override fun onResume() {
        super.onResume()
        auth.addAuthStateListener(authstatelistener)
    }

    override fun onPause() {
        super.onPause()
        auth.removeAuthStateListener(authstatelistener)
    }
    private fun updateUI() {
        val curruser=auth.currentUser
        if(curruser!=null){
            binding.textView.text=curruser.email
        }
        else{
            binding.textView.text="no user"
        }
    }
     private fun isvalidinput(email : String,pass : String) : Boolean {
         return if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
             Toast.makeText(this,"invalid email pattern",Toast.LENGTH_SHORT).show()
             false
         }else if (pass.length<6){
                 Toast.makeText(this,"pass length must be greater than 6",Toast.LENGTH_SHORT).show()
                 false
         }else{
             true
         }
     }
}