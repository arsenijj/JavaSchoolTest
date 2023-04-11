package com.digdes.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {

  public JavaSchoolStarter() {

  }

  public String execute(String request) {

    Parse parser = new Parse();
    String res = parser.parse(request);

    if (res.equals("insert")) {
      Insert i = new Insert();
      Map<String, Object> newRow = i.insert(request.split("\\s+"));

      Main.table.add(newRow);
    } else {

      String[] reqSep = request.split("\\s+");
      if (res.contains("where")) {

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

        List<Map<String, Object>> new_table = new ArrayList<>();
        Where cond = new Where();
        for (int i = 0; i < Main.table.size(); i++) {
          cond.logic(right, and, Main.table.get(i));
        }
      } else {
        if (res.contains("select")) {

          String[] args = request.split(",");
          args[0] = args[0].split("\\s+")[1].replaceAll("\\s+",
                                                              "");
          for (String arg : args) {
            arg = arg.replaceAll("\\s+", "");
            if (!parser.isValidArg(arg)) {
              throw new NullPointerException("Arguement error");
            }
          }
          for (int i = 0; i < Main.table.size(); i++) {
            Map<String, Object> st = Main.table.get(i);
            for (String arg : args) {
              System.out.print(st.get(arg.replaceAll("\\s+",
                                                     "")) + " ");
            }
            System.out.println();
          }
        } else {

        }
      }
    }

    return "";
  }
}
