package br.edu.ifsp.arq.domos5_2021.meuslivros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifsp.arq.domos5_2021.meuslivros.R;
import br.edu.ifsp.arq.domos5_2021.meuslivros.constants.Constants;
import br.edu.ifsp.arq.domos5_2021.meuslivros.controller.AmigosController;
import br.edu.ifsp.arq.domos5_2021.meuslivros.controller.LivroController;
import br.edu.ifsp.arq.domos5_2021.meuslivros.dao.LivroContract;
import br.edu.ifsp.arq.domos5_2021.meuslivros.model.Amigo;

public class DetalhesActivity extends AppCompatActivity {

    private EditText mTitleEditText;
    private EditText mAuthorEditText;
    private CheckBox mBorrowedCheckBox;
    private Spinner mSpinner;
    private Button mSaveButton;
    private Button mUpdateButton;
    private int id;
    private Amigo amigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        id = -1;
        mTitleEditText = findViewById(R.id.edit_title);
        mAuthorEditText = findViewById(R.id.edit_author);
        mBorrowedCheckBox = findViewById(R.id.check_borrowed);
        mSaveButton = findViewById(R.id.button_save);
        mUpdateButton = findViewById(R.id.button_upgrade);
        mSpinner = findViewById(R.id.spinner_amigos);

        mSaveButton.setOnClickListener(v -> saveLivro());
        mUpdateButton.setOnClickListener(v -> updateLivro());

        mBorrowedCheckBox.setOnClickListener(v -> {
            if(mBorrowedCheckBox.isChecked()){
                mSpinner.setVisibility(View.VISIBLE);
            }else{
                mSpinner.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            id = bundle.getInt(LivroContract.LivroEntry._ID);
            mTitleEditText.setText(bundle.getString(LivroContract.LivroEntry.COLUMN_TITLE));
            mAuthorEditText.setText(bundle.getString(LivroContract.LivroEntry.COLUMN_AUTHOR));
            mBorrowedCheckBox.setChecked(bundle.getBoolean(LivroContract.LivroEntry.COLUMN_BORROWED));
            mSaveButton.setVisibility(View.GONE);
            mBorrowedCheckBox.setVisibility(View.VISIBLE);
            mUpdateButton.setVisibility(View.VISIBLE);

            //if(mBorrowedCheckBox.isChecked()) {
                mSpinner.setVisibility(View.VISIBLE);
                mSpinner.setAdapter(AmigosController.getAmigosAdapter(this));
                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        amigo = (Amigo) parent.getAdapter().getItem(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        amigo = null;
                    }
                });
            //}
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
        if(!mBorrowedCheckBox.isChecked()){
            amigo = null;
        }
        if(LivroController.atualizarLivro(this, id, mTitleEditText.getText().toString(), mAuthorEditText.getText().toString(), mBorrowedCheckBox.isChecked(), amigo)){
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