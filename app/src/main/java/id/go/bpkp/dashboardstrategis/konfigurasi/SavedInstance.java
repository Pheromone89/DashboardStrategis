package id.go.bpkp.dashboardstrategis.konfigurasi;

import org.json.JSONException;
import org.json.JSONObject;

public class SavedInstance {

    public static String nipLama = "";
    public static String userToken = "";

    public static void saveData(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getString("success").equals("true")){
                nipLama = jsonObject.getJSONObject("message").getString("user_nip");
                userToken = jsonObject.getString("api_token");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
