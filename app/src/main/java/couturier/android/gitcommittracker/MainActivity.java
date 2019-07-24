package couturier.android.gitcommittracker;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

/**
 * Activity that will display the list of commits
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView commitList;
    private SwipeRefreshLayout commitListRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get views
        this.commitList = findViewById(R.id.commit_list);
        this.commitListRefresh = findViewById(R.id.commit_list_refresh);

        // set refresh listener for SwipeRefreshLayout
        this.commitListRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadCommitData();
            }
        });

        // initial data load
        reloadCommitData();
    }

    /**
     * Reload commit data into the RecyclerView
     */
    private void reloadCommitData() {

    }
}
