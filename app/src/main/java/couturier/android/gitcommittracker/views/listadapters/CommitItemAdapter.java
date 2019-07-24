package couturier.android.gitcommittracker.views.listadapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import couturier.android.gitcommittracker.R;
import couturier.android.gitcommittracker.model.Commit;

/**
 * RecyclerView Adapter for Commit items
 */
public class CommitItemAdapter extends RecyclerView.Adapter<CommitItemAdapter.ViewHolder> {
    private List<Commit> commitList;

    /**
     * @param commitList list of Commits to display
     */
    public CommitItemAdapter(final List<Commit> commitList) {
        this.commitList = commitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.commit_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // get corresponding Commit object
        final Commit commit = commitList.get(i);

        // set ViewHolder values
        viewHolder.commitAuthor.setText(commit.getAuthor());
        viewHolder.commitSha.setText(commit.getSha());
        viewHolder.commitMessage.setText(commit.getMessage());
    }

    @Override
    public int getItemCount() {
        return this.commitList.size();
    }

    /**
     * ViewHolder for a commit item
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView commitAuthor;
        TextView commitSha;
        TextView commitMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            commitAuthor = itemView.findViewById(R.id.commit_author_text);
            commitSha = itemView.findViewById(R.id.commit_sha_text);
            commitMessage = itemView.findViewById(R.id.commit_message_text);
        }
    }
}
