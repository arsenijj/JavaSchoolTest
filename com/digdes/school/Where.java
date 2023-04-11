package com.digdes.school;

import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthDesktopPaneUI;

public class Where {

    private Boolean logicString(String str1, String str2, String operator) {

        switch (operator.toLowerCase()) {
            case "like", "ilike":
                if (str2.startsWith("%") && str2.endsWith("%")) {
                    if (operator.equals("like")) {
                        return str1.contains(str2);
                    } else {
                        return str1.toLowerCase().contains(str2.toLowerCase());
                    }
                } else {
                    if (str2.startsWith("%")) {
                        if (operator.equals("like")) {
                            return str1.endsWith(str2);
                        } else {
                            return str1.toLowerCase().endsWith(str2.toLowerCase());
                        }
                    } else {
                        if (str2.endsWith("%")) {
                            if (operator.equals("like")) {
                                return str1.startsWith(str2);
                            } else {
                                return str1.toLowerCase().startsWith(str2.toLowerCase());
                            }
                        } else {
                            return str1.equals(str2);
                        }
                    }
                }

            case "=":
                return str1.equals(str2);

            case "!=":
                return !str1.equals(str2);

        }
        return false;
    }

    private Boolean logicNumber(Object a, Double b, String operator) {

        switch (operator) {

            case ">=":
                return Double.parseDouble(a.toString()) >= b;
            case "=":
                return Double.parseDouble(a.toString()) == b;
            case "<=":
                return Double.parseDouble(a.toString()) <= b;
            case ">":
                return Double.parseDouble(a.toString()) > b;
            case "<":
                return Double.parseDouble(a.toString()) < b;
        }
        return false;
    }

    public Boolean logic(String right, Integer and, Map<String, Object> row) {
        
        System.out.println(row);
        String[] sep_by_logic = right.split("or");
        String[][] sep_by_and = new String[and][];
        Integer counter = 0;

        for (String i : right.split("or")) {
            sep_by_and[counter] = i.split("and");
            counter++;
            System.out.println(sep_by_and[counter - 1]);
        }
        for (int i = 0; i < sep_by_and.length;i++) {
            for (int j = 0; j < sep_by_and[i].length; j++) {
                System.out.println(sep_by_and[i][j]);
                if (sep_by_and[i][j].contains("id") || sep_by_and[i][j].contains("age")
                        || sep_by_and[i][j].contains("cost")) {
                    String[] arg1arg2;
                    String operator;
                    if (sep_by_and[i][j].contains("<=")) {
                        sep_by_and[i][j].replace("<=", " ");
                        operator = "<=";
                        arg1arg2 = sep_by_and[i][j].split("\\s+");
                    } else {
                        if (sep_by_and[i][j].contains(">=")) {
                            sep_by_and[i][j].replace(">=", " ");
                            operator = ">=";
                            arg1arg2 = sep_by_and[i][j].split("\\s+");
                        } else {
                            if (sep_by_and[i][j].contains("<")) {
                                sep_by_and[i][j].replace("<", " ");
                                operator = "<";
                                arg1arg2 = sep_by_and[i][j].split("\\s+");
                            } else {
                                if (sep_by_and[i][j].contains(">")) {
                                    sep_by_and[i][j].replace(">", " ");
                                    operator = ">";
                                    arg1arg2 = sep_by_and[i][j].split("\\s+");
                                } else {
                                    if (sep_by_and[i][j].contains("=")) {
                                        sep_by_and[i][j].replace("=", " ");
                                        operator = "=";
                                        arg1arg2 = sep_by_and[i][j].split("\\s+");
                                    } else {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    String arguement = "'" + arg1arg2[0] +"'";
                    System.out.println(row.get(arguement));
                    if (!logicNumber(row.get(arguement), Double.parseDouble(arg1arg2[1]), operator)){
                        return false;
                    }
                }

            }
            return true;
            // System.out.println("newRow))");
        }
        for (int i = 0; i < sep_by_and.length; i++) {
            String[] bol = sep_by_and[i];
            System.out.println();
        }

        return true;
    }
}