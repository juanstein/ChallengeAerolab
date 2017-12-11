package com.example.challengeaerolab;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.challengeaerolab.R;
import com.example.challengeaerolab.controller.ProductController;
import com.example.challengeaerolab.controller.UserController;
import com.example.challengeaerolab.model.Product;
import com.example.challengeaerolab.model.User;
import com.example.challengeaerolab.util.ResultListener;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogFragment extends Fragment {

    List<Product> productList;
    RecyclerView recyclerView;
    ProductController productController;
    UserController userController;
    Main2Activity main2Activity;
    FloatingActionButton floatingActionButtonHighest;
    FloatingActionButton floatingActionButtonLowest;
    FloatingActionMenu floatingActionMenu;
    GridLayoutManager gridLayoutManager;
    public CatalogFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewProducts);
        productList = new ArrayList<>();

        final AVLoadingIndicatorView avi = (AVLoadingIndicatorView) view.findViewById(R.id.aviCatalog);

        productController = new ProductController(getContext());

        userController = new UserController(getContext());

        final Fragment me = this;

        gridLayoutManager = new GridLayoutManager(getContext(), 2);

        userController.getUser(new ResultListener<User>() {
            @Override
            public void finish(User resultado) {
                main2Activity.user = resultado;
                main2Activity.userPointsNavigation.setText(main2Activity.user.getPoints().toString());
                main2Activity.userNamenavigation.setText(main2Activity.user.getName());
                productController.getProductList(new ResultListener<List<Product>>() {
                    @Override
                    public void finish(List<Product> resultado) {
                        avi.hide();
                        productList = resultado;
                        main2Activity.productAdapter = new ProductAdapter(productList,getContext(), main2Activity.user, me);
                        recyclerView.setAdapter(main2Activity.productAdapter);

                        recyclerView.setLayoutManager(gridLayoutManager);
                    }
                });
            }
        });

        floatingActionButtonLowest = (FloatingActionButton) view.findViewById(R.id.catalog_frgment_fab_lowest);
        floatingActionButtonHighest = (FloatingActionButton) view.findViewById(R.id.catalog_frgment_fab_highest);
        floatingActionMenu = (FloatingActionMenu) view.findViewById(R.id.catalog_fragment_fab_menu);

        floatingActionButtonLowest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(productList);
                main2Activity.productAdapter.notifyDataSetChanged();
                floatingActionMenu.close(true);
                gridLayoutManager.scrollToPositionWithOffset(0,0);
            }
        });
        floatingActionButtonHighest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(productList);
                Collections.reverse(productList);
                main2Activity.productAdapter.notifyDataSetChanged();
                floatingActionMenu.close(true);
                gridLayoutManager.scrollToPositionWithOffset(0,0);
            }
        });



        return view;
    }

    public void onProductAvailableClick(final Product product){
        main2Activity.onProductAvailableClick(product);
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popup_reedem);
        dialog.show();
        ImageView imageViewPopup = (ImageView) dialog.findViewById(R.id.popup_reedem_image_view);
        TextView textViewPopupPoints = (TextView) dialog.findViewById(R.id.popup_reedem_points);
        TextView textViewProductName = (TextView) dialog.findViewById(R.id.popup_redeem_produc_name);
        Button buttonPopupReedem = (Button) dialog.findViewById(R.id.popup_reedem_button);
        Glide.with(this).load(product.getImg().getHdUrl()).into(imageViewPopup);
        textViewPopupPoints.setText(product.getCost().toString());
        textViewProductName.setText(product.getName());
        buttonPopupReedem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductController productController = new ProductController(getContext());

                final ProgressDialog progress = new ProgressDialog(getContext());
                progress.setMessage("Reedeming");
                progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                progress.show();

                productController.reedemAProduct(new ResultListener<String>() {
                    @Override
                    public void finish(String resultado) {
                        progress.dismiss();
                        Toast.makeText(getContext(), product.getName() + " reedemed!", Toast.LENGTH_SHORT).show();
                        main2Activity.user.setPoints(main2Activity.user.getPoints() - product.getCost());
                        main2Activity.userPointsNavigation.setText(main2Activity.user.getPoints().toString());
                        main2Activity.productAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }, product.get_id());
            }
        });
    }
    public void onProductNotAvailableClick(Product product){
        main2Activity.onProductNotAvailableClick(product);
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popup_not_available);
        dialog.show();
        ImageView imageViewPopup = (ImageView) dialog.findViewById(R.id.popup_noreedem_image_view);
        TextView textViewPopupPoints = (TextView) dialog.findViewById(R.id.popup_noreedem_points);
        TextView textViewProductName = (TextView) dialog.findViewById(R.id.popup_noredeem_produc_name);
        TextView textViewNeededPoints = (TextView) dialog.findViewById(R.id.popup_noreedem_points_needed);
        Glide.with(this).load(product.getImg().getHdUrl()).into(imageViewPopup);
        textViewPopupPoints.setText(product.getCost().toString());
        Integer pointsNeeded = (product.getCost() - main2Activity.user.getPoints());
        textViewNeededPoints.setText("YOU NEEED " + pointsNeeded.toString());
        textViewProductName.setText(product.getName());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        main2Activity = (Main2Activity) activity;
    }
}
