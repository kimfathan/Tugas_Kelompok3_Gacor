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
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import java.util.List;

/**
 * Fragment Pesanan Saya (Layar 5).
 * Menangani filter riwayat pesanan berdasarkan status: Semua, Berlangsung, dan Selesai.
 */
public class OrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private List<Order> allOrders;
    private List<Order> filteredOrders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        // 1. Inisialisasi View
        recyclerView = view.findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 2. Persiapan Data Riwayat Pesanan
        allOrders = new ArrayList<>();
        populateData();

        // Data awal yang ditampilkan (Semua)
        filteredOrders = new ArrayList<>(allOrders);
        adapter = new OrderAdapter(filteredOrders);
        recyclerView.setAdapter(adapter);

        // 3. Logika Filter Status (Chips)
        Chip chipAll = view.findViewById(R.id.chipAll);
        Chip chipActive = view.findViewById(R.id.chipActive);
        Chip chipDone = view.findViewById(R.id.chipDone);

        // Filter: Semua
        chipAll.setOnClickListener(v -> filterOrders("Semua"));

        // Filter: Berlangsung (Dalam Pengiriman & Menunggu Pembayaran)
        chipActive.setOnClickListener(v -> filterOrders("Berlangsung"));

        // Filter: Selesai
        chipDone.setOnClickListener(v -> filterOrders("Selesai"));

        return view;
    }

    private void populateData() {
        allOrders.add(new Order(
                "12 Mei 2024", "Dalam Pengiriman", "Paket Sembako A", "Rp 155.000",
                "Lacak Paket", R.color.status_green_bg, R.color.price_green, R.color.primary_blue
        ));
        allOrders.add(new Order(
                "11 Mei 2024", "Menunggu Pembayaran", "Alat Pijat Kaki", "Rp 425.000",
                "Bayar Sekarang", R.color.status_red_bg, R.color.emergency_red, R.color.primary_blue
        ));
        allOrders.add(new Order(
                "05 Mei 2024", "Selesai", "Kacamata Baca +2.0", "Rp 89.000",
                "Beli Lagi", R.color.status_grey_bg, R.color.text_grey, R.color.action_green
        ));
    }

    /**
     * Logika Filter List berdasarkan status yang dipilih
     */
    private void filterOrders(String criteria) {
        filteredOrders.clear();
        
        for (Order order : allOrders) {
            if (criteria.equals("Semua")) {
                filteredOrders.add(order);
            } else if (criteria.equals("Berlangsung")) {
                if (order.getStatus().equals("Dalam Pengiriman") || order.getStatus().equals("Menunggu Pembayaran")) {
                    filteredOrders.add(order);
                }
            } else if (criteria.equals("Selesai")) {
                if (order.getStatus().equals("Selesai")) {
                    filteredOrders.add(order);
                }
            }
        }
        
        // Memberitahu adapter bahwa data telah berubah
        adapter.notifyDataSetChanged();
    }
}