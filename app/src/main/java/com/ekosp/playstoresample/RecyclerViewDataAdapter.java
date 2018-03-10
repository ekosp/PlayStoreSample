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

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SectionDataModel> dataList;
    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private SnapHelper snapHelper;

    public RecyclerViewDataAdapter(ArrayList<SectionDataModel> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        snapHelper = new GravitySnapHelper(Gravity.START);

        switch (viewType) {
            case SectionDataModel.SLIDER_TYPE:
                View v3 = inflater.inflate(R.layout.section_row_item, viewGroup, false);
                viewHolder = new ItemSliderHolder(v3);
                break;
            case SectionDataModel.NORMAL_TYPE:
                View v1 = inflater.inflate(R.layout.section_row_item, viewGroup, false);
                viewHolder = new ItemRowHolder(v1);
                break;
            case SectionDataModel.CATEGORY_TYPE:
                View v2 = inflater.inflate(R.layout.section_row_item, viewGroup, false);
                viewHolder = new ItemRowHolder2(v2);
                break;
            case SectionDataModel.PROMO_TYPE:
                View v4 = inflater.inflate(R.layout.section_row_item, viewGroup, false);
                viewHolder = new ItemRowHolder(v4);
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
           case 3:
                return SectionDataModel.SLIDER_TYPE;
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
                configurePromoHolder(vh, position, SectionDataModel.PROMO_TYPE);
                break;
            case SectionDataModel.SLIDER_TYPE:
                ItemSliderHolder vh3 = (ItemSliderHolder) viewHolder;
                configureSliderHolder(vh3, position, SectionDataModel.PROMO_TYPE);
                break;
        }
    }

    private void configureItemHolder(ItemRowHolder holder, int position, int type) {
        final String sectionName = dataList.get(position).getHeaderTitle()
                + " NORMAL TYPE";
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

    private void configurePromoHolder(ItemRowHolder holder, int position, int type) {
        final String sectionName = dataList.get(position).getHeaderTitle()
                + " PROMO TYPE";
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
                + " CATEGORY TYPE";
        ArrayList singleSectionItems = dataList.get(position).getAllItemInSection();
        holder.itemTitle.setText(sectionName);
        SectionListDataAdapter adapter = new SectionListDataAdapter(singleSectionItems, mContext, type);
        holder.recyclerView.setHasFixedSize(true);

        GridLayoutManager mLayoutManager =
                new GridLayoutManager(mContext, 2, GridLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(mLayoutManager);

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

    private void configureSliderHolder(final ItemSliderHolder holder, int position, int type) {
        final String sectionName = dataList.get(position).getHeaderTitle()
                + " SLIDER TYPE";
      //  ArrayList singleSectionItems = dataList.get(position).getAllItemInSection();
        holder.itemTitle.setText(sectionName);
       /*final int[] currentPage = {0};
        final Integer[] XMEN= {R.drawable.aa,R.drawable.keima_katsuragi_chibi,
                R.drawable.aa,R.drawable.keima_katsuragi_chibi,R.drawable.aa};
        ArrayList<Integer> XMENArray = new ArrayList<Integer>();

        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        holder.mPager.setAdapter(new MyAdapter(mContext,XMENArray));
        holder.indicator.setViewPager(holder.mPager);
        holder.mPager.setCurrentItem(currentPage[0]++, true);
        */

        /*
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage[0] == XMEN.length) {
                    currentPage[0] = 0;
                }
                holder.mPager.setCurrentItem(currentPage[0]++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);*/
    }

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

    public class ItemSliderHolder extends RecyclerView.ViewHolder {
        protected TextView itemTitle;
        protected RecyclerView recyclerView;
        protected Button btnMore;

        public ItemSliderHolder(View itemView) {
            super(itemView);
            this.itemTitle = itemView.findViewById(R.id.itemTitle);
            this.btnMore = itemView.findViewById(R.id.btnMore);
            /* this.indicator = itemView.findViewById(R.id.indicator);
            this.mPager = itemView.findViewById(R.id.pager);*/

        }
    }
}