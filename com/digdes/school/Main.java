package com.digdes.school;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    
    public static void main(String... args){
        // Parse test = new Parse();
        // Boolean a = test.execute("INSERT VALUES 'lastName' = 'Федоров', 'id' = 3, 'age' = 40, 'active' = true");
        // System.out.println(a);
       
        JavaSchoolStarter a = new JavaSchoolStarter();
        // Insert i = new Insert();
        // Map<String, Object> b = test1.execute("insert VALUES 'lastName' = 'Никитин' , 'id' = 3, 'age' = 21, 'active' = false, 'cost' = 44.2020");
        String b = a.execute("select aboba, jopa, test1, test2 where suka = 2 and penis < 4 or aboba like 'su4ka' and 1 = 1 or 2 = 4 and suka ilike 'pensil%'");
        // String b = a.execute("INSERT VALUES 'lastName' = 'Федоров', 'id' = 3, 'age' = 40, 'active' = true, 'cost' = 3.3;");

        System.out.println(b);
    }
 }
 