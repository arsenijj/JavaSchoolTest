package com.digdes.school;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main{

    public Main() {

    }

    public static List<Map<String, Object>> table = new ArrayList<>();
    
    public static void main(String... args) throws Exception{

        // JavaSchoolStarter a = new JavaSchoolStarter();

        JavaSchoolStarter a = new JavaSchoolStarter();
  
        List<Map<String, Object>> r1 = a.execute("INSERT VALUES 'lastName' = '1Федоров1', 'id' = 1, 'age' = 10, 'active' = true, 'cost' = 3.1;");
        List<Map<String, Object>> r2 = a.execute("INSERT VALUES 'lastName' = 'Федоров2', 'id' = 2, 'age' = 42, 'active' = false, 'cost' = 3.2;");
        List<Map<String, Object>> r3 = a.execute("INSERT VALUES 'lastName' = 'Федоров3', 'id' = 3, 'age' = 43, 'active' = true, 'cost' = 3.3;");
        List<Map<String, Object>> r4 = a.execute("INSERT VALUES 'lastName' = 'Федоров4', 'id' = 4, 'age' = 44, 'active' = false, 'cost' = 3.4;");
        List<Map<String, Object>> b = a.execute("select age, lastName, cost where active = 1");
        List<Map<String, Object>> f = a.execute("select age, id where age = 1;");
        List<Map<String, Object>> u= a.execute("UpDate active = true, lastName = 'Сеня' where id = 2 or cost >= 3.4");
        List<Map<String, Object>> d= a.execute("delete where id = 1 or lastName ilike '%ФеДо%'");
        System.out.println(table);
    }
 }
 

 /*--------------ПРИМЕР ЗАПУСКА--------------
  
        JavaSchoolStarter a = new JavaSchoolStarter();
  
        List<Map<String, Object>> r1 = a.execute("INSERT VALUES 'lastName' = '1Федоров1', 'id' = 1, 'age' = 10, 'active' = true, 'cost' = 3.1;");
        List<Map<String, Object>> r2 = a.execute("INSERT VALUES 'lastName' = 'Федоров2', 'id' = 2, 'age' = 42, 'active' = false, 'cost' = 3.2;");
        List<Map<String, Object>> r3 = a.execute("INSERT VALUES 'lastName' = 'Федоров3', 'id' = 3, 'age' = 43, 'active' = true, 'cost' = 3.3;");
        List<Map<String, Object>> r4 = a.execute("INSERT VALUES 'lastName' = 'Федоров4', 'id' = 4, 'age' = 44, 'active' = false, 'cost' = 3.4;");
        List<Map<String, Object>> b = a.execute("select age, lastName, cost where active = 1");
        List<Map<String, Object>> f = a.execute("select age, id where age = 1;");
        List<Map<String, Object>> u= a.execute("UpDate active = true, lastName = 'Сеня' where id = 2 or cost >= 3.4");
        List<Map<String, Object>> d= a.execute("delete where id = 1 or lastName ilike '%ФеДо%'");
        System.out.println(table);

  */