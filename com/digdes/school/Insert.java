package com.digdes.school;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Insert {

    public Map<String, Object> insert(String[] query) {

        String left = "";

        for (int i = 2; i < query.length; i++) {
            left += query[i].replace(",", "").replace(";", "") + " ";
        }

        query = left.split("\\s+");

        Map<String, Object> row = new HashMap<>();

        row.put("id", null);
        row.put("lastName", null);
        row.put("age", null);
        row.put("cost", null);
        row.put("active", null);

        if (query.length < 3 || query.length % 3 != 0 || query.length > 15) {
            System.out.println("Too few arguements");
            return row;
        } else {
            for (int i = 0; i < query.length - 2; i += 3) {
                if (query[i + 1].equals("=")) {
                    switch (query[i]) {

                        case "'lastName'", "lastName":
                            row.put("lastName", query[i + 2]);
                            break;
                        case "'id'", "id":
                            try {
                                row.put("id", 
                                        Integer.parseInt(query[i + 2]));

                            } catch (Exception e) {
                                System.out.println("Value error in id.");
                            }
                            break;
                        case "'active'", "active":
                            try {

                                row.put("active", 
                                        Boolean.parseBoolean(query[i + 2]));
                            } catch (Exception e) {
                                System.out.println("Value error in active.");
                            }
                            break;
                        case "'age'", "age":
                            try {

                                row.put("age", 
                                        Integer.parseInt(query[i + 2]));
                            } catch (Exception e) {
                                System.out.println("Value error in age.");
                            }
                            break;
                        case "'cost'", "cost":
                            try {

                                row.put("cost", 
                                        Double.parseDouble(query[i + 2]));
                            } catch (Exception e) {

                            }
                            break;
                    }
                } else {
                    return row;
                }
            }

        }
        return row;
    }
}
