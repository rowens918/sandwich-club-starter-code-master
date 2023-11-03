package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static Sandwich sammich = new Sandwich();

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONObject objname = obj.getJSONObject("name");
            String name = objname.optString("mainName");
            JSONArray aka = objname.getJSONArray("alsoKnownAs");
            String origin = obj.optString("placeOfOrigin");
            String desc = obj.optString("description");
            String img = obj.optString("image");
            JSONArray ing = obj.getJSONArray("ingredients");
            List<String> akaList = new ArrayList<>();
            List<String> ingList = new ArrayList<>();

            for (int i = 0; i < aka.length(); i++) {
                akaList.add(i, aka.optString(i));
            }
            if (akaList.size() < 1) {
                akaList.add(0, "");
            }
            for (int i = 0; i < ing.length(); i++) {
                ingList.add(i, ing.optString(i));
            }
            if (ingList.size() < 1) {
                ingList.add(0, "");
            }

            sammich = new Sandwich(name, akaList, origin, desc, img, ingList);
        } catch (JSONException e){
            e.printStackTrace();
        }
        return sammich;
    }
}
