package com.example.tugas_kelompok3_gacor;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * MainActivity sebagai kontainer utama aplikasi.
 * Menangani navigasi bawah (BottomNavigationView) dan Tombol Darurat Persisten.
 */
public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabEmergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inisialisasi Navigasi Bawah
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            // Pemilihan Fragment berdasarkan posisi menu
            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_search) {
                selectedFragment = new SearchFragment();
            } else if (id == R.id.nav_cart) {
                selectedFragment = new CartFragment();
            } else if (id == R.id.nav_orders) {
                selectedFragment = new OrdersFragment();
            } else if (id == R.id.nav_chat) { // Sesuai visual: Chat/Profil
                selectedFragment = PlaceholderFragment.newInstance("Profil & Bantuan");
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // Set Fragment default saat aplikasi pertama dibuka
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        // 2. Logika Tombol Darurat Persisten
        fabEmergency = findViewById(R.id.fab_emergency);
        
        // Mencegah klik tidak sengaja dengan Long Click (1.5 Detik)
        fabEmergency.setOnLongClickListener(v -> {
            showEmergencyDialog();
            return true;
        });

        // Memberi petunjuk jika hanya diklik biasa
        fabEmergency.setOnClickListener(v -> {
            Toast.makeText(this, "Tekan lama (1.5 detik) untuk bantuan darurat", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Menampilkan Modal Konfirmasi Darurat (Layar 4)
     */
    private void showEmergencyDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_emergency_confirmation, null);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();
        
        // Transparansi background agar CardView rounded terlihat sempurna
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        MaterialButton btnConfirm = dialogView.findViewById(R.id.btnConfirmEmergency);
        MaterialButton btnCancel = dialogView.findViewById(R.id.btnCancelEmergency);

        // Aksi: YA, SAYA MEMBUTUHKAN BANTUAN
        btnConfirm.setOnClickListener(v -> {
            kirimSinyalDarurat();
            dialog.dismiss();
        });

        // Aksi: BATAL
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    /**
     * Method Placeholder untuk pengiriman sinyal darurat
     */
    private void kirimSinyalDarurat() {
        // Logika pengiriman koordinat GPS atau SMS darurat bisa ditambahkan di sini
        Toast.makeText(this, "Sinyal Darurat Terkirim! Bantuan sedang menuju lokasi Anda.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_text_size) {
            Toast.makeText(this, "Pengaturan Ukuran Teks", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_help) {
            Toast.makeText(this, "Pusat Bantuan", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_logout) {
            showLogoutConfirmation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLogoutConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Keluar Akun?")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("KELUAR", (dialog, which) -> finish())
                .setNegativeButton("TIDAK", null)
                .show();
    }
}