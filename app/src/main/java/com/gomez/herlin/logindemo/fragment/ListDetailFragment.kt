package com.gomez.herlin.logindemo.fragment;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomez.herlin.logindemo.R;
import com.gomez.herlin.logindemo.adapter.ListDetailAdapter;

import java.util.List;

public class ListDetailFragment extends Fragment  {

    private List<String> list;

    public ListDetailFragment(List<String> stringList) {
        this.list = stringList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewListDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ListDetailAdapter(list));
        return view;
    }

}
