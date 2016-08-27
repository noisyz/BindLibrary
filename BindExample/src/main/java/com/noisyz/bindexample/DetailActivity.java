package com.noisyz.bindexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.noisyz.bindexample.model.Movie;
import com.noisyz.bindexample.model.MovieStore;
import com.noisyz.bindlibrary.base.impl.ObjectDataBinder;
import com.noisyz.noisybindexample.R;

public class DetailActivity extends AppCompatActivity {

   private static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";

   public static Intent buildIntent(final Context context, final int movieId) {
      Intent intent = new Intent(context, DetailActivity.class);
      intent.putExtra(EXTRA_MOVIE_ID, movieId);
      return intent;
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      final int movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, 0);

      final Movie movie = MovieStore.getAllMovies().get(movieId);
      setContentView(R.layout.activity_detail);
      new ObjectDataBinder(movie).registerView(this).bindUI();
   }
}
