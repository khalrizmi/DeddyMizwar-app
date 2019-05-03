package com.example.hamz.dedymizwarapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.adapter.ProgressAdapter;
import com.example.hamz.dedymizwarapp.model.History;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ArrayList<History> histories;
    ProgressAdapter progressAdapter;
    View RootView;

    public CompletedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        RootView = view;

        loadDummy();
        init();

        return view;
    }

    private void init() {
        swipeRefreshLayout = RootView.findViewById(R.id.swiperefreshlayout);
        recyclerView = RootView.findViewById(R.id.recyclerview);
        progressAdapter = new ProgressAdapter(getContext(), histories);
        recyclerView.setAdapter(progressAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showProgress() {
        if (!swipeRefreshLayout.isRefreshing()) {

            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    private void hideProgress() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void loadDummy() {
        histories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            histories.add(new History(i));
        }
    }

}
