package br.edu.ifsp.arq.domos5_2021.meupocket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.edu.ifsp.arq.domos5_2021.meupocket.R;
import br.edu.ifsp.arq.domos5_2021.meupocket.model.Site;

public class ItemSiteAdapter extends ArrayAdapter {

    private LayoutInflater inflater;

    public ItemSiteAdapter(Context context, List<Site> data) {
        super(context, R.layout.item_list_pocket, data);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_list_pocket, null);
            holder = new ViewHolder();
            holder.tituloTextView = convertView.findViewById(R.id.text_titulo);
            holder.enderecoTextView = convertView.findViewById(R.id.text_endereco);
            holder.favoritoImageView = convertView.findViewById(R.id.img_icon_favorite);
            holder.favoritoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v == holder.favoritoImageView){
                        heartClick(position);
                    }
                }
            });
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Site site = (Site) getItem(position);
        holder.tituloTextView.setText(site.getTitle());
        holder.enderecoTextView.setText(site.getUrl());
        if(site.isFavorite()){
            holder.favoritoImageView.setColorFilter(convertView.getResources().getColor(R.color.red, convertView.getContext().getTheme()));
        }else{
            holder.favoritoImageView.setColorFilter(convertView.getResources().getColor(R.color.gray, convertView.getContext().getTheme()));
        }
        return convertView;
    }

    private void heartClick(int position){
        Site site = (Site) getItem(position);
        site.setFavorite(!site.isFavorite());
        notifyDataSetChanged();
    }

    private static class ViewHolder{
        public TextView tituloTextView;
        public TextView enderecoTextView;
        public ImageView favoritoImageView;

    }
}
