package com.hfad.workout;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  WorkoutDetailFragment frag =  (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        frag.setWorkout(1);*/ //эти строки уже не нужны, показывали текст из 1 позиции.
    }

    @Override
    public void itemClicked(long id) {

    }
}
