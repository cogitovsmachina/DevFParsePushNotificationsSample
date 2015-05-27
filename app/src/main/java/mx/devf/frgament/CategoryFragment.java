package mx.devf.frgament;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.devf.R;
import mx.devf.adapter.CategoryAdapter;
import mx.devf.app.ParsePushApplication;
import mx.devf.model.Category;
import mx.devf.parser.JsonDataParser;

/**
 * Created by hugo on 5/25/15.
 */
public class CategoryFragment extends Fragment {

    private RecyclerView my_recycler_view;
    private CategoryAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private String url ="https://sl-webplatform-engine-staging.appspot.com/api/home/getTimeline/";

    public CategoryFragment() {
    }

    public static CategoryFragment getInstance(){

        return new CategoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoty_layout, container, false);
        initView(view);
        makeSimpleRequest();
        jsonRequest();
        return view;
    }

    private void initView(View view) {
        my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        manager = new GridLayoutManager(getActivity(), 3);
        my_recycler_view.setLayoutManager(manager);

        ArrayList<Category> categories = new ArrayList<>();

        for(int index = 0; index < 20; index++){

            Category category = new Category();

            category.setCategoryId("123");
            category.setCategoryImage("");
            category.setCategoryName("Frutas");


            categories.add(category);
        }

        adapter = new CategoryAdapter(categories);

        my_recycler_view.setAdapter(adapter);
    }

    private void makeSimpleRequest() {
        // Request a string response from the provided URL.

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.wtf("STRING-REQUEST::", String.valueOf(jsonObject));
                String parseResponse = JsonDataParser.parserCategoriesJsonObject(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.wtf("STRING-REQUEST-ERROR::", String.valueOf(volleyError));
            }
        });

        // Add the request to the RequestQueue.
        ParsePushApplication.getInstance().addToRequestQueue(jsonObjectRequest, "getCategories");
    }

    private void jsonRequest(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.wtf("STRING-REQUEST::", String.valueOf(jsonObject));
                        String parseResponse = JsonDataParser.parserCategoriesJsonObject(jsonObject);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.wtf("STRING-REQUEST-ERROR::", String.valueOf(volleyError));
            }
        });

        // Add the request to the RequestQueue.
        ParsePushApplication.getInstance().addToRequestQueue(jsonObjectRequest, "getAlgo");
    }
}
