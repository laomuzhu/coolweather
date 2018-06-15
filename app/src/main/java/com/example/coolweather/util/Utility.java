package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility  {
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvince =new JSONArray(response);
                for (int i=0;i< allProvince.length();i++){
                    JSONObject provinceOject=allProvince.getJSONObject(i);
                    Province province =new Province();
                    province.setProvinceCode(provinceOject.getInt("id"));
                    province.setProvinceName(provinceOject.getString("name"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean handleCityResponce(String responce,int provinceId){
        if (!TextUtils.isEmpty(responce)){
            try {
                JSONArray allCity= new JSONArray(responce);
                for (int i=0;i<allCity.length();i++){
                    JSONObject cityObject=allCity.getJSONObject(i);
                    City city=new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }

            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponce(String responce,int cityId){
        try {
            JSONArray allCounty=new JSONArray(responce);
            for (int i=0;i<allCounty.length();i++){
                JSONObject countyObject=allCounty.getJSONObject(i);
                County county=new County();
                county.setWeatherId(countyObject.getString("weather_id"));
                county.setCountyName(countyObject.getString("name"));
                county.setCityId(cityId);
                county.save();
            }
            return true;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return false;
    }
}
