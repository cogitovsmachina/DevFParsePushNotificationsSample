package mx.devf.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import mx.devf.DetailActivity;
import mx.devf.DetailViewPagerActivity;
import mx.devf.R;
import mx.devf.model.Category;

/**
 * Created by hugo on 5/25/15.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private ArrayList<Category> categories;
    private Context context;
    private int comesFrom;

    public CategoryAdapter(ArrayList<Category> categories, Context context, int comesFrom) {
        this.categories = categories;
        this.context = context;
        this.comesFrom = comesFrom;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_category_layout, viewGroup, false);

        CategoryViewHolder vh = new CategoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, final int i) {
        categoryViewHolder.category_name_text.setText(String.valueOf(categories.get(i).getCategoryName()));
        categoryViewHolder.category_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("Click::", categories.get(i).getCategoryId());
//                context.startActivity(new Intent(context, DetailActivity.class));
//                comes From home activity
                if (comesFrom == 0) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("categoryId", categories.get(i).getCategoryId());
                    intent.putExtra("categoryName", categories.get(i).getCategoryName());
                    context.startActivity(intent);
//                  comes form detail
                } else {
                    Intent intent = new Intent(context, DetailViewPagerActivity.class);
                    intent.putExtra("categoryId", categories.get(i).getCategoryId());
                    intent.putExtra("categoryName", categories.get(i).getCategoryName());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView category_image;
         TextView  category_name_text;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            this.category_image = (ImageView) itemView.findViewById(R.id.category_image);
            this.category_name_text = (TextView) itemView.findViewById(R.id.category_name_text);
        }
    }
}
