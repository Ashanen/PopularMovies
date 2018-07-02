package com.example.android.popularmovies1.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies1.Models.Trailer;
import com.example.android.popularmovies1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder> {
    private final Context context;
    private final List<Trailer> trailerList;

    class TrailerViewHolder extends RecyclerView.ViewHolder {
        private final ImageView movieTrailerThumbnail;

        private TrailerViewHolder(View itemView) {
            super(itemView);
            movieTrailerThumbnail = itemView.findViewById(R.id.trailerPoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        String trailer_id = trailerList.get(position).getKey();

                        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(context.getString(R.string.youtube_web_intent) +
                                        trailer_id));
                        Intent appIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(context.getString(R.string.youtube_app_intent) +
                                        trailer_id));

                        try {
                            appIntent.putExtra(context.getString(R.string.TRAILER_ID_KEY),
                                    trailer_id);

                            context.startActivity(appIntent);
                        } catch (ActivityNotFoundException ex) {

                            webIntent.putExtra(context.getString(R.string.TRAILER_ID_KEY),
                                    trailer_id);
                            context.startActivity(webIntent);
                        }
                    }
                }
            });
        }
    }

    public TrailersAdapter(Context context, List<Trailer> trailerList) {
        this.context = context;
        this.trailerList = trailerList;
    }

    @NonNull
    @Override
    public TrailersAdapter.TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trailer_list_item, viewGroup, false);

        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder,
                                 int position) {

        String trailerThumbnail = context.getString(R.string.trailer_thumbnail_URL_part_one) +
                trailerList.get(position).getKey() +
                context.getString(R.string.trailer_thumbnail_URL_part_two);

        Picasso.get()
                .load(trailerThumbnail)
                .into(holder.movieTrailerThumbnail);
    }

    @Override
    public int getItemCount() {
        return (trailerList == null) ? 0 : trailerList.size();
    }
}
