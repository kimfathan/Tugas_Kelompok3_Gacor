package com.example.tugas_kelompok3_gacor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.List;

/**
 * Adapter untuk menampilkan daftar belanja di Keranjang.
 * Menangani logika penambahan, pengurangan, dan penghapusan item secara real-time.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private OnCartChangeListener listener;

    // Interface untuk berkomunikasi dengan Fragment saat ada perubahan harga/jumlah
    public interface OnCartChangeListener {
        void onTotalChanged(long total);
        void onItemDeleted();
    }

    public CartAdapter(List<CartItem> cartItems, OnCartChangeListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.cartProductName.setText(item.getName());
        holder.cartProductBrand.setText(item.getBrand());
        holder.cartProductPrice.setText(item.getPrice());
        holder.textQuantity.setText(String.valueOf(item.getQuantity()));

        // Logika Tombol Plus (+)
        holder.btnPlus.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            holder.textQuantity.setText(String.valueOf(item.getQuantity()));
            updateTotal();
        });

        // Logika Tombol Minus (-)
        holder.btnMinus.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.textQuantity.setText(String.valueOf(item.getQuantity()));
                updateTotal();
            }
        });

        // Logika Tombol Sampah (Hapus)
        holder.btnDelete.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                cartItems.remove(currentPos);
                notifyItemRemoved(currentPos);
                notifyItemRangeChanged(currentPos, cartItems.size());
                updateTotal();
                if (listener != null) listener.onItemDeleted();
            }
        });
    }

    private void updateTotal() {
        if (listener != null) {
            long total = 0;
            for (CartItem item : cartItems) {
                // Parsing harga (Asumsi format "Rp 450.000")
                long price = Long.parseLong(item.getPrice()
                        .replace("Rp ", "")
                        .replace(".", ""));
                total += (price * item.getQuantity());
            }
            listener.onTotalChanged(total);
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cartProductName, cartProductBrand, cartProductPrice, textQuantity;
        MaterialButton btnMinus, btnPlus, btnDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartProductName = itemView.findViewById(R.id.cartProductName);
            cartProductBrand = itemView.findViewById(R.id.cartProductBrand);
            cartProductPrice = itemView.findViewById(R.id.cartProductPrice);
            textQuantity = itemView.findViewById(R.id.textQuantity);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            // Mencari tombol delete (OutlinedButton di item_cart.xml)
            btnDelete = (MaterialButton) ((ViewGroup) itemView).getChildAt(0); // Tombol pertama di baris kontrol
            // Catatan: Pastikan ID ditambahkan di XML untuk akses yang lebih baik
            btnDelete = itemView.findViewById(R.id.btnDelete); 
        }
    }
}