package br.edu.ifsp.arq.domos5_2021.meuslivros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.arq.domos5_2021.meuslivros.R;
import br.edu.ifsp.arq.domos5_2021.meuslivros.constants.Constants;
import br.edu.ifsp.arq.domos5_2021.meuslivros.controller.LivroController;

public class DetalhesActivity extends AppCompatActivity {

    private EditText mTitleEditText;
    private EditText mAuthorEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        mTitleEditText = findViewById(R.id.edit_title);
        mAuthorEditText = findViewById(R.id.edit_author);
        mButton = findViewById(R.id.button_save);

        mButton.setOnClickListener(v -> saveLivro());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mTitleEditText.setText(bundle.getString(Constants.KEY_TITLE));
            mAuthorEditText.setText(bundle.getString(Constants.KEY_AUTHOR));
            mButton.setVisibility(View.GONE);
            mTitleEditText.setEnabled(false);
            mAuthorEditText.setEnabled(false);
        }
    }

    private void saveLivro(){
        if(LivroController.salvarLivro(this, mTitleEditText.getText().toString(), mAuthorEditText.getText().toString())){
            showToast(getString(R.string.message_success));
        }else{
            showToast(getString(R.string.message_erros));
        }
        finish();
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}