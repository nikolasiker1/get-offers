package com.nikolasiker.feature_offer_list.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nikolasiker.feature_offer_list.R;
import com.nikolasiker.lib_api.model.Offer;

import java.util.List;

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.ViewHolder> {

    private List<Offer> data;

    public OfferListAdapter(List<Offer> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTitleTextView().setText(data.get(position).getTitle());
        Glide
                .with(holder.getImageView().getContext())
                .load(data.get(position).getThumbnail().getHires())
                .thumbnail(Glide.with(holder.getImageView().getContext()).load(data.get(position).getThumbnail().getLowres()))
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.offerTextView);
            imageView = view.findViewById(R.id.offerImageView);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
