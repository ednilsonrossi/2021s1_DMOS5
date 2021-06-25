package br.edu.ifsp.arq.domos5_2021.meuslivros.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.arq.domos5_2021.meuslivros.R;
import br.edu.ifsp.arq.domos5_2021.meuslivros.model.Livro;
import br.edu.ifsp.arq.domos5_2021.meuslivros.view.RecyclerItemClickListener;

public class ItemLivrosAdapter extends RecyclerView.Adapter<ItemLivrosAdapter.ViewHolder> {

    private List<Livro> dataSource;
    private static RecyclerItemClickListener mClickListener;

    public ItemLivrosAdapter(List<Livro> dataSource, RecyclerItemClickListener listener) {
        this.dataSource = dataSource;
        mClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livro, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLivrosAdapter.ViewHolder holder, int position) {
        holder.mTitleTextView.setText(dataSource.get(position).getTitulo());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(List<Livro> dataSource) {
        this.dataSource = dataSource;
        this.notifyDataSetChanged();
    }

    public List<Livro> getDataSource() {
        return dataSource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.text_item_titulo);
            itemView.setOnClickListener(v -> mClickListener.onClick(getAdapterPosition()));
        }
    }

}
