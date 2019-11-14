package com.map524s1a.snakegame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends Fragment {
    private SnakeGameView snakeGameView; // custom view to display the game

    // called when Fragment's view needs to be created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // inflate the fragment_main.xml layout
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // get a reference to the CannonView
        snakeGameView = (SnakeGameView) view.findViewById(R.id.snakeGameView);
        return view;
    }

    // set up volume control once Activity is created
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // when MainActivity is paused, terminate the game
    @Override
    public void onPause() {
        super.onPause();
    }

    // when MainActivity is paused, MainActivityFragment releases resources
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
