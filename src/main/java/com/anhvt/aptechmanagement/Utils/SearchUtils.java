package com.anhvt.aptechmanagement.Utils;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;

public class SearchUtils {
    public static SearchUtils getInstance(){
        return new SearchUtils();
    }

    public static <T> FilteredList<T> createSearchFilter(ObservableList<T> observableList, TextField txtSearch, String searchText) {
        FilteredList<T> filteredList = new FilteredList<>(observableList, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return searchText.toLowerCase().contains(lowerCaseFilter);
            });
        });
        return filteredList;
    }

    public static <T> FilteredList<T> createSearchFilter(ObservableList<T> observableList, TextField txtSearch, String searchText1, String searchText2) {
        FilteredList<T> filteredList = new FilteredList<>(observableList, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return searchText1.toLowerCase().contains(lowerCaseFilter) ||
                            searchText2.toLowerCase().contains(lowerCaseFilter);
            });
        });
        return filteredList;
    }

    private static Object getSearchText(Object data) {
        // Implement your logic here to return the appropriate search text from the object
        return "";
    }
 }
