package com.example.testfirebase;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testfirebase.Object.UserAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private Button btnBacktoLogin, btnDangKy, btnCancleEmailsend, btnAgreeEmailsend;
    private TextInputEditText edtEmail, edtPassWord, edtName, edtAge;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private CheckBox cbConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        mappingId();
        progressBar.setVisibility(View.INVISIBLE);

        //tro ve man hinh dang nhap
        btnBacktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        //su kien cho nut Dang Ky
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogConfirmEmail(Gravity.CENTER);
            }
        });

    }

    public void mappingId()
    {
        btnBacktoLogin = findViewById(R.id.btnBacktoLogin);
        btnDangKy = findViewById(R.id.btnDangKy_dk);
        edtEmail = findViewById(R.id.edtEmail_dk);
        edtPassWord = findViewById(R.id.edtPass_dk);
        edtName = findViewById(R.id.edtName_dk);
        edtAge = findViewById(R.id.edtAge_dk);
        progressBar = findViewById(R.id.progressBar_dk);
        cbConfirm = findViewById(R.id.cbConfirm);
        mAuth = FirebaseAuth.getInstance();
    }

    public void checkNullInput(String email, String passWord, String name, String age)
    {
        email = edtEmail.getText().toString().trim();
        passWord = edtPassWord.getText().toString().trim();
        name = edtName.getText().toString().trim();
        age = edtAge.getText().toString().trim();
        if(name.isEmpty())
        {
            edtName.setError("Yêu cầu nhập tên!");
            edtName.requestFocus();
            return;
        }

        if(age.isEmpty())
        {
            edtAge.setError("Yêu cầu nhập tuổi!");
            edtAge.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            edtEmail.setError("Yêu cầu nhập email!");
            edtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            edtEmail.setError("Yêu cầu nhập chính xác email của bạn!");
            edtEmail.requestFocus();
        }

        if(passWord.isEmpty())
        {
            edtPassWord.setError("Yêu cầu nhập mật khẩu!");
            edtPassWord.requestFocus();
            return;
        }

        if(passWord.length() < 6)
        {
            edtPassWord.setError("Yêu cầu mật khẩu lớn hơn 5 ký tự!");
            edtPassWord.requestFocus();
        }
    }

    public void registerUser()
    {
        String email = edtEmail.getText().toString().trim();
        String passWord = edtPassWord.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String age = edtAge.getText().toString().trim();
        checkNullInput(email, passWord, name, age);

        if(name.isEmpty())
        {
            edtName.setError("Yêu cầu nhập tên!");
            edtName.requestFocus();
            return;
        }

        if(age.isEmpty())
        {
            edtAge.setError("Yêu cầu nhập tuổi!");
            edtAge.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            edtEmail.setError("Yêu cầu nhập email!");
            edtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            edtEmail.setError("Yêu cầu nhập chính xác email của bạn!");
            edtEmail.requestFocus();
        }

        if(passWord.isEmpty())
        {
            edtPassWord.setError("Yêu cầu nhập mật khẩu!");
            edtPassWord.requestFocus();
            return;
        }

        if(passWord.length() < 6)
        {
            edtPassWord.setError("Yêu cầu mật khẩu lớn hơn 5 ký tự!");
            edtPassWord.requestFocus();
            return;
        }

        if(cbConfirm.isChecked()==false)
            Toast.makeText(Register.this, "Vui lòng xác nhận thông tin!", Toast.LENGTH_SHORT).show();
        else
        {
            mAuth.createUserWithEmailAndPassword(email, passWord)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressBar.setVisibility(View.VISIBLE);
                                mAuth.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    UserAccount user = new UserAccount(email, name, age);
                                                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(Register.this, "Đăng ký thành công!",
                                                                    Toast.LENGTH_LONG).show();
                                                            progressBar.setVisibility(View.GONE);
                                                        }
                                                    });
                                                }
                                                else
                                                {
                                                    Toast.makeText(Register.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                            }
                            else
                            {
                                Toast.makeText(Register.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
    }


    public void openDialogConfirmEmail(int gravity)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirm_email_send);

        Window window = dialog.getWindow();
        if (window == null)
        {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity)
        {
            dialog.setCancelable(true);
        }

        btnCancleEmailsend = dialog.findViewById(R.id.btn_Cancle_emailSend);
        btnAgreeEmailsend = dialog.findViewById(R.id.btn_agree_emailSend);

        btnCancleEmailsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnAgreeEmailsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}