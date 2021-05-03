package br.edu.ifsp.arq.domos5_2021.sorteador_dmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifsp.arq.domos5_2021.sorteador_dmo.R;
import br.edu.ifsp.arq.domos5_2021.sorteador_dmo.model.Sorteio;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final int LIMITE_NULO = -1;
    private Button mSortearButton;
    private Button mLimitadorButton;
    private TextView mSaidaView;
    private TextView mMessageView;
    private EditText mBorderEditText;
    private Sorteio mSorteio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sorteio sem limite
        mSorteio = new Sorteio();

        //Recuperar as referências do leiaute
        mSaidaView = findViewById(R.id.view_number);
        mSortearButton = findViewById(R.id.button_sort);
        mLimitadorButton = findViewById(R.id.button_limiter);
        mMessageView = findViewById(R.id.view_message);
        mBorderEditText = findViewById(R.id.text_border);

        //Configurar o listener do botão
        mSortearButton.setOnClickListener(this);
        mLimitadorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_sort:
                sortear();
                break;
            case R.id.button_limiter:
                defineLimite();
                break;
        }
    }

    private void sortear(){
        //mSaidaView.setText(String.format("%d", Sorteio.novoInteiro()));
        mSaidaView.setText(String.format("%d", mSorteio.obtemInteiro()));
    }

    private void defineLimite(){
        mSorteio = new Sorteio(obtemLimite());
        mMessageView.setText(String.format("Definido limite de 1 a %d", mSorteio.getBorder()));
    }

    private int obtemLimite(){
        int borda;
        try{
            borda = Integer.parseInt(mBorderEditText.getText().toString());
        }catch (NumberFormatException e){
            borda = LIMITE_NULO;
        }
        return borda;
    }
}