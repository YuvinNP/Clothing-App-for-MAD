package com.example.clothing_app_mad.Entites;

import java.util.ArrayList;

public class CommonUtil {

    public static String generateCustomerID(ArrayList<String> arrayList) {

        String id;
        int next = arrayList.size();
        next++;
        id = "C0" + next;
        if (arrayList.contains(id)) {
            next++;
            id = "C0" + next;
        }
        return id;
    }
}
