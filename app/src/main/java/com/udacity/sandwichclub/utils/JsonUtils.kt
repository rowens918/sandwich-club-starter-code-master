package com.udacity.sandwichclub.utils

import com.udacity.sandwichclub.model.Sandwich
import org.json.JSONObject
import org.json.JSONException

object JsonUtils {
    private var sammich = Sandwich()
    fun parseSandwichJson(json: String): Sandwich {
        try {
            val obj = JSONObject(json)
            val objname = obj.getJSONObject("name")
            val name = objname.optString("mainName")
            val aka = objname.getJSONArray("alsoKnownAs")
            val origin = obj.optString("placeOfOrigin")
            val desc = obj.optString("description")
            val img = obj.optString("image")
            val ing = obj.getJSONArray("ingredients")
            val akaList: ArrayList<String> = ArrayList()
            val ingList: ArrayList<String> = ArrayList()

            for (i in 0 until aka.length()) {
                akaList.add(i, aka.optString(i))
            }
            if (akaList.isEmpty()) {
                akaList.add(0, "")
            }
            for (i in 0 until ing.length()) {
                ingList.add(i, ing.optString(i))
            }
            if (ingList.isEmpty()) {
                ingList.add(0, "")
            }
            sammich = Sandwich(name, akaList, origin, desc, img, ingList)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return sammich
    }
}