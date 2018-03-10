package com.ekosp.playstoresample;

/**
 * Created by Eko Setyo Purnomo on 09-Mar-18.
 * Find me on https://ekosp.com
 * Or email me directly to ekosetyopurnomo@gmail.com
 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<SectionDataModel> dataList;
    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private SnapHelper snapHelper;

    public RecyclerViewDataAdapter(ArrayList<SectionDataModel> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    /*@Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        ItemRowHolder rowHolder = new ItemRowHolder(v);
        snapHelper = new GravitySnapHelper(Gravity.START);
        return rowHolder;
    }*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        snapHelper = new GravitySnapHelper(Gravity.START);

        switch (viewType) {
            case SectionDataModel.NORMAL_TYPE:
                View v1 = inflater.inflate(R.layout.list_item, viewGroup, false);
                viewHolder = new ItemRowHolder(v1);
                break;
            case SectionDataModel.CATEGORY_TYPE:
                View v2 = inflater.inflate(R.layout.list_item, viewGroup, false);
                viewHolder = new ItemRowHolder2(v2);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                viewHolder = new ItemRowHolder(v);
                break;
        }
        return viewHolder;
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    @Override
    public int getItemViewType(int position) {
        switch (dataList.get(position).getSectionType()) {
            case 0:
                return SectionDataModel.NORMAL_TYPE;
            case 1:
                return SectionDataModel.CATEGORY_TYPE;
            case 2:
                return SectionDataModel.PROMO_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case SectionDataModel.NORMAL_TYPE:
                ItemRowHolder vh1 = (ItemRowHolder) viewHolder;
                configureItemHolder(vh1, position, SectionDataModel.NORMAL_TYPE);
                break;
            case SectionDataModel.CATEGORY_TYPE:
                ItemRowHolder2 vh2 = (ItemRowHolder2) viewHolder;
                configureItemHolder2(vh2, position, SectionDataModel.CATEGORY_TYPE);
                break;
            case SectionDataModel.PROMO_TYPE:
                ItemRowHolder vh = (ItemRowHolder) viewHolder;
                configureItemHolder(vh, position, SectionDataModel.PROMO_TYPE);
                break;
        }
    }

    private void configureItemHolder(ItemRowHolder holder, int position, int type) {
        final String sectionName = dataList.get(position).getHeaderTitle()
                +" NORMAL TYPE";
        ArrayList singleSectionItems = dataList.get(position).getAllItemInSection();
        holder.itemTitle.setText(sectionName);
        SectionListDataAdapter adapter = new SectionListDataAdapter(singleSectionItems, mContext, type);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);
        snapHelper.attachToRecyclerView(holder.recyclerView);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Button More Clicked!" + sectionName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureItemHolder2(ItemRowHolder2 holder, int position, int type) {
        final String sectionName = dataList.get(position).getHeaderTitle()
                +" CATEGORY TYPE";
        ArrayList singleSectionItems = dataList.get(position).getAllItemInSection();
        holder.itemTitle.setText(sectionName);
        SectionListDataAdapter adapter = new SectionListDataAdapter(singleSectionItems, mContext, type);
        holder.recyclerView.setHasFixedSize(true);

        GridLayoutManager mLayoutManager =
                new GridLayoutManager(mContext, 2, GridLayoutManager.HORIZONTAL, false);
       // LinearLayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
       holder.recyclerView.setLayoutManager(mLayoutManager);
       // holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, GridLayoutManager.HORIZONTAL, false));

        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);
        snapHelper.attachToRecyclerView(holder.recyclerView);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Button More Clicked!" + sectionName, Toast.LENGTH_SHORT).show();
            }
        });
    }

  /*  @Override
    public void onBindViewHolder(ItemRowHolder holder, int position) {
        final String sectionName = dataList.get(position).getHeaderTitle();
        ArrayList singleSectionItems = dataList.get(position).getAllItemInSection();
        holder.itemTitle.setText(sectionName);
        SectionListDataAdapter adapter = new SectionListDataAdapter(singleSectionItems, mContext);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);
        snapHelper.attachToRecyclerView(holder.recyclerView);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Button More Clicked!" + sectionName, Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        protected TextView itemTitle;
        protected RecyclerView recyclerView;
        protected Button btnMore;

        public ItemRowHolder(View itemView) {
            super(itemView);
            this.itemTitle = itemView.findViewById(R.id.itemTitle);
            this.recyclerView = itemView.findViewById(R.id.recycler_view_list);
            this.btnMore = itemView.findViewById(R.id.btnMore);
        }
    }

    public class ItemRowHolder2 extends RecyclerView.ViewHolder {
        protected TextView itemTitle;
        protected RecyclerView recyclerView;
        protected Button btnMore;

        public ItemRowHolder2(View itemView) {
            super(itemView);
            this.itemTitle = itemView.findViewById(R.id.itemTitle);
            this.recyclerView = itemView.findViewById(R.id.recycler_view_list);
            this.btnMore = itemView.findViewById(R.id.btnMore);
        }
    }
}