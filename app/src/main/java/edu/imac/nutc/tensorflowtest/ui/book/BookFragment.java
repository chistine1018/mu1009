package edu.imac.nutc.tensorflowtest.ui.book;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import edu.imac.nutc.tensorflowtest.R;
import info.hoang8f.widget.FButton;


import java.io.File;
import java.util.ArrayList;


/**
 * Created by user on 2018/7/12.
 */

public class BookFragment extends AppCompatActivity implements BookAdapter.ItemClickListener, View.OnClickListener {
    public static String TAG = BookFragment.class.getSimpleName();
    private BookAdapter adapter;

    public static BookFragment newInstance() {
        return new BookFragment();
    }

    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;
    private String bitmapPath;
    private ImageView backBk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_book);
        relativeLayout = findViewById(R.id.relativeLayout);
        ((FButton) findViewById(R.id.mammalsBtn)).setOnClickListener(this);
        ((FButton) findViewById(R.id.fishBtn)).setOnClickListener(this);
        ((FButton) findViewById(R.id.birdsBtn)).setOnClickListener(this);
        ((FButton) findViewById(R.id.amphibianBtn)).setOnClickListener(this);
        ((FButton) findViewById(R.id.otherBtn)).setOnClickListener(this);
        ((FButton) findViewById(R.id.reptileBtn)).setOnClickListener(this);
        backBk = findViewById(R.id.backBk);
        recyclerView = findViewById(R.id.book_recyclerView);
        bitmapPath = Environment.getExternalStorageDirectory().toString() + "/Pictures/Mammals";
        File directory = new File(bitmapPath);
        File[] imgAmount = directory.listFiles();
        ArrayList<String> imgAmountReg = new ArrayList<>();
        if (imgAmount != null) {
            for (int i = 0; i < imgAmount.length; i++) {
                imgAmountReg.add(i, "" + i);
            }
            recyclerView.setItemViewCacheSize(imgAmount.length * 2 - 15);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        adapter = new BookAdapter(this, imgAmountReg, imgAmount);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
    }

    private void setData(String name) {
        bitmapPath = Environment.getExternalStorageDirectory().toString() + "/Pictures/" + name;
        File directory = new File(bitmapPath);
        File[] imgAmount = directory.listFiles();
        ArrayList<String> imgAmountReg = new ArrayList<>();
        if (imgAmount != null) {
            for (int i = 0; i < imgAmount.length; i++) {
                imgAmountReg.add(i, "" + i);
            }
            recyclerView.setItemViewCacheSize(imgAmount.length * 2 - 15);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        adapter = new BookAdapter(this, imgAmountReg, imgAmount);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mammalsBtn: {
                setData("Mammals");
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.fbutton_color_alizarin));
                break;
            }
            case R.id.amphibianBtn: {
                setData("Amphibian");
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.fbutton_color_orange));

                break;
            }
            case R.id.birdsBtn: {
                setData("Bird");
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.fbutton_color_sun_flower));
                break;
            }
            case R.id.fishBtn: {
                setData("Fish");
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.fbutton_color_emerald));
                break;
            }
            case R.id.reptileBtn: {
                setData("Reptile");
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            }
            case R.id.otherBtn: {
                setData("Other");
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.fbutton_color_amethyst));
                break;
            }
            case R.id.backBk: {
                onBackPressed();
                break;
            }
        }
    }
}
