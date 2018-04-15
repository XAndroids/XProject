package workshop1024.com.xproject.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import workshop1024.com.xproject.R;
import workshop1024.com.xproject.model.sub.SubInfo;
import workshop1024.com.xproject.model.sub.tag.Tag;
import workshop1024.com.xproject.view.GrassView;

/**
 * 抽屉导航HomeFragment的子Frament-HomeFragment的ViewPager的子Fragment-HomeSubFragment中列表适配器，处理公共的视图渲染逻辑
 */
public abstract class HomeSubListAdapter extends RecyclerView.Adapter {
    //列表项空视图类型
    static final int VIEW_TYPE_EMPTY = 0;
    //列表项内容视图类型
    static final int VIEW_TYPE_ITEM = 1;

    private SubListItemListener mSubListItemListener;

    public HomeSubListAdapter(SubListItemListener subListItemListener) {
        mSubListItemListener = subListItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == VIEW_TYPE_EMPTY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homesublist_item_empty,
                    parent, false);
            viewHolder = new EmptyViewHolder(view);
        }

        return viewHolder;
    }

    public interface SubListItemListener {
        void onSubListItemClick(SubInfo subInfo);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mNameTextView;
        TextView mNewsCountTextView;
        SubInfo mSubInfo;

        public ItemViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            mNameTextView = itemView.findViewById(R.id.homesublist_item_textview_name);
            mNewsCountTextView = itemView.findViewById(R.id.homesublist_item_textview_newscount);
        }

        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        public void onClick(View v) {
            if(mSubListItemListener != null){
                mSubListItemListener.onSubListItemClick(mSubInfo);
            }
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        private GrassView mGrassView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            mGrassView = itemView.findViewById(R.id.homesublist_grassview);
        }
    }
}