package br.edu.ednilsonrossi.meualunofirebase.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ednilsonrossi.meualunofirebase.R;
import br.edu.ednilsonrossi.meualunofirebase.model.Aluno;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.ViewHolder>{

    private List<Aluno> mAlunoList;

    public AlunoAdapter(@NonNull List<Aluno> alunos) {
        this.mAlunoList = alunos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_aluno, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nomeAlunoTextView.setText(mAlunoList.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return mAlunoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nomeAlunoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeAlunoTextView = itemView.findViewById(R.id.textview_nome_aluno);
        }
    }
}
