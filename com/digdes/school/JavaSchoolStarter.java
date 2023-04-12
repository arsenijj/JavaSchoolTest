package com.digdes.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {

  public JavaSchoolStarter() {

  }

  public List<Map<String, Object>> execute(String request) {

    request = request.replace(";", "");
    Parse parser = new Parse();
    String res = parser.parse(request);

    if (res.equals("insert")) {
      Insert i = new Insert();
      Map<String, Object> newRow = i.insert(request.split("\\s+"));

      Main.table.add(newRow);
    } else {
      List<Map<String, Object>> new_table = new ArrayList<>();
      String[] reqSep = request.split("\\s+");

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

      if (res.contains("where")) {
        new_table.clear();
        Where cond = new Where();
        for (int i = 0; i < Main.table.size(); i++) {
          Map<String, Object> row = Main.table.get(i);
          if (cond.logic(right, and, row)) {
            new_table.add(row);
          }
        }
      }

      if (res.contains("select")) {
        List<Map<String, Object>> selected_rows = new ArrayList<>();
        String[] args = left.split("\\s+");

        if (new_table.size() == 0) {
          return new_table;
        } else if (args.length == 1) {
          return Main.table;
        } else {
          
          for (int i = 0; i < new_table.size(); i++) {

            Map<String, Object> current_row = new_table.get(i);

            Map<String, Object> row = new HashMap<>();
            for (int j = 1; j < args.length; j++) {

              if (!Parse.isValidArg(args[j])) {
                throw new NullPointerException("Arguement name error:" + args[j]);
              } else {
                row.put(args[j], current_row.get(args[j]));
              }
            }
            selected_rows.add(row);
          }
        }
        return selected_rows;
      } else if (res.contains("update")) {
        String[] args = left.split("\\s+");

        if (args.length == 1) {
          return Main.table;
        } else {
          new_table.clear();
          for (int i = 0; i < Main.table.size(); i++) {

            Map<String, Object> current_row = Main.table.get(i);

            Map<String, Object> row = new HashMap<>();
            for (int j = 1; j < args.length; j++) {
              if (!Parse.isValidArg(args[j])) {
                throw new NullPointerException("Arguement name:" + args[j]);
              } else {
                row.put(args[j], current_row.get(args[j]));
              }
            }
            new_table.add(row);
          }
        }
        return new_table;
      } else if (res.contains("delete")) {
        new_table.clear();
      }

    }
    return Main.table;
  }
}
