package com.digdes.school;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Insert {

    public Insert() {

    }

    public Map<String, Object> insert(String[] query) throws Exception{

        String left = "";

        for (int i = 2; i < query.length; i++) {
            left += query[i].replace(",",
                    "").replace(";",
                            "")
                    +
                    " ";
        }
        // System.out.println(left);
        query = left.split("[=] | ['id'] | ['lastName'] | ['cost'] | ['active'] | ['age']");
        // System.out.println(Arrays.toString(query));
        Map<String, Object> row = new HashMap<>();

        row.put("id", null);
        row.put("lastName", null);
        row.put("age", null);
        row.put("cost", null);
        row.put("active", null);

        if (query.length < 3 || query.length % 2 != 0 || query.length > 10) {
            throw new NullPointerException("Too few arguements");

        } else {
            for (int i = 0; i < query.length - 1; i += 2) {
                    switch (query[i].trim()) {

                        case "'lastName'", "lastName":
                            row.put("lastName", query[i + 1]);
                            break;
                        case "id'", "id":
                            try {
                                row.put("id",
                                        Long.parseLong(query[i + 1].trim()));

                            } catch (Exception e) {
                                System.out.println("Value error in id.");
                            }
                            break;
                        case "active'", "active":
                            try {

                                row.put("active",
                                        Boolean.parseBoolean(query[i + 1].trim()));
                            } catch (Exception e) {
                                System.out.println("Value error in active.");
                            }
                            break;
                        case "age'", "age":
                            try {

                                row.put("age",
                                        Long.parseLong(query[i + 1].trim()));
                            } catch (Exception e) {
                                System.out.println("Value error in age.");
                            }
                            break;
                        case "cost'", "cost":
                            try {

                                row.put("cost",
                                        Double.parseDouble(query[i + 1].trim()));
                            } catch (Exception e) {
                                System.out.println("Value error in cost.");
                            }
                            break;
                        default : throw new NullPointerException("Arguement does not exist " + query[i]);
                    }
            }

        }
        return row;
    }
}
