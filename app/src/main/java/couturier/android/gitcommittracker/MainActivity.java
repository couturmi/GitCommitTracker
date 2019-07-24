package couturier.android.gitcommittracker;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

/**
 * Activity that will display the list of commits
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get views
        RecyclerView commitList = findViewById(R.id.commit_list);
        SwipeRefreshLayout commitListRefresh = findViewById(R.id.commit_list_refresh);

    }
}
