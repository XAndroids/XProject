package workshop1024.com.xproject.controller.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ListView;

import java.util.List;

import workshop1024.com.xproject.R;
import workshop1024.com.xproject.controller.fragment.settings.SettingsFragment;
import workshop1024.com.xproject.utils.UnitUtils;

public class SettingsActivity extends AppCompatPreferenceActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ListView headerListView = getListView();
        headerListView.setDivider(getDrawable(R.drawable.setting_headerlist_itemdivider));
        headerListView.setDividerHeight(UnitUtils.spToPx(this, 1));
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.settings_preferences, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SettingsFragment.class.getName().equals(fragmentName);
    }
}