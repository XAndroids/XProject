package workshop1024.com.xproject.controller.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import workshop1024.com.xproject.R;
import workshop1024.com.xproject.databinding.HomesublistItemContentBinding;
import workshop1024.com.xproject.model.subinfo.SubInfo;

//FIXME 这几个Adapter是什么关系
public class SubscribeListAdapter extends HomeSubListAdapter {
    private Context mContext;
    private SubInfoListMenuListener mSubInfoListMenuListener;
    private SubscribeItemViewHolder mSubscribeItemViewHolder;

    public SubscribeListAdapter(Context context, List<SubInfo> subInfoList, SubListItemListener
            subListItemListener, SubInfoListMenuListener subInfoListMenuListener) {
        super(subInfoList, subListItemListener);
        mContext = context;
        mSubInfoListMenuListener = subInfoListMenuListener;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(HomesublistItemContentBinding homesublistItemContentBinding) {
        homesublistItemContentBinding.setSubscribeHandlers(new SubscribeHandlers());
        mSubscribeItemViewHolder = new SubscribeItemViewHolder(homesublistItemContentBinding);
        return mSubscribeItemViewHolder;
    }

    public interface SubInfoListMenuListener {
        void onRenameMenuClick(SubInfo subscribe);

        void onUnscribeMenuClick(SubInfo subscribe);
    }

    public class SubscribeItemViewHolder extends ItemViewHolder implements PopupMenu.OnMenuItemClickListener {

        public SubscribeItemViewHolder(HomesublistItemContentBinding homesublistItemContentBinding) {
            super(homesublistItemContentBinding);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homepage_recyclerviewmenu_rename:
                    mSubInfoListMenuListener.onRenameMenuClick(mHomesublistItemContentBinding.getSubInfo());
                    return true;
                case R.id.homepage_recyclerviewmenu_unsubscribe:
                    mSubInfoListMenuListener.onUnscribeMenuClick(mHomesublistItemContentBinding.getSubInfo());
                    return true;
                default:
                    return false;
            }
        }
    }

    public class SubscribeHandlers {
        public boolean onLongClickItem(View view) {
            PopupMenu popupMenu = new PopupMenu(mContext, view);
            popupMenu.setOnMenuItemClickListener(mSubscribeItemViewHolder);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.homepage_recyclerview_menu, popupMenu.getMenu());
            popupMenu.show();

            return false;
        }
    }
}
