package com.moemoedev.client;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.concurrent.Executor;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private String jk = "";
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //inisialisasi
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        final EditText etName = v.findViewById(R.id.et_register_name);
        final EditText etEmail = v.findViewById(R.id.et_register_email);
        final EditText etPass = v.findViewById(R.id.et_register_pass);
        final EditText etNisn = v.findViewById(R.id.et_register_nisn);
        final EditText etTtl = v.findViewById(R.id.et_register_ttl);
        final EditText etAddress = v.findViewById(R.id.et_register_address);
        final EditText etJurusan = v.findViewById(R.id.et_register_jurusan);
        final RadioButton rbMale = v.findViewById(R.id.rb_male);
        final RadioButton rbFemale = v.findViewById(R.id.rb_female);
        final Spinner spinner =  v.findViewById(R.id.spin_graduate);
        Button btnRegister = v.findViewById(R.id.btnRegister);


        //array spinner tahun kelulusan
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduate_year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //register
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        firebaseAuth = firebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etPass.getText().toString();
                final String nisn = etNisn.getText().toString();
                final String ttl = etTtl.getText().toString();
                final String address = etAddress.getText().toString();
                final String jurusan = etJurusan.getText().toString();
                final String graduate = spinner.getSelectedItem().toString();

                if (rbMale.isChecked()){
                    jk = "Laki-Laki";
                }
                if (rbFemale.isChecked()){
                    jk = "Perempuan";
                }

                //alert
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(getActivity(), "Masukkan Nama Lengkap", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getActivity(), "Masukkan E-mail", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getActivity(), "Masukkan Password", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(nisn)){
                    Toast.makeText(getActivity(), "Masukkan NISN", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(ttl)){
                    Toast.makeText(getActivity(), "Masukkan Tenggal Lahir", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(address)){
                    Toast.makeText(getActivity(), "Masukkan Alamat", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(jurusan)){
                    Toast.makeText(getActivity(), "Masukkan Jurusan", Toast.LENGTH_SHORT).show();
                }
                if (password.length()<6){
                    Toast.makeText(getActivity(), "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User information = new User(
                                            name,
                                            email,
                                            password,
                                            nisn,
                                            jk,
                                            ttl,
                                            address,
                                            jurusan,
                                            graduate
                                    );
                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getActivity(), "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getActivity(),MainActivity.class));
                                        }
                                    });
                                } else {

                                }

                                // ...
                            }
                        });

            }
        });


        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
