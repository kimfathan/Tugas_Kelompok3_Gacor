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

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Sepatu Jalan Empuk (Anti-Slip)", "Rp 450.000"));
        productList.add(new Product("Multivitamin Senior (Isi 60)", "Rp 225.000"));
        productList.add(new Product("Masker Lembut Premium (50pcs)", "Rp 85.000"));

        ProductAdapter adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}