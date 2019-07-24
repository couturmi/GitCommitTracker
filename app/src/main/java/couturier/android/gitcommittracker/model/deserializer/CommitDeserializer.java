package couturier.android.gitcommittracker.model.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import couturier.android.gitcommittracker.model.Commit;

/**
 * Custom response deserializer for commit objects
 */
public class CommitDeserializer implements JsonDeserializer<Commit> {
    @Override
    public Commit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // create Commit object
        Commit commit = new Commit();
        JsonObject objectRoot = json.getAsJsonObject();

        // get SHA value
        String sha = objectRoot.get("sha").getAsString();
        commit.setSha(sha);
        // get author value
        String author = objectRoot.getAsJsonObject("commit").getAsJsonObject("author").get("name").getAsString();
        commit.setAuthor(author);
        // get message value
        String message = objectRoot.getAsJsonObject("commit").get("message").getAsString();
        commit.setMessage(message);

        // return commit object
        return commit;
    }
}
