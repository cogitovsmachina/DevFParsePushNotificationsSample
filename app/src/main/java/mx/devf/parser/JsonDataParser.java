package mx.devf.parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.devf.model.Category;

/**
 * Created by hugo on 5/26/15.
 */
public class JsonDataParser {

    public static ArrayList<Category> parserCategoriesJsonObject(JSONObject jsonObject){
        ArrayList<Category> categories = new ArrayList<>();

//        try {
//            for(int i = 0; i < jsonObject.getJSONArray("categories").length(); i++){
//                Category category = new Category();
//
//                category.setCategoryId(jsonObject.getJSONArray("categories").getJSONObject(i).getString("id"));
//                category.setCategoryImage(jsonObject.getJSONArray("categories").getJSONObject(i).getString("image"));
//                category.setCategoryName(jsonObject.getJSONArray("categories").getJSONObject(i).getString("name"));
//
//
//                categories.add(category);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        for (int index = 0; index < 20; index++) {

            Category category = new Category();

            category.setCategoryId("123");
            category.setCategoryImage("");
            category.setCategoryName("Frutas");


            categories.add(category);
        }

        return categories;

    }

    public static String parserCategoryProducts(JSONObject jsonObjectProducts){
        String algo = "";
        return algo;
    }


}
