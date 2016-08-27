package com.noisyz.bindexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.noisyz.bindexample.model.Movie;
import com.noisyz.bindexample.model.MovieStore;
import com.noisyz.bindlibrary.base.impl.adapter.recyclerview.RecyclerBindAdapter;
import com.noisyz.bindlibrary.callback.clickevent.OnItemClickListener;
import com.noisyz.noisybindexample.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {
        final RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        RecyclerBindAdapter adapter = new RecyclerBindAdapter(MovieStore.getAllMovies(), R.layout.list_item)
                .setOnItemClickListener(new OnItemClickListener<Movie>() {
                    @Override
                    public void onItemClick(View convertView, int position, Movie movie) {
                        startActivity(DetailActivity.buildIntent(getApplicationContext(), position));
                    }
                });
        list.setAdapter(adapter);
    }
}
