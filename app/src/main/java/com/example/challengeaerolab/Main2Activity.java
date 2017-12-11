package com.example.challengeaerolab;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.challengeaerolab.controller.ProductController;
import com.example.challengeaerolab.controller.UserController;
import com.example.challengeaerolab.model.Product;
import com.example.challengeaerolab.model.User;
import com.example.challengeaerolab.util.ResultListener;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        TextView userNamenavigation;
        TextView userPointsNavigation;
        ProductAdapter productAdapter;
        User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Electronics");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        userNamenavigation = (TextView) header.findViewById(R.id.user_name);
        userPointsNavigation = (TextView) header.findViewById(R.id.user_points);
        Fragment nuevoFragment = new CatalogFragment();
        FragmentManager unFragmentManager = getSupportFragmentManager();
        FragmentTransaction unFragmentTransaction = unFragmentManager.beginTransaction();
        unFragmentTransaction.replace(R.id.fragmentContainerHomeReceptionActivity, nuevoFragment, "Recepcion");
        unFragmentTransaction.commit();
        navigationView.setCheckedItem(R.id.nav_catalog);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        UserController userController = new UserController(getApplicationContext());

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_post_1000) {
            userController.postPoints(new ResultListener<String>() {
                @Override
                public void finish(String resultado) {
                    user.setPoints(user.getPoints() + 1000);
                    userPointsNavigation.setText(user.getPoints().toString());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                }
            },"1000");
            return true;
        }
        else if(id == R.id.action_post_5000){
            userController.postPoints(new ResultListener<String>() {
                @Override
                public void finish(String resultado) {
                    user.setPoints(user.getPoints() + 5000);
                    userPointsNavigation.setText(user.getPoints().toString());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                }
            },"5000");
            return true;
        }
        else if(id == R.id.action_post_7500){
            userController.postPoints(new ResultListener<String>() {
                @Override
                public void finish(String resultado) {
                    user.setPoints(user.getPoints() + 7500);
                    userPointsNavigation.setText(user.getPoints().toString());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                }
            },"7500");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment nuevoFragment = null;
        FragmentManager unFragmentManager = getSupportFragmentManager();
        FragmentTransaction unFragmentTransaction = unFragmentManager.beginTransaction();
        String fragmentName = null;

        int id = item.getItemId();

        if (id == R.id.nav_catalog) {
            nuevoFragment = new CatalogFragment();
            fragmentName = "Catalogo";
        }/* else if (id == R.id.nav_catalog) {

        }*/
        unFragmentTransaction.replace(R.id.fragmentContainerHomeReceptionActivity, nuevoFragment, fragmentName);
        unFragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onProductAvailableClick(final Product product){

    }
    public void onProductNotAvailableClick(Product product){

    }
}
