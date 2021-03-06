package com.ekosp.playstoresample;

/**
 * Created by Eko Setyo Purnomo on 09-Mar-18.
 * Find me on https://ekosp.com
 * Or email me directly to ekosetyopurnomo@gmail.com
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder>{

    private ArrayList<SingleItemModel> itemModels;
    private Context mContext;
    private int sectionType;

    public SectionListDataAdapter(ArrayList<SingleItemModel> itemModels, Context mContext, int sectionType) {
        this.itemModels = itemModels;
        this.mContext = mContext;
        this.sectionType = sectionType;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        switch (sectionType){
            case SectionDataModel.NORMAL_TYPE :
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card, null);
                break;
            case SectionDataModel.CATEGORY_TYPE :
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_category_card, null);
                break;
            case SectionDataModel.PROMO_TYPE :
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_promo_card, null);
                break;
            case SectionDataModel.SLIDER_TYPE :
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_slider_card, null);
                break;
        }

        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int position) {
        SingleItemModel itemModel = itemModels.get(position);
        holder.tvTitle.setText(itemModel.getName());
    }

    @Override
    public int getItemCount() {
        return (null != itemModels ? itemModels.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;
        protected ImageView itemImage;

        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.itemImage = itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
