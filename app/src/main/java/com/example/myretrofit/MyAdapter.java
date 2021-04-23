package com.example.myretrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Symbol> localDataSet;


    public static class ViewHolder extends RecyclerView.ViewHolder {
       public final TextView tv1, tv2, tv3;

        public ViewHolder(View view) {
            super(view);
            tv1 = view.findViewById(R.id.textView);
            tv2 = view.findViewById(R.id.textView2);
            tv3 = view.findViewById(R.id.textView3);
        }

    }

    public MyAdapter(List<Symbol> dataSet) {
        localDataSet = dataSet;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_simple_list, viewGroup, false);

        return new ViewHolder(view);
    }

    public void updateStocks(List<Symbol> dataSet){
        this.localDataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.tv1.setText(localDataSet.get(position).type);
        viewHolder.tv2.setText(localDataSet.get(position).figi);
        viewHolder.tv3.setText(String.valueOf(localDataSet.get(position).price));

    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}