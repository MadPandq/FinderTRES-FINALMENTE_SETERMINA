package com.finder.stotomas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registrar extends AppCompatActivity implements View.OnClickListener {


    private EditText nombreRegis, apellidoRegis, rutRegis, contraRegis, correoRegis, direcRegis;
    private FirebaseAuth mAuth;
    private Spinner spinner_genero;
    private Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        mAuth = FirebaseAuth.getInstance();

        spinner_genero = findViewById(R.id.spinnerUno);
        String[] generos ={"Hombre", "Mujer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, generos);
        spinner_genero.setAdapter(adapter);

        nombreRegis = (EditText) findViewById(R.id.nombreRegistro);
        apellidoRegis = (EditText) findViewById(R.id.apellidoRegistro);
        rutRegis = (EditText) findViewById(R.id.rutRegistro);
        contraRegis = (EditText) findViewById(R.id.contraRegistro);
        correoRegis = (EditText) findViewById(R.id.correoRegistro);
        direcRegis = (EditText) findViewById(R.id.direccionRegistro);
        validar = (Button) findViewById(R.id.btnValidar);
        validar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnValidar:
                registrarUsuario();
                break;
        }
    }

    private void registrarUsuario() {
        String nombre = nombreRegis.getText().toString().trim();
        String apellido = apellidoRegis.getText().toString().trim();
        String direccion = direcRegis.getText().toString().trim();
        String correo = correoRegis.getText().toString().trim();
        String contraseña = contraRegis.getText().toString().trim();
        String rut = rutRegis.getText().toString().trim();

        if(nombre.isEmpty()){
            nombreRegis.setError("Es necesario ingresar un nombre");
            nombreRegis.requestFocus();
            return;
        }

        if(apellido.isEmpty()){
            apellidoRegis.setError("Es necesario ingresar un apellido");
            apellidoRegis.requestFocus();
            return;
        }

        if(direccion.isEmpty()){
            direcRegis.setError("Es necesario ingresar una dirección");
            direcRegis.requestFocus();
            return;
        }

        if(correo.isEmpty()){
            correoRegis.setError("Es necesario ingresar un correo");
            correoRegis.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            correoRegis.setError("Ingresa un correo válido");
            correoRegis.requestFocus();
            return;
        }

        if(contraseña.isEmpty()){
            contraRegis.setError("Es necesario ingresar una contraseña");
            contraRegis.requestFocus();
            return;
        }

        if (contraseña.length()<6) {
            contraRegis.setError("La contraseña debe tener 6 carácteres como mínimo");
            contraRegis.requestFocus();
            return;
        }

        if(rut.isEmpty()){
            rutRegis.setError("Es necesario ingresar un rut");
            rutRegis.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Usuario usuario = new Usuario(nombre, apellido, rut, correo, contraseña,direccion);

                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(usuario).addOnCompleteListener(task1 -> {
                                    if(task1.isSuccessful()){
                                        Toast.makeText(Registrar.this, "Usuario ha sido registrado", Toast.LENGTH_LONG).show();
                                    }

                                    else{
                                        Toast.makeText(Registrar.this, "Falló el registro", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                });
    }
}