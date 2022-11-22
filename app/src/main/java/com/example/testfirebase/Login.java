package com.example.testfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity{
    private Button btnLogin, btnDangKy_login, btnForgotPass;
    private TextInputEditText edtEmail, edtPassword;
    private FirebaseAuth mAuth;
    private long backPressedTime;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mappingId();
        checkCurrentUser();

        //su kien click cho nut dang ky o man hinh dang nhap
        btnDangKy_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPass();
            }
        });
    }

    //anh xa id xml
    public void mappingId()
    {
        btnLogin = findViewById(R.id.btnLogin);
        btnDangKy_login = findViewById(R.id.btnDangKy_login);
        btnForgotPass = findViewById(R.id.btnForgotPass);
        edtEmail = findViewById(R.id.edtEmail_login);
        edtPassword = findViewById(R.id.edtPass_login);
        mAuth = FirebaseAuth.getInstance();
    }

    //back 2 lan de dong ung dung
    public void onBackPressed()
    {
        if(backPressedTime + 2000 > System.currentTimeMillis())
        {
            mToast.cancel();
            finish();
        }
        else
        {
            mToast = Toast.makeText(Login.this, "Back 1 lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    //luu thong tin dang nhap nguoi dung
    public void checkCurrentUser()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            finish();
            startActivity(new Intent(Login.this, Home.class));
        }
    }


    //chuc nang dang nhap
    public void userLogin()
    {
        String email = edtEmail.getText().toString().trim();
        String passWord = edtPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            edtEmail.setError("Yêu cầu nhập email!");
            edtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            edtEmail.setError("Yêu cầu nhập email hợp lệ!");
            edtEmail.requestFocus();
        }

        if(passWord.isEmpty())
        {
            edtPassword.setError("Yêu cầu nhập mật khẩu!");
            edtPassword.requestFocus();
            return;
        }

        if(passWord.length() < 6)
        {
            edtPassword.setError("Yêu cầu mật khẩu lớn hơn 5 ký tự!");
            edtPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.sendEmailVerification();
                    if(user.isEmailVerified())
                    {
                        finish();
                        startActivity(new Intent(Login.this, Home.class));
                    }
                    else
                        Toast.makeText(Login.this, "Vui lòng kiểm tra và xác nhận email trước khi đăng nhập!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this, "Email hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //chuc nang quen mat khau
    public void forgotPass()
    {
        String email = edtEmail.getText().toString().trim();
        mAuth = FirebaseAuth.getInstance();

        if(email.isEmpty())
        {
            edtEmail.setError("Yêu cầu nhập email!");
            edtEmail.requestFocus();
            return;
        }
        else
        {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                        Toast.makeText(Login.this, "Vui lòng kiểm tra email để đổi mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}