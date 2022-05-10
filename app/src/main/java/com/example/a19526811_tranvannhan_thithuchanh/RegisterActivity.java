package com.example.a19526811_tranvannhan_thithuchanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        EditText edtEmail = findViewById(R.id.edt_email);
        EditText edtPassword = findViewById(R.id.edt_password);
        EditText edtRepassword = findViewById(R.id.edt_repassword);
        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            String repassword = edtRepassword.getText().toString();

            if(email.equals("") || password.equals("") || repassword.equals("")){
                Toast.makeText(this, "Thông tin không được bỏ trống", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!password.equals(repassword)){
                Toast.makeText(this, "Mật khẩu không trùng", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if(task.isSuccessful()){
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Log.d("register", "success");
                }else{
                    Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    Log.d("register", "fail");
                }
            });
        });



        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}