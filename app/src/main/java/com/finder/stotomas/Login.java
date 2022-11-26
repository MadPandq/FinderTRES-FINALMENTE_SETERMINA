package com.finder.stotomas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView textreg;
    Button botonLogin;

    private EditText contraseña, email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textreg = (TextView) findViewById(R.id.tv_register);
        textreg.setOnClickListener(this);

        botonLogin = findViewById(R.id.btnlogin);
        botonLogin.setOnClickListener((View.OnClickListener) this);

        email = (EditText) findViewById(R.id.email);
        contraseña = (EditText) findViewById(R.id.contraseña);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(this, Registrar.class));
                break;

            case R.id.btnlogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String emmail = email.getText().toString().trim();
        String contra = contraseña.getText().toString().trim();

        if (emmail.isEmpty()) {
            email.setError("Es necesario ingresar un email");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emmail).matches()){
            email.setError("Ingresa un email por favor");
            email.requestFocus();
            return;
        }

        if (contra.isEmpty()) {
            contraseña.setError("Es necesario ingresar una contraseña");
            contraseña.requestFocus();
            return;
        }

        if (contra.length()<6) {
            contraseña.setError("La contraseña debe tener 6 carácteres como mínimo");
            contraseña.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(emmail, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    startActivity(new Intent(Login.this, MainActivity.class));
                }
                else{
                    Toast.makeText(Login.this, "Falló el login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}