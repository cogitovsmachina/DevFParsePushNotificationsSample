package mx.devf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.parse.ParseAnalytics;

import mx.devf.frgament.CategoryFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Track app opens.
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        getFragmentManager().beginTransaction()
                .replace(R.id.home_container, CategoryFragment.getInstance())
                .commit();
    }


}
