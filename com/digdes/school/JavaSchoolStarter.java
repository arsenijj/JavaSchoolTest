package com.digdes.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {
  // Дефолтный конструктор
  public JavaSchoolStarter() {

  }

  // На вход запрос, на выход результат выполнения запроса
  public String execute(String request) {
    // Здесь начало исполнения вашего кода
    Parse a = new Parse();
    String res = a.parse(request);

    if (res.equals("insert")) {
      Insert i = new Insert();
      Map<String, Object> newRow = i.insert(request.split("\\s+"));
      System.out.println(newRow);
    }
    else{
      String[] reqSep = request.split("\\s+");
      if (res.contains("where")){
        
            String left = "";
            String right = "";
            Boolean flag = false;
            Integer and = 0;
            for (String i : reqSep) {
            if (!i.toLowerCase().equals("where") && !flag) {
            left += i.replace(",", "") + " ";
            } else {
            flag = true;
            }
            if (!i.toLowerCase().equals("where") && flag) {
            right += i.replace(",", "") + " ";
            if (i.toLowerCase().equals("and")) {
            and += 1;
            }
            }
            }
            Where i = new Where();
            i.logic(right, and);
      }
    }
    // System.out.print(res);
    return "";
  }
}
