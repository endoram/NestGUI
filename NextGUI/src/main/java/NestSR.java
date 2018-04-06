
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

    TempSystem ts;
    String tempSystemStr;
    String accessToken;

    private String getAccessToken(String code) {
        HttpResponse<JsonNode> res = null;
        try {
            res = Unirest.post("https://api.home.nest.com/oauth2/access_token")
                .queryString("code", code)
                .queryString("client_id", clientId)
                .queryString("client_secret", clientSecret)
                .queryString("grant_type", "authorization_code")
                .asJson();
        } catch (Throwable err) {
            System.out.println("Error with Unirest: " + err);
            return null;
        }
        JsonNode node = res.getBody();
        // access_token
        JSONObject json = node.getObject();
        String accessToken = null;
        try {
            accessToken = json.getString("access_token");
        } catch (Throwable err) {
            System.out.println("Failed to get access token.  Incorrect code");
            return null;
        }
        return accessToken;
    }

    public void setTarget(String key, double temp) {
        try {
            HttpResponse<JsonNode> res = Unirest.put(devicesUrl + "/devices/thermostats/" + key)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body("{\"target_temperature_f" + "\": " + temp + "}")
                .asJson();
            System.out.println(res.getBody().getObject());
        } catch (Throwable err) {
            System.out.println("Error changing temperature: " + temp);
        }
    }

    public NestInformation getNestInfo() {
        // TODO: implement getting thermostat code
        HttpResponse<JsonNode> res = null;
        try {
            res = Unirest.get(devicesUrl)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .asJson();
        } catch (Throwable err) {
            System.out.println("Error getting devices: " + err);
            return null;
        }

        JsonNode node = res.getBody();
        JSONObject json = node.getObject();

        try {
            JSONObject devices = json.getJSONObject("devices");
            JSONObject thermostats = devices.getJSONObject("thermostats");
            String key = thermostats.names().getString(0);
            JSONObject thermostat = thermostats.getJSONObject(key);
            String scaleStr = "";
            switch(ts) {
                case C: {
                    scaleStr = "c";
                    break;
                }
                case F: {
                    scaleStr = "f";
                    break;
                }
                case AUTO: {
                    scaleStr = thermostat.getString("temperature_scale").toLowerCase();
                    break;
                }
            }
            String name = thermostat.getString("name");
            double ambientTemp = thermostat.getDouble("ambient_temperature_" + scaleStr);
            double targetTemp = thermostat.getDouble("target_temperature_" + scaleStr);
            tempSystemStr = scaleStr;
            return new NestInformation(key, name, ambientTemp, targetTemp, ts, scaleStr);
        } catch (Throwable err) {
            System.out.println("Error with devices: " + err);
            return null;
        }

    }

    public NestSR(String code, TempSystem ts) {
        this.ts = ts;
        // make POST request to oauth link, with ?code&client_id&client_secret&grant_type, getting auth key
        accessToken = getAccessToken(code);
        if (accessToken == null) {
            System.out.println("Debug: access token is null.  Exiting.");
            return;
        }
        System.out.println("Debug: access token = " + accessToken);
    }

}
