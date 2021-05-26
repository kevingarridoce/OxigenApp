package com.tit.oxigenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Patient extends AppCompatActivity {
    Button historicoBtn, infoBtn;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    TextView bienvenido_txt;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        historicoBtn = findViewById(R.id.historico_btn);
        infoBtn = findViewById(R.id.info_btn);
        bienvenido_txt = findViewById(R.id.bienvenido_txt);

        fAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = fAuth.getCurrentUser();
        fstore = FirebaseFirestore.getInstance();
        idUser = fAuth.getCurrentUser().getUid();

        Button logout = findViewById(R.id.logout_paciente_btn);

        bienvenido_txt.setText(user.getEmail());

        //Para llamar a otras clases y interfaz
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        historicoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Historico_Paciente.class));
                finish();
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Informacion_Paciente.class));
                finish();
            }
        });
    }
}