package com.example.rayaivanova.bookingappkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.rayaivanova.bookingappkotlin.model.User
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var username: EditText? = null
    private var password: EditText? = null
    private var login: Button? = null
    private var register: Button? = null
    private var valid = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        users = if (savedInstanceState != null) {
            savedInstanceState.getSerializable("users") as ArrayList<User>
        } else {
            ArrayList<User>()
        }
        users.add(User("raya", "12", "raya@abv.bg", false, 26))
        username = findViewById(R.id.username)
        password = this.findViewById(R.id.password)
        login = this.findViewById(R.id.login)
        login?.setOnClickListener(View.OnClickListener {
            valid = true
            validData()
            if (valid) {
                val user = getUser(username?.text.toString())
                val intent = Intent(this@MainActivity, HotelsActivity::class.java)
                intent.putExtra("user", user)
                this@MainActivity.startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Invalid login data", Toast.LENGTH_SHORT).show()
            }
        })
        register = findViewById(R.id.register)
        register?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivityForResult(intent, 4)
        })

    }

    override fun onStop() {
        super.onStop()
        password?.setText("")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("users", users)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        username?.error = (null)
        password?.error = (null)
        if (data == null || data.extras!!.getSerializable("user") == null) {
            username?.setText("")
            password?.setText("")
        } else {
            val user = data.extras!!.getSerializable("user") as User
            users.add(user)
            username?.setText(user.username)
            password?.setText(user.password)
        }

    }

    private fun getUser(username: String): User? {
        for (user in users) {
            if (user.username == username || user.email == username) {
                return user
            }
        }
        return null
    }


    private fun validData(): Boolean {
        val user = username?.text.toString()
        val pass = password?.text.toString()
        if (user?.length == 0) {
            valid = false
            username?.error = ("Please enter a username")
        }

        if (pass?.length == 0) {
            valid = false
            password?.error = ("Please enter a password")
        }
        if (userExists(user)) {
            val currentUser = getUser(user.toString())
            if (pass != currentUser!!.password) {
                valid = false
                password?.error = ("Invalid Password")
            }
        } else {
            valid = false
            username?.error = ("No such user")
        }
        return valid
    }
}

lateinit var users: ArrayList<User>
fun userExists(username: String): Boolean {
    for (user in users) {
        if (user.username == username || user.email == username) {
            return true
        }
    }
    return false
}