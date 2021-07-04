package br.edu.ifsp.arq.domos5_2021.meuslivros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.arq.domos5_2021.meuslivros.R;
import br.edu.ifsp.arq.domos5_2021.meuslivros.constants.Constants;
import br.edu.ifsp.arq.domos5_2021.meuslivros.controller.LivroController;

public class DetalhesActivity extends AppCompatActivity {

    private EditText mTitleEditText;
    private EditText mAuthorEditText;
    private CheckBox mBorrowedCheckBox;
    private Button mSaveButton;
    private Button mUpdateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        mTitleEditText = findViewById(R.id.edit_title);
        mAuthorEditText = findViewById(R.id.edit_author);
        mBorrowedCheckBox = findViewById(R.id.check_borrowed);
        mSaveButton = findViewById(R.id.button_save);
        mUpdateButton = findViewById(R.id.button_upgrade);

        mSaveButton.setOnClickListener(v -> saveLivro());
        mUpdateButton.setOnClickListener(v -> updateLivro());


    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mTitleEditText.setText(bundle.getString(Constants.KEY_TITLE));
            mAuthorEditText.setText(bundle.getString(Constants.KEY_AUTHOR));
            mBorrowedCheckBox.setChecked(bundle.getBoolean(Constants.KEY_BORROWED));
            mSaveButton.setVisibility(View.GONE);
            mTitleEditText.setEnabled(false);
            mBorrowedCheckBox.setVisibility(View.VISIBLE);
            mUpdateButton.setVisibility(View.VISIBLE);
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

    private void updateLivro(){
        if(LivroController.atualizarLivro(this, mTitleEditText.getText().toString(), mAuthorEditText.getText().toString(), mBorrowedCheckBox.isChecked())){
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