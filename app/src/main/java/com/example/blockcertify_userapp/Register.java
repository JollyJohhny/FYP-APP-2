package com.example.blockcertify_userapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blockcertify_userapp.Extra.LoadingDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Register extends AppCompatActivity {

    private LoadingDialog loadingDialog;
    EditText txtName,txtEmail,txtPassword,txtCnic,txtCompId,txtCnfrmPwd;



    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loadingDialog = new LoadingDialog(this, R.style.DialogLoadingTheme);


        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtCnfrmPwd = findViewById(R.id.txtCnfrmPwd);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void Register(View v){

        final String Email = txtEmail.getText().toString();
        String Password = txtPassword.getText().toString();
        boolean flag = true;


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

        if(txtPassword.getText().toString().equals(txtCnfrmPwd.getText().toString())){

        }
        else{
            flag = false;
            Toast.makeText(Register.this, "Password does not match!", Toast.LENGTH_SHORT).show();
        }

        if(flag){
            loadingDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                loadingDialog.dismiss();
                                Toast.makeText(Register.this, "User Registered!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Register.this, "Email already exists!", Toast.LENGTH_SHORT).show();

                                Log.i("failed", "createUserWithEmail:failure", task.getException());
                                loadingDialog.dismiss();

                            }

                            // ...
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    loadingDialog.dismiss();
                }
            });
        }



    }

}
