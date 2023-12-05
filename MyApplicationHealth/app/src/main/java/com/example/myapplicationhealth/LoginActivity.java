package com.example.myapplicationhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
// 098 @duc12345
public class LoginActivity extends AppCompatActivity {
    EditText EditText_UserName, EditText_Password;
    Button Button_LogIn, Button_Register;

    TextView TextView_Title, TextView_Header, TextView_UserName, TextView_PassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText_UserName = findViewById(R.id.editTextUserName);
        EditText_Password = findViewById(R.id.editTextPassword);

        TextView_Title = findViewById(R.id.textViewTitle);
        TextView_Header = findViewById(R.id.textViewHeaderContent);
        TextView_UserName = findViewById(R.id.textViewUserName);
        TextView_PassWord = findViewById(R.id.textViewPassword);

        Button_LogIn = findViewById(R.id.buttonLogin);
        Button_Register = findViewById(R.id.buttonRegister);

        Button_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = EditText_UserName.getText().toString();
                String password = EditText_Password.getText().toString();
                Database database = new Database(getApplicationContext(), "healthcare", null, 1);
                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all detail", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (database.login(username, password) == 1) {
                        Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Error username or password", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        Button_Register.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Error", "onClick: Register");
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        }));
    }
}