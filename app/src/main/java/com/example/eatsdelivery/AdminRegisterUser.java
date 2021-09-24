package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.TipoDeAcceso;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

public class AdminRegisterUser extends AppCompatActivity {


    private EditText nameClient;
    private EditText usernameClient;
    private EditText numberClient;
    private EditText mailClient;
    private EditText passwordClient;
    private EditText confirmPass;
    private Spinner tipoSpinner;

    TipoDeAcceso tda;
    Usuario usuario;
    Model model = MainActivity.model;
    Button confirm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register_user);

        tipoSpinner = findViewById(R.id.spinner_tipoacceso);

        String [] tipos = {"Repartidor", "Encargado", "Gerente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, tipos);
        tipoSpinner.setAdapter(adapter);

        //Crea un tipo de acceso
        tda = new TipoDeAcceso();
        // Crea un usuario
        usuario = new Usuario();


        nameClient = (EditText)findViewById(R.id.name_reg_admin);
        usernameClient = (EditText)findViewById(R.id.username_reg_admin);
        numberClient = (EditText)findViewById(R.id.phone_reg_admin);
        mailClient = (EditText)findViewById(R.id.email_reg_admin);
        passwordClient = (EditText)findViewById(R.id.password_reg_admin);
        confirmPass = (EditText)findViewById(R.id.confpass_reg_admin);
        confirm_btn = (Button)findViewById(R.id.confirm_btn_register_admin);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Selecciona el texto de los EDIT TEXT VIEW
                String user = usernameClient.getText().toString();
                String pass = passwordClient.getText().toString();
                String passConfirmed = confirmPass.getText().toString();
                String name = nameClient.getText().toString();
                String mail = mailClient.getText().toString();
                String num = numberClient.getText().toString();
                model = new Model();

                // Verifica que todos los textview esten llenos
                if (user.equals("") && pass.equals("") && passConfirmed.equals("") && name.equals("") && mail.equals("")){
                    Toast.makeText(view.getContext(),"No has llenado los espacios",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (pass.equals(passConfirmed)) {
                        //Tipo de acceso seleccionado
                        String select = tipoSpinner.getSelectedItem().toString();
                        //Asigna el objeto TipoDeAcceso
                        switch (select){

                            case "Repartidor": tda.setTipo("2"); tda.setDescripcion("Repartidor");
                                break;

                            case "Encargado": tda.setTipo("4"); tda.setDescripcion("Encargado");
                                break;

                            case "Gerente": tda.setTipo("3"); tda.setDescripcion("Gerente");
                                break;
                        }

                        // Objeto Usuario
                        usuario.setUsuario(user);
                        usuario.setContrasenha(pass);
                        usuario.setCorreo(mail);
                        usuario.setTelefono(num);
                        usuario.setTipoAccesoID(tda.getTipo());
                        usuario.setTarjetaID(getIntent().getStringExtra("numero"));

                        int status = model.insertUsuario(view.getContext(), usuario);

                        if (status == 1){
                            Toast.makeText(view.getContext(), "Usuario agregado con éxito", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(view.getContext(), "Error al agregar usuario", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(view.getContext(),"Tus contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}