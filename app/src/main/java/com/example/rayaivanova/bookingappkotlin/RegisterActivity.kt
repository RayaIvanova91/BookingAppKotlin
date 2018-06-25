package com.example.rayaivanova.bookingappkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.rayaivanova.bookingappkotlin.model.User
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private var name: EditText? = null
    private var password: EditText? = null
    private var password2: EditText? = null
    private var email: EditText? = null
    private var isMale: ToggleButton? = null
    private var age: EditText? = null
    private var register: Button? = null

    private var validUser = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        name=reg_name
        password = reg_password
        password2 =reg_confirm
        email = reg_email
        isMale = gender
        age = reg_age

        register = reg_register

        register!!.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            var user: User? = null
            validUser = true
            if (validData()) {
                val male=(isMale!!.text.toString() == isMale!!.textOff)
                user = User(name!!.text.toString(), password!!.text.toString(), email!!.text.toString(), male, Integer.parseInt(age!!.text.toString()))
            }
            if (userExists(name!!.text.toString())) {
                name!!.error = "Username already taken"
                validUser = false
            }
            if (userExists(email!!.text.toString())) {
                email!!.error = "Email already taken"
                validUser = false
            }
            if (user != null && validUser) {
                intent.putExtra("user", user)
                setResult(3, intent)
                finish()
            }
        }
    }

    private fun validData(): Boolean {
        if (name!!.text.toString().isEmpty()) {
            validUser = false
            name!!.error = "Invalid name"
        }
        if (password!!.text.toString().isEmpty()) {
            validUser = false
            password!!.error = "Invalid password"
        }
        if (password2!!.text.toString().isEmpty()) {
            validUser = false
            password2!!.error = "Please confirm the password"
        }
        if (password2!!.text.toString() != password!!.text.toString()) {
            validUser = false
            password2!!.error = "Password mismatch"
        }
        if (email!!.text.toString().isEmpty()) {
            validUser = false
            email!!.error = "Invalid email"
        }
        if (age!!.text.toString().isEmpty() || Integer.parseInt(age!!.text.toString()) <= 0) {
            validUser = false
            age!!.error = "Invalid age"
        }
        return validUser
    }
}
