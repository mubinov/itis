// http://vk.com/dev/
// https://code.google.com/p/google-gson/

import com.google.gson.*;
import java.util.*;

public class VK {

    public static void main(String[] args) {
        Get get = new Get();
        String response = get.executeGet("https://api.vk.com/method/wall.get?owner_id=-59311888");

        System.out.println(response.replaceAll(",", ",\n"));

        Gson gson = new Gson();
        JsonObject json = gson.fromJson(response, JsonObject.class);
        JsonObject post;
        for(JsonElement o:json.getAsJsonArray("response")){
            if(o.isJsonObject()){
                post = o.getAsJsonObject();
                System.out.println("ID: " + post.get("id") + " / " + new Date(post.get("date").getAsLong()) + "> " + post.get("text").getAsString());
            }

        }
    }
}
