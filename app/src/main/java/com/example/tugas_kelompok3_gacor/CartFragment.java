package com.example.tugas_kelompok3_gacor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Fragment Keranjang Belanja (Layar 3).
 * Menangani tampilan daftar belanja dan kalkulasi total harga secara real-time.
 */
public class CartFragment extends Fragment implements CartAdapter.OnCartChangeListener {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private List<CartItem> cartItems;
    private TextView textTotalAmount;
    private MaterialButton btnCheckout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // 1. Inisialisasi View
        recyclerView = view.findViewById(R.id.recyclerViewCart);
        textTotalAmount = view.findViewById(R.id.textTotalAmount);
        btnCheckout = view.findViewById(R.id.btnCheckout);

        // 2. Persiapan Data Dummy
        cartItems = new ArrayList<>();
        cartItems.add(new CartItem("Alat Tensi Digital", "Merk Sehat Selalu", "Rp 450.000", 1));
        cartItems.add(new CartItem("Vitamin D3 1000IU", "Isi 60 Tablet", "Rp 125.000", 2));

        // 3. Setup RecyclerView & Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CartAdapter(cartItems, this);
        recyclerView.setAdapter(adapter);

        // 4. Hitung Total Awal
        calculateInitialTotal();

        // 5. Logika Tombol Checkout
        btnCheckout.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(getContext(), "Keranjang Anda kosong", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Menuju Halaman Pembayaran...", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void calculateInitialTotal() {
        long total = 0;
        for (CartItem item : cartItems) {
            long price = parsePrice(item.getPrice());
            total += (price * item.getQuantity());
        }
        updateTotalUI(total);
    }

    /**
     * Callback dari Adapter saat jumlah barang berubah
     */
    @Override
    public void onTotalChanged(long total) {
        updateTotalUI(total);
    }

    /**
     * Callback dari Adapter saat barang dihapus
     */
    @Override
    public void onItemDeleted() {
        if (cartItems.isEmpty()) {
            // Tampilkan state kosong jika perlu
        }
    }

    private void updateTotalUI(long total) {
        // Format ke mata uang Rupiah
        NumberFormat formatter = NumberFormat.getInstance(new Locale("in", "ID"));
        textTotalAmount.setText("Rp " + formatter.format(total));
    }

    private long parsePrice(String priceString) {
        try {
            return Long.parseLong(priceString
                    .replace("Rp ", "")
                    .replace(".", "")
                    .trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}