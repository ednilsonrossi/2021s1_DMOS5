package br.edu.ednilsonrossi.meualunofirebase.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.ednilsonrossi.meualunofirebase.R;
import br.edu.ednilsonrossi.meualunofirebase.model.Aluno;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_ALUNOS = "alunos";
    private List<Aluno> mAlunoList;

    private EditText prontuarioEditText;
    private EditText nomeEditText;
    private EditText emailEditText;
    private RecyclerView alunosRecyclerView;
    private AlunoAdapter mAlunoAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prontuarioEditText = findViewById(R.id.edittext_prontuario);
        nomeEditText = findViewById(R.id.edittext_nome);
        emailEditText = findViewById(R.id.edittext_email);
        alunosRecyclerView = findViewById(R.id.recycler_alunos);

        mAlunoList = new ArrayList<>();
        configDataBase();
        configRecyclerView();
    }

    private void configRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        alunosRecyclerView.setLayoutManager(layoutManager);

        mAlunoAdapter = new AlunoAdapter(mAlunoList);
        alunosRecyclerView.setAdapter(mAlunoAdapter);

    }

    private void configDataBase() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(KEY_ALUNOS);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mAlunoList.clear();

                for (DataSnapshot alunoSnapshot : dataSnapshot.getChildren()) {
                    Aluno a = alunoSnapshot.getValue(Aluno.class);
                    mAlunoList.add(a);
                }
                alunosRecyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void salvarDados(View v){
        if(v.getId() == R.id.button_salvar){
            Aluno a = new Aluno(
                    prontuarioEditText.getText().toString(),
                    nomeEditText.getText().toString(),
                    emailEditText.getText().toString()
            );
            mDatabaseReference.child(a.getProntuario()).setValue(a);
        }
    }
}