package com.example.a19526811_tranvannhan_thithuchanh.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a19526811_tranvannhan_thithuchanh.HomeActivity;
import com.example.a19526811_tranvannhan_thithuchanh.R;
import com.example.a19526811_tranvannhan_thithuchanh.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {

    private HomeActivity context;
    private List<Book> lst;
    private int selected;
    private View viewSellected;

    public BookRecyclerAdapter(HomeActivity context, List<Book> lst){
        this.context = context;
        this.lst = lst;
    }

    public BookRecyclerAdapter(HomeActivity context){
        this.context = context;
        this.lst = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_book, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookRecyclerAdapter.ViewHolder holder, int position) {
        holder.bookname.setText(lst.get(position).getName());
        Log.d("onBindViewHolder", lst.get(position).toString());

        holder.itemView.setOnClickListener(v -> {

            EditText edtBook = context.findViewById(R.id.edt_book);

            if(selected == position){
                holder.itemView.setBackgroundColor(0);
                selected = -1;
                viewSellected = null;
                edtBook.setText("");
                return;
            }

            if(viewSellected != null){
                viewSellected.setBackgroundColor(0);
            }

            Resources res = context.getResources();
            String packageName = context.getPackageName();

            int colorId = res.getIdentifier("sellect", "color", packageName);
            int desiredColor = res.getColor(colorId);
            holder.itemView.setBackgroundColor(colorId);
            edtBook.setText(lst.get(position).getName());

            selected = position;
            viewSellected = holder.itemView;
        });

        holder.btnDelete.setOnClickListener(v -> {
            if(viewSellected != null){
                viewSellected.setBackgroundColor(0);
            }

            selected = -1;
            viewSellected = null;
            context.delete(lst.get(position).getKey());
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView bookname;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookname = itemView.findViewById(R.id.book_name);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    public List<Book> getLst() {
        return lst;
    }

    public void setLst(List<Book> lst) {
        this.lst = lst;
        notifyDataSetChanged();
    }

    public String getKey(){
        if(selected == -1)
                return "";

        return lst.get(selected).getKey();
    }
}
