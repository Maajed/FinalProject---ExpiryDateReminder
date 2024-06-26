//package com.example.finalproject;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class MyAdapter extends RecyclerView.RecyclerAdapter<MyViewHolder> {
//
//    private Context context;
//    private List<DataClass> dataList;
//
//    public MyAdapter(Context context, List<DataClass> dataList) {
//        this.context = context;
//        this.dataList = dataList;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
//        holder.recTitle.setText(dataList.get(position).getDataProduct());
//        holder.recDesc.setText(dataList.get(position).getDataDesc());
//        holder.recDate.setText(dataList.get(position).getExpiryDate());
//
//        holder.recCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, FoodUpload.class);
//                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
//                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDesc());
//                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataProduct());
////                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()));
//                intent.putExtra("Expiry", dataList.get(holder.getAdapterPosition()).getExpiryDate());
//                context.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//
//}
//
//class MyViewHolder extends RecyclerView.ViewHolder{
//
//    ImageView recImage;
//    TextView recTitle, recDesc, recDate;
//    CardView recCard;
//
//    public MyViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        recImage = itemView.findViewById(R.id.recImage);
//        recCard = itemView.findViewById(R.id.recCard);
//        recDesc = itemView.findViewById(R.id.recDesc);
//        recDate = itemView.findViewById(R.id.recDate);
//        recTitle = itemView.findViewById(R.id.recProduct);
//    }
//}