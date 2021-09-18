package com.example.eatsdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.DireccionXCliente;
import com.example.eatsdelivery.SQLite.Tables.InfoUsuario;
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
    InfoUsuario infoUsuario;
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
        // Crea info de usuario
        infoUsuario = new InfoUsuario();

        nameClient = (EditText)findViewById(R.id.name_reg);
        usernameClient = (EditText)findViewById(R.id.username_reg);
        numberClient = (EditText)findViewById(R.id.phone_reg);
        mailClient = (EditText)findViewById(R.id.email_reg);
        passwordClient = (EditText)findViewById(R.id.password_reg);
        confirmPass = (EditText)findViewById(R.id.confpass_reg);
        directionClient = (EditText)findViewById(R.id.direction_reg);
        nameDir = (EditText)findViewById(R.id.nameDir);

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

                // Verifica que todos los textview esten llenos
                if (user.equals("") && pass.equals("") && passConfirmed.equals("") && name.equals("") && mail.equals("") && dir.equals("")){
                    Toast.makeText(RegisterActivity.this,"No has llenado los espacios",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (pass.equals(passConfirmed)) {
                        //Verifica que haya agregado tipo de pago
                        if (tarjeta != null) {
                            // Objeto direccion
                            direccion.setNombre(nombreDir);
                            direccion.setDescripcion(dir);
                            // Objeto Usuario
                            usuario.setUsuario(user);
                            usuario.setContrasenna(pass);
                            usuario.setTipoAccesoID(tda.getTipo());
                            // Objeto Info Usuario
                            infoUsuario.setNombre(name);
                            infoUsuario.setCorreo(mail);
                            infoUsuario.setUsuarioID(usuario.getUsuarioID());
                            infoUsuario.setTelefono(num);
                            // Objeto DireccionXUsuario
                            direccionXCliente.setDireccionID(direccion.getDireccionID());
                            direccionXCliente.setInfoUsuarioID(infoUsuario.getInfoUsuarioID());


                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Debes agregar un método de pago",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Tus contraseñas no son iguales. Deben serlo.",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });



    }


    public void addPaymentMethod(View view){

        Intent next = new Intent(this, AddPaymentMethodActivity.class);
        startActivity(next);
    }



    public EditText getNameClient() {
        return nameClient;
    }

    public EditText getUsernameClient() {
        return usernameClient;
    }

    public EditText getNumberClient() {
        return numberClient;
    }

    public EditText getMailClient() {
        return mailClient;
    }

    public EditText getPasswordClient() {
        return passwordClient;
    }

    public EditText getConfirmPass() {
        return confirmPass;
    }

    public EditText getDirectionClient() {
        return directionClient;
    }
}