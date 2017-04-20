package com.noisyz.bindexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bindlibrary.generated.movie.adapter.MovieBindAdapter;
import com.noisyz.bindexample.model.Movie;
import com.noisyz.bindexample.model.MovieStore;
import com.noisyz.bindlibrary.callback.clickevent.OnItemClickListener;
import com.noisyz.noisybindexample.R;


public class MainActivity extends AppCompatActivity implements OnItemClickListener<Movie> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieBindAdapter(MovieStore.getAllMovies(), R.layout.list_item).setOnItemClickListener(this));
    }

    @Override
    public void onItemClick(View convertView, int position, Movie movie) {
        startActivity(DetailActivity.buildIntent(this, position));
    }
}
