package br.pro.ednilsonrossi.hellodroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mensagemTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensagemTextView = findViewById(R.id.textview_mensagem);

        mensagemTextView.setText("Hello Droid!!!");
    }
}