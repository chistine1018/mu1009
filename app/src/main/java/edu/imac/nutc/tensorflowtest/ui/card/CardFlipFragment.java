package edu.imac.nutc.tensorflowtest.ui.card;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.imac.nutc.tensorflowtest.R;


import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * Created by user on 2018/7/12.
 */

public class CardFlipFragment extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    private MyRecyclerViewAdapter adapter;
    private TextView passTextView;

    private ArrayList<String> randomList;
    private ArrayList<String> reList;
    private ArrayList<String> randomImgList;
    private int r1;
    private int r2;
    private int RandomSize;

    private int imgStatus;
    private ArrayList<Integer> imgSum;
    private ArrayList<Integer> imgSave;
    private int imgRandom;

    private ArrayList<String> setBitmapPath;
    private String bitmapPath;
    private ArrayList<String> selectBmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardflip);
        passTextView = findViewById(R.id.pass_tv);
        randomList = new ArrayList<>();
        reList = new ArrayList<>();
        randomImgList = new ArrayList<>();
        Random random = new Random();
        r1 = random.nextInt(8 - 2) + 2;//1~7

        //產生亂數表元素
        int reItemSize = 2; //圖片重複數
        for (int i = 1; i <= r1; i++) {
            for (int j = 1; j <= reItemSize; j++) {
                randomList.add(String.valueOf(i));
            }
        }

        //產生隨機亂數表_data
        RandomSize = randomList.size();
        for (int i = 1; i <= RandomSize; i++) {
            r2 = random.nextInt(randomList.size());
            reList.add(String.valueOf(randomList.get(r2)));
            randomList.remove(r2);
        }
        Object list[] = reList.toArray();
        String[] data = Arrays.copyOf(list, list.length, String[].class);

        //產生選擇狀態表_selectList
        int[] selectRegister = new int[RandomSize];
        for (int i = 0; i <= RandomSize - 1; i++) {
            selectRegister[i] = 1;
        }

        //imgSelect
        selcetImgType();
        RecyclerView recyclerView = findViewById(R.id.my_recycler);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new MyRecyclerViewAdapter(this, data, selectRegister, selectBmp);
        adapter.setClickListener(this);
        recyclerView.setItemViewCacheSize(r1 * reItemSize - 15);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position, int score) {
        passTextView.setVisibility(View.VISIBLE);
    }

    private void selcetImgType() {
        Random random = new Random();
        setBitmapPath = new ArrayList<>();
        selectBmp = new ArrayList<>();
        String[] imgType = {"Mammals", "Amphibian", "Bird", "Fish", "Reptile", "Other"};
        for (int i = 0; i <= 5; i++) {
            bitmapPath = Environment.getExternalStorageDirectory().toString() + "/Pictures/" + imgType[i];
            File directory = new File(bitmapPath);
            File[] imgAmount = directory.listFiles();
            if (imgAmount != null) {
                for (int j = 0; j < imgAmount.length; j++) {
                    setBitmapPath.add(imgAmount[j].getPath());
                }
            }
            Log.e("test", "" + bitmapPath);
            Log.e("test", "" + setBitmapPath);
        }

        for (int i = 0; i < 7; i++) {
            int t = random.nextInt(setBitmapPath.size());
            selectBmp.add(setBitmapPath.get(t));
            setBitmapPath.remove(t);
            Log.e("test", "" + selectBmp.get(i));
        }

    }
}
