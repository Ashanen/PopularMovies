package com.example.android.popularmovies1.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies1.Models.Review;
import com.example.android.popularmovies1.R;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {
    private final List<Review> reviewList;

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        private final TextView movieReviewAuthor;
        private final TextView movieReviewContent;

        private ReviewViewHolder(View view) {
            super(view);
            movieReviewAuthor = view.findViewById(R.id.movie_review_author);
            movieReviewContent = view.findViewById(R.id.movie_review_content);
        }
    }

    public ReviewsAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewsAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                              int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.review_recycler_item, viewGroup, false);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.ReviewViewHolder holder,
                                 int position) {
        String reviewAuthor = reviewList.get(position).getAuthor();
        String reviewContent = reviewList.get(position).getContent();

        holder.movieReviewAuthor.setText(reviewAuthor);
        holder.movieReviewContent.setText(reviewContent);
    }

    @Override
    public int getItemCount() {
        return (reviewList == null) ? 0 : reviewList.size();
    }
}
