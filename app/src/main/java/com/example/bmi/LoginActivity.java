package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmi.db.UserManager;
import com.example.bmi.model.User;

public class LoginActivity extends AppCompatActivity {

    private Button mLogin;
    private EditText mUsername;
    private EditText mPassword;
    private TextView mRegister;
    private Context mContext;

    private UserManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mManager = new UserManager(this);

        mContext = this;

        mLogin = findViewById(R.id.login_button);
        mUsername = findViewById(R.id.username_edit_text);
        mPassword = findViewById(R.id.password_edit_text);
        mRegister = findViewById(R.id.register_button);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mUsername.getText()) && TextUtils.isEmpty(mPassword.getText())) {
                    Toast.makeText(mContext, "All fields are required", Toast.LENGTH_SHORT).show();
                }else {
                    checkLogin();

                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }

    private void checkLogin() {
        String username = mUsername.getText().toString().trim().toLowerCase();
        String password = mPassword.getText().toString().trim();
        User user = new User(username, password);

        User validateUser = mManager.checkUserLogin(user);

        if (null == validateUser) {
            Toast.makeText(mContext, "Invalid username or password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Welcome" +" "+ user.getUsername(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
        }
    }
}
