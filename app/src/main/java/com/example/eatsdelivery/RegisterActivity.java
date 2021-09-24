package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.DireccionXCliente;
import com.example.eatsdelivery.SQLite.Tables.Tarjeta;
import com.example.eatsdelivery.SQLite.Tables.TipoDeAcceso;
import com.example.eatsdelivery.SQLite.Tables.Usuario;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameClient;
    private EditText usernameClient;
    private EditText numberClient;
    private EditText mailClient;
    private EditText passwordClient;
    private EditText confirmPass;
    private EditText directionClient;
    private  EditText nameDir;
    TipoDeAcceso tda;
    Direccion direccion;
    DireccionXCliente direccionXCliente;
    Usuario usuario;
    Tarjeta tarjeta = null;
    Model model = MainActivity.model;
    Button addPayment_tbn;
    Button confirm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Crea un tipo de acceso
        tda = new TipoDeAcceso();
        tda.setDescripcion("Cliente");
        tda.setTipo("1");
        // Crea un objeto de direccion
        direccion = new Direccion();
        // Crea un direccion x cliente
        direccionXCliente = new DireccionXCliente();
        // Crea un usuario
        usuario = new Usuario();


        nameClient = (EditText)findViewById(R.id.name_reg);
        usernameClient = (EditText)findViewById(R.id.username_reg);
        numberClient = (EditText)findViewById(R.id.phone_reg);
        mailClient = (EditText)findViewById(R.id.email_reg);
        passwordClient = (EditText)findViewById(R.id.password_reg);
        confirmPass = (EditText)findViewById(R.id.confpass_reg);
        directionClient = (EditText)findViewById(R.id.direction_reg);
        nameDir = (EditText)findViewById(R.id.dir_name);
        confirm_btn = (Button)findViewById(R.id.confirm_btn_register);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Selecciona el texto de los EDIT TEXT VIEW
                String user = usernameClient.getText().toString();
                String pass = passwordClient.getText().toString();
                String passConfirmed = confirmPass.getText().toString();
                String name = nameClient.getText().toString();
                String mail = mailClient.getText().toString();
                String dir = directionClient.getText().toString();
                String nombreDir = nameDir.getText().toString();
                String num = numberClient.getText().toString();
                model = new Model();

                // Verifica que todos los textview esten llenos
                if (user.equals("") && pass.equals("") && passConfirmed.equals("") && name.equals("") && mail.equals("") && dir.equals("")){
                    Toast.makeText(view.getContext(),"No has llenado los espacios",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (pass.equals(passConfirmed)) {

                            String numero = getIntent().getStringExtra("numero");
                            Cursor cur = model.selectTarjetaNum(getApplicationContext(), numero);
                            cur.moveToFirst();
                            int index = cur.getColumnIndexOrThrow("id");

                            // Objeto direccion
                            direccion.setNombre(nombreDir);
                            direccion.setDescripcion(dir);
                            // Objeto Usuario
                            usuario.setUsuario(user);
                            usuario.setTarjetaID(cur.getString(index));
                            usuario.setContrasenha(pass);
                            usuario.setCorreo(mail);
                            usuario.setTelefono(num);
                            usuario.setNombre(name);
                            usuario.setTipoAccesoID(tda.getTipo());

                            int status = model.insertUsuario(view.getContext(), usuario);

                            if (status == 1){
                                Toast.makeText(view.getContext(), "Usuario agregado con éxito", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(view.getContext(), "Error al agregar usuario", Toast.LENGTH_SHORT).show();
                            }
                            status = model.insertDireccion(view.getContext(), direccion);

                            Cursor cursor = model.selectDireccionNom(getApplicationContext(), direccion.getNombre());
                            cursor.moveToFirst();
                            int idx = cursor.getColumnIndexOrThrow("id");

                            Cursor cursor2 = model.selectUsuarioTel(getApplicationContext(), usuario.getTelefono());
                            cursor2.moveToFirst();
                            int idx2 = cursor2.getColumnIndexOrThrow("id");

                            // Objeto DireccionXUsuario
                            direccionXCliente.setDireccionID(String.valueOf(cursor.getInt(idx)));
                            direccionXCliente.setUsuarioID(String.valueOf(cursor2.getInt(idx2)));
                            model.insertDireccionXCliente(getApplicationContext(), direccionXCliente);

                        if (status == 1){
                            Toast.makeText(view.getContext(), "Direccion agregada con éxito", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(view.getContext(), "Error al agregar direccion", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Tus contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        }
}