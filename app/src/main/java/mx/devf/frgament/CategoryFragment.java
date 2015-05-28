package mx.devf.frgament;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import mx.devf.R;
import mx.devf.adapter.CategoryAdapter;
import mx.devf.app.ParsePushApplication;
import mx.devf.model.Category;
import mx.devf.model.Local;
import mx.devf.model.Precio;
import mx.devf.model.Product;
import mx.devf.parser.JsonDataParser;

/**
 * Created by hugo on 5/25/15.
 */
public class CategoryFragment extends Fragment {

    private RecyclerView my_recycler_view;
    private CategoryAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private String url = "https://sl-webplatform-engine-staging.appspot.com/api/home/getTimeline/";

    public CategoryFragment() {
    }

    public static CategoryFragment getInstance() {

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

    }

    private void makeSimpleRequest() {
        // Request a string response from the provided URL.

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.wtf("STRING-REQUEST::", String.valueOf(jsonObject));
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

    private void jsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ArrayList<Category> categories = null;
                        if (jsonObject != null) {
                            Log.wtf("STRING-REQUEST::", String.valueOf(jsonObject));
                            categories = JsonDataParser.parserCategoriesJsonObject(jsonObject);

                            adapter = new CategoryAdapter(categories);

                            my_recycler_view.setAdapter(adapter);
                        }
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

    private ArrayList<Product> createProduct(JSONArray jsonArray){
        ArrayList<Product> products = new ArrayList<>();
        try {

            for(int index = 0; index < jsonArray.length(); index++){
                Product product = new Product();
                ArrayList<Precio> precios = new ArrayList<>();
                product.setProductId(jsonArray.getJSONObject(index).getString("id"));
                product.setProductName(jsonArray.getJSONObject(index).getString("name"));
                for(int indexb = 0; indexb < jsonArray.getJSONObject(index).getJSONArray("precios").length(); indexb++){
                    Precio precio = new Precio();
                    precio.setPrecioSuper(jsonArray.getJSONObject(index).getJSONArray("precios").getJSONObject(indexb).getLong("precio"));
                    Local local = new Local();
                    local.setName(jsonArray.getJSONObject(index).getJSONArray("precios").getJSONObject(indexb).getJSONObject("local").getString("name"));
                    precio.setPrecioLocal(local);
                    precios.add(precio);
                }
                products.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }
}
