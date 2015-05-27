package mx.devf.parser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hugo on 5/26/15.
 */
public class JsonDataParser {

    public static String parserCategoriesJsonObject(JSONObject jsonObject){
        String re = "";
        try {
            re = jsonObject.getString("last_epoch");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return re;
    }

    public static String parserCategoryProducts(JSONObject jsonObjectProducts){
        String algo = "";
        return algo;
    }


}
