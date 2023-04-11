package com.digdes.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {

  public JavaSchoolStarter() {

  }

  public String execute(String request) {

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

        Where cond = new Where();
        for (int i = 0; i < Main.table.size(); i++) {
          Map<String, Object> row = Main.table.get(i);
          if (cond.logic(right, and, row)){
            new_table.add(row);
          }
        }
      } else {
 
        new_table = Main.table;
        if (res.contains("select")) {

          String[] args = request.split(",");
          args[0] = args[0].split("\\s+")[1].replaceAll("\\s+",
                                                              "");
          for (String arg : args) {
            arg = arg.replaceAll("\\s+", "");
            if (!Parse.isValidArg(arg)) {
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
          if (res.contains("update")){
            String[] args = request.split(",");
            // args[0] = args[0].split("\\s+")[1].replaceAll("\\s+",
            //                                                   "");
            for (String arg: args){
                String[] argValue = arg.split("=");
                String[] current;
                if ((current = argValue[0].split("\\s+")).length == 2){
                  argValue[0] = current[1];
                }
                System.out.println(Arrays.toString(argValue));
                if (!Parse.isValidArg(argValue[0])){
                  throw new NullPointerException("Arguement error");
                }
                else{
                  for (int i = 0; i < new_table.size(); i++){
                    Map<String, Object> currentRow= new_table.get(i);
                    currentRow.put(argValue[0], argValue[1]);
                  }
                }
              
              }
          }
          else{
            if (res.contains("delete")){
              new_table.clear();
            }
          }
        }
      }
    }

    return "";
  }
}
