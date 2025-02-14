package com.gomez.herlin.logindemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gomez.herlin.logindemo.service.LoginService;

public class RegisterActivity extends AppCompatActivity {

    LoginService loginService;

    EditText textViewUsername, textViewPassword, textViewConfirmPassword;
    Button btnConfirm;

    TextView tv_login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginService = new LoginService(this);
        textViewUsername = (EditText)findViewById(R.id.username);
        textViewPassword = (EditText)findViewById(R.id.password);
        textViewConfirmPassword = (EditText)findViewById(R.id.confirmPassword);
        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        tv_login = findViewById(R.id.tv_login);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textViewUsername.getText().toString();
                String password = textViewPassword.getText().toString();
                String confirm_password = textViewConfirmPassword.getText().toString();

                if(username.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.errorRequiredUser, Toast.LENGTH_LONG).show();
                } else if(password.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.errorRequiredPassword, Toast.LENGTH_LONG).show();
                } else if(confirm_password.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.errorRequiredConfirmPassword, Toast.LENGTH_LONG).show();
                } else {
                    if(username.length() < 8) {
                        Toast.makeText(getApplicationContext(), R.string.errorMin8Characters, Toast.LENGTH_LONG).show();
                    }else if(password.length() < 6) {
                        Toast.makeText(getApplicationContext(), R.string.errorMin6Characters, Toast.LENGTH_LONG).show();
                    }else if(password.equals(confirm_password)){
                        Boolean verifyuser = loginService.verifyUser(username);
                        if(verifyuser == true){
                            Boolean insert = loginService.Insert(username, password);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(), R.string.txtRegisterSuccess, Toast.LENGTH_LONG).show();
                                textViewUsername.setText("");
                                textViewPassword.setText("");
                                textViewConfirmPassword.setText("");
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.txtUsernameInUse, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.txtPasswordMismatch, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
}

}