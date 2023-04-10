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

        Boolean result = false;

        switch (operator.toLowerCase()) {
            case "like", "ilike": {
                if (str2.startsWith("%") && str2.endsWith("%")) {
                    if (operator.equals("like")) {
                        return str1.contains(str2);
                    } else {
                        result = str1.toLowerCase().contains(str2.toLowerCase());
                    }
                } else {
                    if (str2.startsWith("%")) {
                        if (operator.equals("like")) {
                            return str1.endsWith(str2);
                        } else {
                            result = str1.toLowerCase().endsWith(str2.toLowerCase());
                        }
                    } else {
                        if (str2.endsWith("%")) {
                            if (operator.equals("like")) {
                                result = str1.startsWith(str2);
                            } else {
                                result = str1.toLowerCase().startsWith(str2.toLowerCase());
                            }
                        } else {
                            result = str1.equals(str2);
                        }
                    }
                }
            }

            case "=":
                result = str1.equals(str2);

            case "!=":
                result = !str1.equals(str2);

        }
        return result;
    }

    private Boolean logicNumber(Double a, Double b, String operator) {

        Boolean result = false;

        switch (operator) {

            case ">=":
                result = a >= b;
            case "=":
                result = a == b;
            case "<=":
                result = a < b;
            case ">":
                result = a > b;
            case "<":
                result = a < b;
        }
        return result;
    }

    public Boolean logic(String right, Integer and) {
        String[] sep_by_logic = right.split("or");
        String[][] sep_by_and = new String[and][];
        Integer counter = 0;

        for (String i : right.split("or")) {
            sep_by_and[counter] = i.split("and");
            counter++;
        }
        for (int i=0; i < sep_by_and.length; i++){
            for (int j=0; j<sep_by_and[i].length; j++){
                System.out.println(sep_by_and[i][j]);
            }
            System.out.println("newRow))");
        }
        for (int i = 0; i < sep_by_and.length; i++) {
            String[] bol = sep_by_and[i];
            System.out.println();
        }

        return true;
    }
}