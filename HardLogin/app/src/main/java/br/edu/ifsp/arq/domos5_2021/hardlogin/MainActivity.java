package br.edu.ifsp.arq.domos5_2021.hardlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final String USER = "ednilsonrossi@ifsp.edu.br";
    private final int PASSWORD = 12345;
    private EditText mUserEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuperando referencias do layout
        mUserEditText = findViewById(R.id.edit_user);
        mPasswordEditText = findViewById(R.id.edit_password);
        mLoginButton = findViewById(R.id.button_login);

        //Configurando listener
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mLoginButton){
            login();
        }
    }

    private void login(){
        String username = mUserEditText.getText().toString();
        int password;
        try{
            password = Integer.valueOf(mPasswordEditText.getText().toString()).intValue();
        } catch (NumberFormatException ex){
            password = 0;
        }

        if(checkLogin(username, password)){
            Toast.makeText(this, getString(R.string.msg_login_success), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, getString(R.string.msg_login_error), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkLogin(String username, int password){
        return username.equalsIgnoreCase(USER) && password == PASSWORD;
    }
}