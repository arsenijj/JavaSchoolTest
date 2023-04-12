package com.digdes.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {

  public JavaSchoolStarter() {

  }

  public List<Map<String, Object>> execute(String request) throws Exception{

    List<Map<String, Object>> new_table = new ArrayList<>();
    List<Map<String, Object>> help_table = new ArrayList<>();
    request = request.replace(";", "");
    Parse parser = new Parse();
    String res = parser.parse(request);

    if (res.equals("insert")) {
      Insert i = new Insert();
      Map<String, Object> newRow = i.insert(request.split("\\s+"));
      Main.table.add(newRow);
      return Main.table;
    } else {
      new_table.clear();
      help_table.clear();
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
        help_table.clear();
        Where cond = new Where();
        for (int i = 0; i < Main.table.size(); i++) {
          Map<String, Object> row = Main.table.get(i);
          if (cond.logic(right, and, row)) {
            new_table.add(row);
          } else {
            help_table.add(row);
          }
        }

      }

      if (res.contains("select")) {
        List<Map<String, Object>> selected_rows = new ArrayList<>();
        String[] args = left.split("\\s+");

        if (args.length == 1) {
          return Main.table;
        } else if (new_table.size() == 0) {
          for (int i = 0; i < Main.table.size(); i++) {
            Map<String, Object> row = new HashMap<>();
            for (int j = 1; j < args.length; j++) {
              if (!Parse.isValidArg(args[j])) {
                throw new NullPointerException("Arguement name error: " + args[j]);
              }
              row.put(args[j], Main.table.get(i).get(args[j]));
            }
            new_table.add(row);
          }
          return new_table;
        } else {

          for (int i = 0; i < new_table.size(); i++) {

            Map<String, Object> current_row = new_table.get(i);
            Map<String, Object> row = new HashMap<>();

            for (int j = 1; j < args.length; j++) {

              if (!Parse.isValidArg(args[j])) {
                throw new NullPointerException("Arguement name error: " + args[j]);
              }
              row.put(args[j], current_row.get(args[j]));
            }
            selected_rows.add(row);
          }
        }
        new_table.clear();
        help_table.clear();
        return selected_rows;
      } else if (res.contains("update")) {

        String[] args = left.split("\\s+");

        if (args.length == 1) {
          new_table.clear();
          help_table.clear();
          return Main.table;
        }
        if (new_table.size() == 0) {
          new_table = Main.table;
        }
        for (int i = 0; i < new_table.size(); i++) {
          for (int j = 1; j < args.length; j += 3) {
            if (!Parse.isValidArg(args[j])) {
              throw new NullPointerException("Arguement name error: " + args[j]);
            }
            if (!args[j + 1].equals("=")) {
              throw new NullPointerException("Mismatch equals sign: " + args[j + 1]);
            }
            if (args[j].equals("age") || args[j].equals("id")) {
              try {
                new_table.get(i).put(args[j], Long.parseLong(args[j + 2]));
              } catch (Exception e) {
                System.out.println("Value error in " + args[i]);
              }
            } else if (args[j].equals("cost")) {
              try {
                new_table.get(i).put(args[j], Double.parseDouble(args[j + 2]));
              } catch (Exception e) {
                System.out.println("Value error in cost.");
              }
            } else if (args[j].equals("active")) {
              try {
                new_table.get(i).put(args[j], Boolean.parseBoolean(args[j + 2]));
              } catch (Exception e) {
                System.out.println("Value error in active.");
              }
            } else if (args[j].equals("lastName")) {
              new_table.get(i).put(args[j], args[j + 2]);
            } else {
              throw new NullPointerException("Arguement does not exist: " + args[j]);
            }
          }
        }

        for (int i = 0; i < help_table.size(); i++) {
          new_table.add(help_table.get(i));
        }
        return new_table;
      } else if (res.contains("delete")) {
        if (!res.contains("where")) {
          Main.table.clear();
          return Main.table;
        } else if (new_table.size() == 0) {
          return Main.table;
        } else {
          Main.table = help_table;
          return Main.table;
        }
      }

    }

    throw new NullPointerException(
        "Some error has occured in query: " + request + " It migth been happened cause of keywords.");
  }
}
