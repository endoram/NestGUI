
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public class NestSR {

    final String oauthUrl = "https://api.home.nest.com/oauth2/access_token";
    final String devicesUrl = "https://developer-api.nest.com";

    final String clientId = "85b1b461-5491-4ba2-ba7f-ae498c9e98ec";
    final String clientSecret = "UKsDyW8ZSvlcCGp0XkoJexJkM";

    public NestSR(String code) {
        // make POST request to oauth link, with ?code&client_id&client_secret&grant_type, getting auth key
        // make GET request to devices url, with header Authorization of "Bearer " + auth key from POST request
    }

}
