package mx.devf.frgament;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
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
    private int comesFrom = -1;

    public CategoryFragment() {
    }

    public static CategoryFragment getInstance(int comesFrom) {
        CategoryFragment mCategoryFragment = new CategoryFragment();

        Bundle args = new Bundle();
        args.putInt("from", comesFrom);
        mCategoryFragment.setArguments(args);

        return mCategoryFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            comesFrom = getArguments().getInt("from");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoty_layout, container, false);
        initView(view);
        jsonArrayRequest();
        return view;
    }

    private void initView(View view) {
        my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        manager = new GridLayoutManager(getActivity(), 3);
        my_recycler_view.setLayoutManager(manager);

    }

    private void jsonArrayRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://profecoapi.tk/categories/?format=json", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.wtf("ArrayREquestResult:::", String.valueOf(jsonArray));
                if(jsonArray != null){
                    ArrayList<Category> cat = JsonDataParser.parserCategoryProducts(jsonArray);
                    Log.wtf("Que hay??", String.valueOf(cat.get(0).getCategoryName()));
                    adapter = new CategoryAdapter(cat, getActivity(), comesFrom);

                    my_recycler_view.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        ParsePushApplication.getInstance().addToRequestQueue(jsonArrayRequest, "jsonArrayRequest");
    }
}
