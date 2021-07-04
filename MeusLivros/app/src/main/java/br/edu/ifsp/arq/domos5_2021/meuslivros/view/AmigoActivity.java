package br.edu.ifsp.arq.domos5_2021.meuslivros.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import br.edu.ifsp.arq.domos5_2021.meuslivros.R;
import br.edu.ifsp.arq.domos5_2021.meuslivros.controller.AmigosController;

public class AmigoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private Button mButton;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigo);

        layout = findViewById(R.id.layout_amigo);
        mEditText = findViewById(R.id.edit_name);
        mButton = findViewById(R.id.button_save_friend);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mButton){
            if(!mEditText.getText().toString().isEmpty()) {
                if (AmigosController.insereAmigo(this, mEditText.getText().toString())) {
                    showSnack(getString(R.string.message_success));
                }else{
                    showSnack(getString(R.string.message_erros));
                }
                finish();
            }
        }
    }

    private void showSnack(String msg){
        Snackbar.make(layout, msg, Snackbar.LENGTH_SHORT).show();
    }
}