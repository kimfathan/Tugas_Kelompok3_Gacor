package com.example.tugas_kelompok3_gacor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPopular);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Product> popularProducts = new ArrayList<>();
        popularProducts.add(new Product("Kemeja Batik Katun Halus", "Rp 245.000"));
        popularProducts.add(new Product("Set Alat Masak Ergonomis", "Rp 180.000"));

        ProductAdapter adapter = new ProductAdapter(popularProducts);
        recyclerView.setAdapter(adapter);

        return view;
    }
}