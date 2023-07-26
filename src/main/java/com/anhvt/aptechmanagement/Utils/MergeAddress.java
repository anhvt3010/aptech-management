package com.anhvt.aptechmanagement.Utils;

public class MergeAddress {
    public static MergeAddress getInstance(){
        return new MergeAddress();
    }

    public String mergeAddress(String lang, String xa, String huyen, String tinh){
        return lang + ", " + xa + ", " + huyen + ", " + tinh;
    }
}
