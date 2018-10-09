package edu.imac.nutc.tensorflowtest.ui.book;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.imac.nutc.tensorflowtest.R;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * Created by user on 2018/7/12.
 */

public class BookFragment extends AppCompatActivity implements BookAdapter.ItemClickListener {
    public static String TAG = BookFragment.class.getSimpleName();
    private BookAdapter adapter;

    public static BookFragment newInstance() {
        return new BookFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_book);

        RecyclerView recyclerView = findViewById(R.id.book_recyclerView);
        String bitmapPath = Environment.getExternalStorageDirectory().toString() + "/Pictures/BitmapReg";
        File directory = new File(bitmapPath);
        File[] imgAmount = directory.listFiles();
        ArrayList<String> imgAmountReg = new ArrayList<>();
        for (int i = 0; i < imgAmount.length; i++) {
            imgAmountReg.add(i,""+i);
        }
        recyclerView.setItemViewCacheSize(imgAmount.length * 2 - 15);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new BookAdapter(this, imgAmountReg);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
    }

}
