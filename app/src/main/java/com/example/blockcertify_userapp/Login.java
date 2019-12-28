package com.example.blockcertify_userapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blockcertify_userapp.Extra.LoadingDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private LoadingDialog loadingDialog;


    private Button btnLogin;

    EditText txtEmail,txtPassword;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadingDialog = new LoadingDialog(this, R.style.DialogLoadingTheme);

        btnLogin = findViewById(R.id.btnLoginManu);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void Login(View v){
        final String Email = txtEmail.getText().toString();
        String Password = txtPassword.getText().toString();
        Boolean flag = true;

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this, "Please Enter Email!",
                    Toast.LENGTH_LONG).show();
            flag = false;
        }
        if(TextUtils.isEmpty(Password)){
            Toast.makeText(this, "Please Enter Password!",
                    Toast.LENGTH_LONG).show();
            flag = false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            Toast.makeText(this, "Please Enter Valid Email!",
                    Toast.LENGTH_LONG).show();
            flag = false;
        }


        if(flag == true){
            loadingDialog.show();
            firebaseAuth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                loadingDialog.dismiss();
                                Toast.makeText(Login.this, "Welcome User!",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Login.this, Profile.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                loadingDialog.dismiss();
                                Toast.makeText(Login.this, "Invalid Login!",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }
    }

}
