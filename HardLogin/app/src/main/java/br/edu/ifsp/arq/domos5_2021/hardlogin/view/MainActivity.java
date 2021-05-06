package br.edu.ifsp.arq.domos5_2021.hardlogin.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifsp.arq.domos5_2021.hardlogin.R;
import br.edu.ifsp.arq.domos5_2021.hardlogin.constantes.Constantes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mChangeBackgroundView;
    private ConstraintLayout mConstraintLayout;
    private EditText mUserEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private CheckBox mSaveLoginCheckBox;
    private boolean mBlueBackgroud;

    //Referências para objetos de preferências do usuário
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuperando referencias do layout
        mChangeBackgroundView = findViewById(R.id.view_backgound_change);
        mConstraintLayout = findViewById(R.id.constraint_layout);
        mUserEditText = findViewById(R.id.edit_user);
        mPasswordEditText = findViewById(R.id.edit_password);
        mLoginButton = findViewById(R.id.button_login);
        mSaveLoginCheckBox = findViewById(R.id.check_save_data);

        //Configurando listener
        mLoginButton.setOnClickListener(this);
        mChangeBackgroundView.setOnClickListener(this);

        //Instanciar as predferências de modo privado, ou seja, somente acessível ao app;
        mSharedPreferences = this.getPreferences(MODE_PRIVATE);
        //Verificar se o usuário já salvou suas preferências
        checkPreferences();

        updateUI();
    }



    @Override
    public void onClick(View view) {
        if(view == mLoginButton){
            login();
        }else if(view == mChangeBackgroundView){
            changeBackground();
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
        //Grava as preferências do usuário
        savePreferences(username, password);

        //Criar um pacote/embrulho de dados (Bundle) para enviar para nova Acvity
        Bundle embrulho = new Bundle();
        embrulho.putString(Constantes.KEY_USERNAME, username);
        embrulho.putInt(Constantes.KEY_PASSWORD, password);

        //Abrir a LoginActivity
        Intent intencao = new Intent(this, LoginActivity.class);
        //Incluir o embrulho na intenção
        intencao.putExtras(embrulho);
        startActivity(intencao);
    }

    private void checkPreferences(){
        String usuario = mSharedPreferences.getString(Constantes.KEY_USERNAME, "");
        int senha = mSharedPreferences.getInt(Constantes.KEY_PASSWORD, -1);
        boolean lembrar = mSharedPreferences.getBoolean(Constantes.KEY_SAVE_PREFERENCES, false);
        mBlueBackgroud = mSharedPreferences.getBoolean(Constantes.KEY_DEFAULT_BACKGROUND, true);
        if(lembrar){
            mUserEditText.setText(usuario);
            mPasswordEditText.setText(String.format("%d", senha));
            mSaveLoginCheckBox.setChecked(true);
        }
    }

    private void savePreferences(String username, int password){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if(mSaveLoginCheckBox.isChecked()){
            editor.putString(Constantes.KEY_USERNAME, username)
                    .putInt(Constantes.KEY_PASSWORD, password)
                    .putBoolean(Constantes.KEY_SAVE_PREFERENCES, true)
                    .putBoolean(Constantes.KEY_DEFAULT_BACKGROUND, mBlueBackgroud);
        }else{
            editor.putString(Constantes.KEY_USERNAME, "")
                    .putInt(Constantes.KEY_PASSWORD, -1)
                    .putBoolean(Constantes.KEY_SAVE_PREFERENCES, false)
                    .putBoolean(Constantes.KEY_DEFAULT_BACKGROUND, mBlueBackgroud);
        }
        editor.commit();
    }

    private void changeBackground(){
        mBlueBackgroud = !mBlueBackgroud;
        savePreferences("", -1);
        updateUI();
    }

    private void updateUI(){
        if(!mBlueBackgroud){
            mConstraintLayout.setBackgroundColor(getColor(R.color.black));
            mSaveLoginCheckBox.setTextColor(getColor(R.color.white));
            mChangeBackgroundView.setTextColor(getColor(R.color.white));
        }else{
            mConstraintLayout.setBackgroundColor(getColor(R.color.backgroud_app));
            mSaveLoginCheckBox.setTextColor(getColor(R.color.black));
            mChangeBackgroundView.setTextColor(getColor(R.color.black));
        }
    }
}