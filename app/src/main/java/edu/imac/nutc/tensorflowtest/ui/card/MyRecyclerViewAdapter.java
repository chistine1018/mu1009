package edu.imac.nutc.tensorflowtest.ui.card;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import edu.imac.nutc.tensorflowtest.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by on 2018/7/12.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Integer[] imageResource = {R.drawable.red_card, R.drawable.orange_card, R.drawable.yellow_card,
            R.drawable.green_card, R.drawable.blue_card, R.drawable.indigo_card, R.drawable.purple_card};

    private int checkEvent;
    private int score;
    private int selectCard;
    private ArrayList<ImageView> ivNumberList;
    private ArrayList<Integer> selectList;
    private ArrayList<View> itemViewList;
    private Handler handler;
    private Bitmap[] bitmapsResource;
    private ArrayList<String> mImgRandomSelect;
    private Bitmap bmp;
    private String[] allBmpPath;

    //選取狀態
    private int[] mSelectRegister = new int[0];

    public MyRecyclerViewAdapter(Context context, String[] data, int[] selectRegister, ArrayList<String> imgRandomSelect) {
        handler = new Handler();
        selectList = new ArrayList<>();
        ivNumberList = new ArrayList<>();
        itemViewList = new ArrayList<>();
        mImgRandomSelect = new ArrayList<>();
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mSelectRegister = selectRegister;
        this.mImgRandomSelect = imgRandomSelect;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.myTextView.setText(mData[position]);
        if (mImgRandomSelect.size() >= 7 && mImgRandomSelect.size() != 0) {
            Bitmap bmp = BitmapFactory.decodeFile(mImgRandomSelect.get(Integer.parseInt(mData[position]) - 1));
            holder.mImageView.setImageBitmap(bmp);
        } else {
            holder.mImageView.setImageResource(imageResource[Integer.parseInt(mData[position]) - 1]);
        }
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView mImageView;
        ImageView cardView;

        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "rotationY", 0, 180);
        ObjectAnimator animatorBack = ObjectAnimator.ofFloat(itemView, "rotationY", 180, 360);

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.info_text);
            mImageView = itemView.findViewById(R.id.photo_item);
            cardView = itemView.findViewById(R.id.photo_stroke);
            //mImageView.setRotation(90);
            itemView.setOnClickListener(this);
        }

        //---------------------------------
        private void flipAnimation() {
            if (selectCard == 2) {
                animator.setDuration(750);
                animator.start();
                if (selectList.get(0).equals(selectList.get(1))) {
                    animatorBack.setDuration(750);
                    animatorBack.start();
                }
            } else {
                animator.setDuration(750);
                animator.start();
            }

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (selectCard < 2) {
                        startEvent();
                    }
                }
            }, 750);
            handler.postDelayed(new Runnable() {
                int position = getAdapterPosition();

                @Override
                public void run() {
                    switchCard(position);
                }
            }, 375);
        }

        private void switchCard(int pos) {
            int getSeclectRegister = mSelectRegister[pos];
            if (getSeclectRegister == 1) {
                mImageView.setVisibility(View.VISIBLE);
            } else {
                mImageView.setVisibility(View.INVISIBLE);
            }
            mSelectRegister[pos] = mSelectRegister[pos] * -1;
        }

        private void startEvent() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkEvent = 0;
                }
            }, 750);
        }

        private void selectView(int pos) {
            selectCard += 1;
            selectList.add(pos);
            itemViewList.add(itemView);
            ivNumberList.add(mImageView);
        }

        private void clearList() {
            selectList.clear();
            itemViewList.clear();
            ivNumberList.clear();
            selectCard = 0;

        }

        private void selectSamePosition() {
            clearList();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startEvent();
                }
            }, 750);
        }

        private void selectSameImage() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <= 1; i++) {
                        itemViewList.get(i).setVisibility(View.INVISIBLE);
                        itemViewList.get(i).setOnClickListener(null);
                    }
                    clearList();
                    startEvent();
                    score += 2;
                    if (score == mData.length) {
                        Log.i("TAG", "pass");
                        mClickListener.onItemClick(itemView, getAdapterPosition(), score);
                    }
                }
            }, 750);

        }

        private void selectDifferentImage() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <= 1; i++) {
                        ObjectAnimator resetAnimator = ObjectAnimator.ofFloat(itemViewList.get(i), "rotationY", 180, 0);
                        resetAnimator.setDuration(750);
                        resetAnimator.start();
                    }
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i <= 1; i++) {
                                ivNumberList.get(i).setVisibility(View.INVISIBLE);
                                mSelectRegister[selectList.get(i)] = 1;
                            }
                        }
                    }, 375);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clearList();
                            startEvent();
                        }
                    }, 751);
                }
            }, 750);
        }

        //---------------------------------
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (checkEvent == 0) {
                checkEvent = 1;
                selectView(position);
                flipAnimation();

                if (selectCard == 2) {
                    if (selectList.get(0).equals(selectList.get(1))) {
                        selectSamePosition();
                    } else if (mData[selectList.get(0)].equals(mData[selectList.get(1)])) {
                        selectSameImage();
                    } else {
                        selectDifferentImage();
                    }
                }
            }
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position, int score);
    }


}