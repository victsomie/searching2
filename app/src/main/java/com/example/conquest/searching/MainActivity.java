package com.example.conquest.searching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    public EditText search;
    private List<String> list = new ArrayList<String>();
    SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        search = (EditText) findViewById( R.id.search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        countryList();

        // call the adapter with argument list of items and context.
        mAdapter = new SimpleAdapter(list, this);
        mRecyclerView.setAdapter(mAdapter);

        addTextListener();

    }

    // this method is used to create list of items.
    public void countryList(){

        list.add("Afghanistan");
        list.add("Albania");
        list.add("Algeria");
        list.add("Bangladesh");
        list.add("Belarus");
        list.add("Canada");
        list.add("Cape Verde");
        list.add("Central African Republic");
        list.add("Denmark");
        list.add("Dominican Republic");
        list.add("Egypt");
        list.add("France");
        list.add("Germany");
        list.add("Hong Kong");
        list.add("India");
        list.add("Iceland");
    }

    public void addTextListener(){

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<String> filteredList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {

                    final String text = list.get(i).toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(list.get(i));
                    }
                }

                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mAdapter = new SimpleAdapter(filteredList, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();  // data set changed
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }



}
