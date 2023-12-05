package com.example.myapplicationhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText EditText_Username, EditText_Email, EditText_Password, EditText_ConfirmPassword;
    Button Button_Register;
    TextView TextView_Title, TextView_HeaderContent, TextView_Footer;
    TextView TextView_Username, TextView_Email, TextView_Password, TextView_ConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText_Username = findViewById(R.id.editTextUserName);
        EditText_Email = findViewById(R.id.editTextTextEmailAddress);
        EditText_Password = findViewById(R.id.editTextPassword);
        EditText_ConfirmPassword = findViewById(R.id.editTextPasswordConfirm);

        Button_Register = findViewById(R.id.buttonRegister);

        TextView_Footer = findViewById(R.id.textViewFooter);
        TextView_Email = findViewById(R.id.textViewEmail);
        TextView_Username = findViewById(R.id.textViewUserName);
        TextView_Title = findViewById(R.id.textViewTitle);
        TextView_Password = findViewById(R.id.textViewPassword);
        TextView_ConfirmPassword = findViewById(R.id.editTextPasswordConfirm);

        TextView_Footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });



        Button_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = EditText_Username.getText().toString();
                String email = EditText_Email.getText().toString();
                String password = EditText_Password.getText().toString();
                String confirmPassword = EditText_ConfirmPassword.getText().toString();

                Database database = new Database(getApplicationContext(), "healthcare", null, 1);
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirmPassword) == 0) {
                        // so sanh password and confirm password
                        if (isValidPassword(password)) {
                            database.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Create new account", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit, and special character", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "password and confirm not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValidPassword(String password) {
        boolean haveDigit = false;
        boolean haveLetter = false;
        boolean haveSpecialCharacter = false;

        if (password.length() < 8) {
            return false;
        }
        else {
            for (int i = 0; i < password.length(); i ++) {
                if (Character.isDigit(password.charAt(i))) {
                    haveDigit = true;
                }

                if(Character.isLetter(password.charAt(i))) {
                    haveLetter = true;
                }

                if (password.charAt(i) >= 33 && password.charAt(i) <= 46 || password.charAt(i) == 64) {
                    haveSpecialCharacter = true;
                }
            }
        }
        return haveDigit && haveLetter && haveSpecialCharacter;
    }

}