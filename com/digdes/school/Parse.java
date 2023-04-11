package com.digdes.school;

// import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parse {
   

    public static boolean containsIgnoreCase(String str1, String str2) {
        final int length = str2.length();
        if (length == 0)
            return true;

        final char firstLo = Character.toLowerCase(str2.charAt(0));
        final char firstUp = Character.toUpperCase(str2.charAt(0));

        for (int i = str1.length() - length; i >= 0; i--) {

            final char ch = str1.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (str1.regionMatches(true, i, str2, 0, length))
                return true;
        }

        return false;
    }

    public Boolean isValidArg(String arg) {
        return arg.equals("age") || arg.equals("cost") 
                || arg.equals("lastName") || arg.equals("id")
                || arg.equals("active");
    }

    public String parse(String request) {

        String[] reqSep = (request.split("\\s+"));
        String flag = containsIgnoreCase(request, "where") ? " where" : "";

        if (reqSep[0].toLowerCase().equals("insert") && reqSep[1].toLowerCase().equals("values")) {
            return "insert";
        } else {
            if (reqSep[0].toLowerCase().equals("select")) {
                return "select" + flag;
            } else {
                if (reqSep[0].toLowerCase().equals("delete")) {
                    return "delete" + flag;
                } else {
                    if (reqSep[0].toLowerCase().equals("update") && !containsIgnoreCase(request, "where")) {
                        return "delete";
                    } else {
                        return "error";
                    }
                }
            }
        }

    }

}

// String left = "";
// String right = "";
// Boolean flag = false;
// Integer and = 0;

// for (String i : reqSep) {
// if (!i.toLowerCase().equals("where") && !flag) {
// left += i.replace(",", "") + " ";
// } else {
// flag = true;
// }
// if (!i.toLowerCase().equals("where") && flag) {
// right += i.replace(",", "") + " ";
// if (i.toLowerCase().equals("and")) {
// and += 1;
// }
// }
// }
// System.out.println(right + and);
// Where i = new Where();
// i.logic(right, and);
