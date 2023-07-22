package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.Utils.GetAddressFromAPI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.*;

public class TEST  implements Initializable {
    @FXML
    private ComboBox<String> huyen;

    @FXML
    private ComboBox<String> tinh;

    @FXML
    private ComboBox<String> xa;

    Map<Integer, String>  provinces = GetAddressFromAPI.getInstance().getProvinceNamesFromAPI();
    Map<Integer, String>  districts = new HashMap<>();
    Map<Integer, String>  communes = new HashMap<>();


    int code_province_selected;
    int code_district_selected;
    int code_comune_selected;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        List<String> listProvinceName = new ArrayList<>(provinces.values());
        tinh.getItems().addAll(listProvinceName);

        tinh.setOnAction(event -> {
            String selectedProvince = tinh.getValue();
            for (Map.Entry<Integer, String> entry : provinces.entrySet()) {
                if (entry.getValue().equals(selectedProvince)) {
                    code_province_selected = entry.getKey();
                    System.out.println("code_province_selected: "+ code_province_selected);
                    break;
                }
            }

            districts = GetAddressFromAPI.getInstance().getDistrictsFromAPI(code_province_selected);
            List<String> listDistrictNames = new ArrayList<>(districts.values());

            huyen.getItems().clear();
            huyen.getItems().addAll(listDistrictNames);

        });

        huyen.setOnAction(event -> {
            String selectedDistrict = huyen.getValue();
            for (Map.Entry<Integer, String> entry : districts.entrySet()) {
                if (entry.getValue().equals(selectedDistrict)) {
                    code_district_selected = entry.getKey();
                    System.out.println("code_district_selected: "+ code_district_selected);
                    break;
                }
            }

            communes = GetAddressFromAPI.getInstance().getCommunesFromAPI(code_district_selected);
            List<String> listCommuneNames = new ArrayList<>(communes.values());

            xa.getItems().clear();
            xa.getItems().addAll(listCommuneNames);


        });

        xa.setOnAction(event -> {
            String selectedCommune = xa.getValue();
            for (Map.Entry<Integer, String> entry : communes.entrySet()) {
                if (entry.getValue().equals(selectedCommune)) {
                    code_comune_selected = entry.getKey();
                    System.out.println("code_comune_selected: "+ code_comune_selected);
                    break;
                }
            }
        });



    }
}
