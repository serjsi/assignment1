package com.shpp.ssierykh.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast





class Sign_upActivity : AppCompatActivity() {

/*
    // Объявляем об использовании следующих объектов:
    private val username: EditText = null
    private val password: EditText? = null
    private var login: Button? = null
    private var loginLocked: TextView? = null
    private var attempts: TextView? = null
    private var numberOfAttempts: TextView? = null
*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

/*        // Связываемся с элементами нашего интерфейса:
        username = findViewById(R.id.enter_email) as EditText?
        password = findViewById(R.id.password_cower) as EditText?
        login = findViewById(R.id.sign_in) as Button?
       *//* loginLocked = findViewById(R.id.login_locked) as TextView?
        attempts = findViewById(R.id.attempts) as TextView?
        numberOfAttempts = findViewById(R.id.number_of_attempts) as TextView?
        numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts))*/


    }
/*    // Обрабатываем нажатие кнопки "Войти":
    fun Login(view: View?) {

        // Если введенные логин и пароль будут словом "admin",
        // показываем Toast сообщение об успешном входе:
        if (username.getText().toString() == "admin" && password.getText().toString() == "admin") {
            Toast.makeText(getApplicationContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show()
        }
    }*/

}