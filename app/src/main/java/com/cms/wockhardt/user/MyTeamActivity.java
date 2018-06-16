package com.cms.wockhardt.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cms.wockhardt.user.adapters.MyTeamAdapter;
import com.cms.wockhardt.user.application.AppConstants;
import com.cms.wockhardt.user.models.Doctor;

import java.util.ArrayList;
import java.util.List;

public class MyTeamActivity extends CustomActivity {

    private RecyclerView rv_list;
    private Toolbar toolbar;
    public boolean isGoNext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        isGoNext = getIntent().getBooleanExtra(AppConstants.EXTRA, false);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.my_team));
        setupUiElements();
    }

    private void setupUiElements() {
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        MyTeamAdapter adapter = new MyTeamAdapter(getContext(), new ArrayList<Doctor>());
        rv_list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private List<Doctor> doctorsList = new ArrayList<>();

    void filter(String text) {
        try {
            List<Doctor> temp = new ArrayList();
            for (Doctor d : doctorsList) {
                if (d.getName().toLowerCase().contains(text)) {
                    temp.add(d);
                }
            }
//            adapter.updateList(temp);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Context getContext() {
        return MyTeamActivity.this;
    }


}