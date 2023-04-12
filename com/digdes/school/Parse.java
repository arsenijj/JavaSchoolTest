package com.digdes.school;


public class Parse {

    public Parse() {

    }

    public static boolean containsIgnoreCase(String str1, String str2){
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

    public static Boolean isValidArg(String arg) {
        return arg.equals("age") || arg.equals("cost")
                || arg.equals("lastName") || arg.equals("id")
                || arg.equals("active");
    }

    public String parse(String request) throws Exception{

        String[] reqSep = (request.split("\\s+"));
        String flag = containsIgnoreCase(request, "where") ? " where" : "";

        if (reqSep[0].toLowerCase().equals("insert") && containsIgnoreCase(request, "values")
                && !containsIgnoreCase(request, "where")) {
            return "insert";
        } else if (!(containsIgnoreCase(request, "values") || containsIgnoreCase(request, "insert"))) {

            if (reqSep[0].toLowerCase().equals("select")) {
                return "select" + flag;
            } else {
                if (reqSep[0].toLowerCase().equals("delete")) {
                    return "delete" + flag;
                } else if (reqSep[0].toLowerCase().equals("update")) {
                    return "update" + flag;
                } else {
                    throw new NullPointerException("Error in the query: " + request);
                }
            }
        } else {
            throw new NullPointerException("Error in the query: " + request);
        }
    }

}
