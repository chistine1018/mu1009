package edu.imac.nutc.tensorflowtest.ui.book;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.imac.nutc.tensorflowtest.R;


import java.io.File;
import java.util.ArrayList;


/**
 * Created by on 2018/7/12.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private String[] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<String> imgPath;
    private Bitmap bmp;
    private File[] imgAmount;
    // data is passed into the constructor
    BookAdapter(Context context, ArrayList<String> data, File[] imgAmount) {
        this.mInflater = LayoutInflater.from(context);
        this.imgPath = data;
        this.imgAmount = imgAmount;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.book_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myTextView.setText(imgPath.get(position));
        bmp = BitmapFactory.decodeFile(imgAmount[position].getPath());
        Log.d("imgph",""+imgAmount[position].getPath());
        if (imgAmount.length>0) {
            holder.imageView.setImageBitmap(bmp);
        }else{
            holder.imageView.setImageResource(R.drawable.nofound);
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return imgPath.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.info_text);
            imageView = itemView.findViewById(R.id.book_item);
            imageView.setRotation(90);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return imgPath.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}