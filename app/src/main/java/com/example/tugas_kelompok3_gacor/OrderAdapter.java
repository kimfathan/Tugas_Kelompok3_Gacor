package com.example.tugas_kelompok3_gacor;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderDate.setText(order.getDate());
        holder.orderStatus.setText(order.getStatus());
        holder.orderTitle.setText(order.getTitle());
        holder.orderTotal.setText(order.getTotal());
        holder.btnAction.setText(order.getActionText());

        // Set Status Badge Colors
        holder.statusCard.setCardBackgroundColor(holder.itemView.getContext().getResources().getColor(order.getStatusBgColor()));
        holder.orderStatus.setTextColor(holder.itemView.getContext().getResources().getColor(order.getStatusTextColor()));

        // Set Action Button Style
        holder.btnAction.setBackgroundTintList(ColorStateList.valueOf(holder.itemView.getContext().getResources().getColor(order.getActionBtnColor())));
        
        // Dynamic Icon based on action
        if (order.getActionText().contains("Lacak")) {
            holder.btnAction.setIconResource(android.R.drawable.ic_menu_send); // Replace with truck icon if available
        } else if (order.getActionText().contains("Beli")) {
            holder.btnAction.setIconResource(android.R.drawable.ic_menu_rotate);
        } else {
            holder.btnAction.setIcon(null);
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDate, orderStatus, orderTitle, orderTotal;
        MaterialButton btnAction;
        MaterialCardView statusCard;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            orderTitle = itemView.findViewById(R.id.orderTitle);
            orderTotal = itemView.findViewById(R.id.orderTotal);
            btnAction = itemView.findViewById(R.id.btnOrderAction);
            statusCard = itemView.findViewById(R.id.statusBadgeCard);
        }
    }
}