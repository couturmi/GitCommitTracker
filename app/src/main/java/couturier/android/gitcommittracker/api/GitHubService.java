package couturier.android.gitcommittracker.api;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import couturier.android.gitcommittracker.model.Commit;
import couturier.android.gitcommittracker.model.deserializer.CommitDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Service for obtaining data via the GitHub API
 */
public class GitHubService {
    private static final String BASE_URL = "https://api.github.com";
    private static final String COMMIT_LIST_REQUEST = "/repos/couturmi/GitCommitTracker/commits";

    /**
     * Get recent commits from the GitCommitTracker repo
     * @param onCompleted determines functionality on UI thread once execution is completed
     */
    public void getCommits(final OnRequestCompleted<List<Commit>> onCompleted) {
        // create url for commits list request
        final String url = createRequestUrl(COMMIT_LIST_REQUEST);
        // start AsyncTask to execute request
        new GetRequestTask(url, new OnRequestCompleted<String>() {
            @Override
            public void onCompleted(String result) {
                // parse response to object
                final Gson gson = new GsonBuilder().registerTypeAdapter(Commit.class, new CommitDeserializer()).create();
                Type listType = new TypeToken<List<Commit>>(){}.getType();
                List<Commit> commitList = gson.fromJson(result, listType);

                // callback to UI with list result
                onCompleted.onCompleted(commitList);
            }
        }).execute();
    }

    /**
     * Creates the full URL for the specified request
     * @param request specific GitHub request
     * @return full URL
     */
    private static String createRequestUrl(final String request) {
        return String.format("%s%s", BASE_URL, request);
    }

    /**
     * AsyncTask for executing GitHub API GET requests
     */
    private static class GetRequestTask extends AsyncTask<Object, Void, String> {
        final String requestUrl;
        final OnRequestCompleted onCompleted;

        GetRequestTask(@NonNull String requestUrl, @NonNull OnRequestCompleted onCompleted) {
            this.requestUrl = requestUrl;
            this.onCompleted = onCompleted;
        }

        @Override
        protected String doInBackground(Object... objects) {
            // set up http client and request
            OkHttpClient httpClient = new OkHttpClient();
            Request request = new Request.Builder().get().url(requestUrl).build();

            // execute request
            String responseBody = "";
            try {
                Response response = httpClient.newCall(request).execute();
                if(response.body() != null) {
                    responseBody = response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // return response string
            return responseBody;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // execute functionality from main thread
            onCompleted.onCompleted(result);
        }
    }

    /**
     * Interface to specify functionality on the main thread when the request is completed
     */
    public interface OnRequestCompleted<T> {
        void onCompleted(T result);
    }
}
