package me.games.httpexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.games.httpexample.model.Category;


/**
 * Created by darshanz on 7/17/15.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    ArrayList<Category> mCategoryList;


    public CategoryAdapter(ArrayList<Category> categoryArrayList) {
        mCategoryList = categoryArrayList;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_item, null);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, int i) {

        categoryViewHolder.categoryName.setText(mCategoryList.get(i).getContent().getCategoryName());
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }



    class CategoryViewHolder extends  RecyclerView.ViewHolder{
        TextView categoryName;
        public CategoryViewHolder(View itemView) {
            super(itemView);

            categoryName = (TextView)itemView.findViewById(R.id.categoryName);
        }
    }
}
