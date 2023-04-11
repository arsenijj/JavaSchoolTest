package com.digdes.school;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    public static List<Map<String, Object>> table = new ArrayList<>();
    
    public static void main(String... args){
        // Parse test = new Parse();
        
        // Boolean a = test.execute("INSERT VALUES 'lastName' = 'Федоров', 'id' = 3, 'age' = 40, 'active' = true");
        // System.out.println(a);
       
        JavaSchoolStarter a = new JavaSchoolStarter();
        // Insert i = new Insert();
        // Map<String, Object> b = test1.execute("insert VALUES 'lastName' = 'Никитин' , 'id' = 3, 'age' = 21, 'active' = false, 'cost' = 44.2020 or active = true");

        String r1 = a.execute("INSERT VALUES 'id' = 1, 'age' = 10, 'active' = true, 'cost' = 3.1;");
        String r2 = a.execute("INSERT VALUES 'lastName' = 'Федоров2', 'id' = 2, 'age' = 42, 'active' = false, 'cost' = 3.2;");
        String r3 = a.execute("INSERT VALUES 'lastName' = 'Федоров3', 'id' = 3, 'age' = 43, 'active' = true, 'cost' = 3.3;");
        String r4 = a.execute("INSERT VALUES 'lastName' = 'Федоров4', 'id' = 4, 'age' = 44, 'active' = false, 'cost' = 3.4;");
        // String b = a.execute("select age, lastName, cost where age >= 2 or cost < 4 or lastName like 'su4ka' and age < 100 or cost > 10 and cost < 1000 or active = false");
        String b = a.execute("select age, lastName, cost where age = null or cost < 10 or lastName like 'abob' or age > 1 or cost > 10 and cost < 1000");
        // String b = a.execute("select age, lastName, cost");
        // System.out.println(b);
        // System.out.println(r1)
        // System.out.println(r2);
        // System.out.println(r3);
        // System.out.println(r4);

    }
 }
 