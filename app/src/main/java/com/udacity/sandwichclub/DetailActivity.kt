package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imgIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imgIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich wich) {
        TextView originTv = findViewById(R.id.origin_tv);
        TextView akaTv =  findViewById(R.id.also_known_tv);
        TextView ingTv = findViewById(R.id.ingredients_tv);
        TextView descTv = findViewById(R.id.description_tv);
        List<String> akaList = wich.getAlsoKnownAs();
        StringBuilder sbAka = new StringBuilder();
        List<String> ingList = wich.getIngredients();
        StringBuilder sbIng = new StringBuilder();

        if(akaList != null) {
            for (String aka: akaList) {
                sbAka.append(aka);
                sbAka.append(", ");
            }
            sbAka.delete(sbAka.length()-2, sbAka.length());
        } else {
            sbAka.append("");
        }

        if(ingList != null) {
            for (String ing: ingList) {
                sbIng.append(ing);
                sbIng.append(", ");
            }
            sbIng.delete(sbIng.length()-2, sbIng.length());
        } else {
            sbIng.append("");
        }

        descTv.setText(wich.getDescription());
        originTv.setText(wich.getPlaceOfOrigin());
        akaTv.setText(sbAka.toString());
        ingTv.setText(sbIng.toString());

    }
}
