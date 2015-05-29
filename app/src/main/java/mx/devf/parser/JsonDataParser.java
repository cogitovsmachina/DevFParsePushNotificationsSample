package mx.devf.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.devf.model.Category;
import mx.devf.model.Local;
import mx.devf.model.Precio;
import mx.devf.model.Product;

/**
 * Created by hugo on 5/26/15.
 */
public class JsonDataParser {

    public static ArrayList<Category> parserCategoryProducts(JSONArray jsonArrayCategories){
        ArrayList<Category> categories = null;
        try {
            categories = new ArrayList<>();
            for(int i = 0; i < jsonArrayCategories.length(); i++){
                Category category = new Category();
                JSONObject cateryObject = jsonArrayCategories.getJSONObject(i);
                category.setCategoryId(cateryObject.getString("id"));
                category.setCategoryName(cateryObject.getString("categoria"));
                category.setCategoryImage(cateryObject.getString("imagen"));

                categories.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return categories;
    }
}
