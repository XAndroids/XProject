package workshop1024.com.xproject.controller.fragment.home;

import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.List;

import workshop1024.com.xproject.R;
import workshop1024.com.xproject.controller.adapter.SubscribeListAdapter;
import workshop1024.com.xproject.model.subinfo.SubInfo;
import workshop1024.com.xproject.view.dialog.InputStringDialog;

/**
 * 抽屉导航HomeFragment的子Frament-HomeFragment的ViewPager的子Fragment-SubscribeFragment，显示已订阅者发布者列表
 */
public class SubscribeFragment extends HomeSubFragment implements SubscribeListAdapter.SubInfoListMenuListener,
        InputStringDialog.InputStringDialogListener {
    private SubscribeListAdapter mSubscribeListAdapter;

    private SubInfo mRenameSubInfo;

    public static SubscribeFragment newInstance() {
        SubscribeFragment subscribeFragment = new SubscribeFragment();
        return subscribeFragment;
    }

    @Override
    public void onRefresh() {
        Log.i("XProject", "SubscribeFragment onRefresh");
        refreshSubscribedList();
    }

    @Override
    protected void loadData() {
        Log.i("XProject", "SubscribeFragment loadData");
        refreshSubscribedList();
    }


    private void refreshSubscribedList() {
        mSwipeRefreshLayout.setRefreshing(true);
        mSubInfoRepository.getSubscribeSubInfos(this);
    }

    @Override
    public void onSubInfosLoaded(List<SubInfo> subInfoList) {
        mSwipeRefreshLayout.setRefreshing(false);
        mSubscribeListAdapter = new SubscribeListAdapter(getContext(), subInfoList,
                this, this);
        mSubRecyclerView.setAdapter(mSubscribeListAdapter);
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void onInputStringDialogClick(DialogFragment dialog, String inputString) {
        mSubInfoRepository.reNameSubscribeSubInfoById(mRenameSubInfo.getInfoId(), inputString);
        refreshSubscribedList();
    }


    @Override
    public void onRenameMenuClick(SubInfo subscribe) {
        mRenameSubInfo = subscribe;
        InputStringDialog renamePublisherDialog = InputStringDialog.newInstance(R.string.rename_dialog_title, R.string
                .rename_dialog_positive);
        renamePublisherDialog.setInputStringDialogListener(this);
        renamePublisherDialog.show(getFragmentManager(), "renamePublisherDialog");
    }

    @Override
    public void onUnscribeMenuClick(SubInfo subscribe) {
        mSubInfoRepository.unSubscribeSubInfoById(subscribe.getInfoId());
        refreshSubscribedList();
    }

    @Override
    public void onSubListItemClick(SubInfo subInfo) {
        NewsListFragment newsListFragment = NewsListFragment.newInstance("Subscribe",subInfo.getInfoId());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainright_framelayout_fragments, newsListFragment)
                .addToBackStack("").commit();
        getActivity().setTitle(subInfo.getName());
    }
}
