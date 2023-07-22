package com.anhvt.aptechmanagement.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAddressFromAPI {

    public static GetAddressFromAPI getInstance(){
        return  new GetAddressFromAPI();
    }

    public  Map<Integer, String> getProvinceNamesFromAPI(){
        Map<Integer, String> provinces = new HashMap<>();

        try {

            // Tạo URL API
            URL url = new URL("https://provinces.open-api.vn/api/?depth=1");

            // Mở kết nối HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Đọc phản hồi từ API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Xử lý phản hồi JSON và trích xuất danh sách tên tỉnh
            JSONArray json = new JSONArray(response.toString());
            for (int i = 0; i < json.length(); i++) {
                JSONObject provinceJson = json.getJSONObject(i);
                String provinceName = provinceJson.getString("name");
                int provinceCode = provinceJson.getInt("code");
                provinces.put(provinceCode,provinceName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return provinces;
    }

    public Map<Integer, String> getDistrictsFromAPI(int code_Province) {
        Map<Integer, String> districts = new HashMap<>();
        try {
            // Tạo URL API
            URL url = new URL("https://provinces.open-api.vn/api/p/" + code_Province + "?depth=2");

            // Mở kết nối HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Đọc phản hồi từ API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

//      Lấy ra đối tượng tỉnh bao gồm list huyện
            JSONObject json = new JSONObject(response.toString());

//      lấy ra JSON huyện
            JSONArray districtsArray = new JSONArray(json.getJSONArray("districts"));
//      lấy ra List tên huyện
            for (Object districtObj : districtsArray) {
                JSONObject districtJson = (JSONObject) districtObj;
                String districtName = (String) districtJson.get("name");
                int districtCode = districtJson.getInt("code");
                districts.put(districtCode, districtName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return districts;
    }

    public Map<Integer, String> getCommunesFromAPI(int code_District) {
        Map<Integer, String> communes = new HashMap<>();
        try {
            // Tạo URL API
            URL url = new URL("https://provinces.open-api.vn/api/d/" + code_District + "?depth=2");

            // Mở kết nối HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Đọc phản hồi từ API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

//      Lấy ra đối tượng tỉnh bao gồm list xã
            JSONObject json = new JSONObject(response.toString());
//      lấy ra JSON xã
            JSONArray districtsArray = new JSONArray(json.getJSONArray("wards"));
//      lấy ra List tên xã
            for (Object communeObj : districtsArray) {
                JSONObject communeJson = (JSONObject) communeObj;
                int commnecode = communeJson.getInt("code");
                String commneName = (String) communeJson.get("name");
                communes.put(commnecode,commneName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return communes;
    }

}
