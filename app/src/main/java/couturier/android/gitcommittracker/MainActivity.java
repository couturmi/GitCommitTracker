package couturier.android.gitcommittracker;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import couturier.android.gitcommittracker.api.GitHubService;
import couturier.android.gitcommittracker.model.Commit;
import couturier.android.gitcommittracker.views.listadapters.CommitItemAdapter;

/**
 * Activity that will display the list of commits
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView commitRecyclerView;
    private SwipeRefreshLayout commitListRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get views
        this.commitRecyclerView = findViewById(R.id.commit_list);
        this.commitListRefresh = findViewById(R.id.commit_list_refresh);

        // set refresh listener for SwipeRefreshLayout
        this.commitListRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // clear RecyclerView
                commitRecyclerView.setAdapter(null);
                // reload new data
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
        new GitHubService().getCommits(new GitHubService.OnRequestCompleted<List<Commit>>() {
            @Override
            public void onCompleted(List<Commit> commitList) {
                // assign adapter to RecyclerView
                CommitItemAdapter itemAdapter = new CommitItemAdapter(commitList);
                commitRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                commitRecyclerView.setAdapter(itemAdapter);

                // stop refresh animation
                if(commitListRefresh.isRefreshing()) {
                    commitListRefresh.setRefreshing(false);
                }
            }
        });
    }
}
