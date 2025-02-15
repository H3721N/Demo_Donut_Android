package com.gomez.herlin.logindemo;

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

public class MainActivity extends AppCompatActivity {
    TextView tv_register;
    Button btnLogin;
    EditText textViewUsername, textViewPassword;

    LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginService = new LoginService(this);
        textViewUsername = (EditText)findViewById(R.id.username);
        textViewPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tv_register = findViewById(R.id.tv_register);

        /*textViewUsername.setText("hgomez123");
        textViewPassword.setText("hgomez123");*/

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intentReg);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textViewUsername.getText().toString();
                String password = textViewPassword.getText().toString();

                if(username.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.errorRequiredUser, Toast.LENGTH_LONG).show();
                } else if(password.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.errorRequiredPassword, Toast.LENGTH_LONG).show();
                } else if (username.length() < 8) {
                    Toast.makeText(getApplicationContext(), R.string.errorMin8Characters, Toast.LENGTH_LONG).show();
                } else if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), R.string.errorMin6Characters, Toast.LENGTH_LONG).show();
                } else {
                    Boolean checklogin = loginService.verifyLogin(username, password);
                    if (checklogin) {
                        Toast.makeText(getApplicationContext(), R.string.txtViewWelcome, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.errorInvalidUser, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public static class ListElement {
    }
}


