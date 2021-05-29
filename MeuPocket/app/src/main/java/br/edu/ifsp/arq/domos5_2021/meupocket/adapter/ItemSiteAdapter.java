package br.edu.ifsp.arq.domos5_2021.meupocket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.edu.ifsp.arq.domos5_2021.meupocket.R;
import br.edu.ifsp.arq.domos5_2021.meupocket.model.Site;
import br.edu.ifsp.arq.domos5_2021.meupocket.view.RecyclerItemClickListener;

public class ItemSiteAdapter extends RecyclerView.Adapter<ItemSiteAdapter.ViewHolder> {

    private Context mContext;
    private List<Site> mSites;
    private static RecyclerItemClickListener mClickListener;

    public ItemSiteAdapter(Context context, List<Site> data) {
        this.mContext = context;
        this.mSites = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pocket, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSiteAdapter.ViewHolder holder, int position) {
        Site site = mSites.get(position);
        holder.tituloTextView.setText(site.getTitle());
        holder.enderecoTextView.setText(site.getUrl());
        if (site.isFavorite()) {
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.red, mContext.getTheme()));
        } else {
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.gray, mContext.getTheme()));
        }
        holder.favoritoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == holder.favoritoImageView) {
                    heartClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSites.size();
    }

    public void setClickListener(RecyclerItemClickListener clickListener){
        this.mClickListener = clickListener;
    }

    private void heartClick(int position) {
        Site site = mSites.get(position);
        site.setFavorite(!site.isFavorite());
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tituloTextView;
        public TextView enderecoTextView;
        public ImageView favoritoImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.text_titulo);
            enderecoTextView = itemView.findViewById(R.id.text_endereco);
            favoritoImageView = itemView.findViewById(R.id.img_icon_favorite);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null){
                mClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
