package mx.devf;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.parse.ParseAnalytics;

import mx.devf.frgament.CategoryFragment;

public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Track app opens.
        ParseAnalytics.trackAppOpened(getIntent());

        getFragmentManager().beginTransaction()
                .replace(R.id.home_container, CategoryFragment.getInstance())
                .commit();
    }
}
