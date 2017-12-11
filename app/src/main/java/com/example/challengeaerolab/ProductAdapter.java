package com.example.challengeaerolab;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.challengeaerolab.model.Product;
import com.example.challengeaerolab.model.User;

import java.util.List;

/**
 * Created by Juan on 07/12/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    // Store a member variable for the contacts
    private List<Product> productList;
    // Store the context for easy access
    private Context mContext;

    private User user;

    private CatalogFragment fragmentContainer;

    public ProductAdapter(List<Product> productList, Context mContext, User user, Fragment fragmentContainer) {
        this.productList = productList;
        this.mContext = mContext;
        this.user = user;
        this.fragmentContainer = (CatalogFragment) fragmentContainer;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cell, parent, false);

        ProductViewHolder productViewHolder = new ProductViewHolder(itemView);
        return productViewHolder;
    }


    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        ProductViewHolder productViewHolder = (ProductViewHolder) holder;
        productViewHolder.bindProduct(product, mContext);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewProductName;
        private TextView textViewProductCategory;
        private TextView textViewProductNeededPoints;
        private LinearLayout linearLayoutNeededPoints;
        private ImageView imageViewProduct;
        private FloatingActionButton reedemProductButton;
        private View productView;
        private TextView TextViewProductCost;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewProductName = (TextView) itemView.findViewById(R.id.product_name);
            textViewProductCategory = (TextView) itemView.findViewById(R.id.product_category);
            textViewProductNeededPoints = (TextView) itemView.findViewById(R.id.points_text_view);
            linearLayoutNeededPoints = (LinearLayout) itemView.findViewById(R.id.points_needed_linear_layout);
            imageViewProduct = (ImageView) itemView.findViewById(R.id.product_image_view);
            reedemProductButton = (FloatingActionButton) itemView.findViewById((R.id.reedemButon));
            TextViewProductCost = (TextView) itemView.findViewById(R.id.product_cost);
            productView = itemView;


        }

        public void bindProduct(final Product product, final Context context){

            textViewProductName.setText(product.getName());
            textViewProductCategory.setText(product.getCategory());
            TextViewProductCost.setText(product.getCost().toString());

            if (user.getPoints() >= product.getCost()){
                reedemProductButton.setVisibility(View.VISIBLE);
                linearLayoutNeededPoints.setVisibility(View.GONE);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fragmentContainer.onProductAvailableClick(product);
                    }
                });
            }
            else{
                reedemProductButton.setVisibility(View.GONE);
                linearLayoutNeededPoints.setVisibility(View.VISIBLE);
                Integer neededPoints = product.getCost() - user.getPoints();
                textViewProductNeededPoints.setText(neededPoints.toString());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fragmentContainer.onProductNotAvailableClick(product);
                    }
                });
            }

            Glide.with(fragmentContainer).load(product.getImg().getUrl()).into(imageViewProduct);



        }
    }

}
