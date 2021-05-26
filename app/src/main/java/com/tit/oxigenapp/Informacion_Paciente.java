package com.tit.oxigenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Informacion_Paciente extends AppCompatActivity {
    TextView nombre, email, direccion, telefono, pin;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    private String idUser;
    Button regresarBtn;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_paciente);

        nombre = findViewById(R.id.info_nombre_txt);
        email = findViewById(R.id.info_correo_txt);
        direccion = findViewById(R.id.info_dir_txt);
        telefono = findViewById(R.id.info_telefono_txt);
        pin = findViewById(R.id.info_pin_txt);
        regresarBtn = findViewById(R.id.info_regresar_button);

        fAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = fAuth.getCurrentUser();
        fstore = FirebaseFirestore.getInstance();
        idUser = fAuth.getCurrentUser().getUid();

        //Mostrar Informacion
        email.setText(user.getEmail());
        pin.setText(user.getUid());

        //Acceder a la documentacion
        DocumentReference documentReference = fstore.collection("Usuarios").document(idUser);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                nombre.setText(documentSnapshot.getString("Nombre Completo"));
                direccion.setText(documentSnapshot.getString("Direccion"));
                telefono.setText(documentSnapshot.getString("Telefono"));
            }
        });

        regresarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Patient.class));
            }
        });


    }
}
