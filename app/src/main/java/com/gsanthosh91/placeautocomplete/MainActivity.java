package com.gsanthosh91.placeautocomplete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;

public class MainActivity extends AppCompatActivity implements PlacesAutoCompleteAdapter.ClickListener {

    private PlacesAutoCompleteAdapter mAutoCompleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Places.initialize(getApplicationContext(), getString(R.string.google_map_key));

        EditText searchEdit = findViewById(R.id.searchEdit);
        RecyclerView placesRv = findViewById(R.id.placesRv);

        searchEdit.addTextChangedListener(filterTextWatcher);
        mAutoCompleteAdapter = new PlacesAutoCompleteAdapter(this);
        placesRv.setLayoutManager(new LinearLayoutManager(this));
        mAutoCompleteAdapter.setClickListener(this);
        placesRv.setAdapter(mAutoCompleteAdapter);

    }


    private TextWatcher filterTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            if (!s.toString().equals("")) {
                mAutoCompleteAdapter.getFilter().filter(s.toString());
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

    };

    @Override
    public void click(Place place) {
        Toast.makeText(this, place.getAddress(), Toast.LENGTH_SHORT).show();
    }
}
