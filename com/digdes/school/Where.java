package com.digdes.school;

import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Where {

    private Boolean logicString(String str1, String str2, String operator) {

        switch (operator.toLowerCase()) {
            case "like", "ilike":
                if (str2.startsWith("%") && str2.endsWith("%")) {
                    if (operator.equals("like")) {
                        return str1.contains(str2.replace("%", ""))
                                && str1.lastIndexOf(str2.replace("%",
                                        "")) != 0;
                    } else {
                        return str1.toLowerCase().contains(str2.toLowerCase().replace("%",
                                ""))
                                && str1.toLowerCase().lastIndexOf(str2.toLowerCase().replace("%",
                                        "")) != 0;
                    }
                } else {
                    if (str2.startsWith("%")) {
                        if (operator.equals("like")) {
                            return str1.endsWith(str2.replace("%",
                                    ""));
                        } else {
                            return str1.toLowerCase().endsWith(str2.toLowerCase().replace("%",
                                    ""));
                        }
                    } else if (str2.endsWith("%")) {

                        if (operator.equals("like")) {
                            return str1.startsWith(str2.replace("%",
                                    ""));
                        } else {
                            return str1.toLowerCase().startsWith(str2.toLowerCase().replace("%",
                                    ""));
                        }
                    } else {
                        return str1.equals(str2.replace("%",
                                ""));
                    }

                }

            case "=":
                return str1.equals(str2.replace("%",
                        ""));

            case "!=":
                return !str1.equals(str2.replace("%", ""));

        }
        return false;
    }

    private Boolean logicNumber(Double a, String b, String operator) {
        // if (b.equals("null") && )
        switch (operator) {

            case ">=":
                return a >= Double.parseDouble(b);
            case "=":
                return a == Double.parseDouble(b);
            case "<=":
                return a <= Double.parseDouble(b);
            case ">":
                return a > Double.parseDouble(b);
            case "<":
                System.out.println("ava");
                return a < Double.parseDouble(b);
        }
        return false;
    }

    public Boolean logic(String right, Integer and, Map<String, Object> row) {

        String[] sep_by_logic = right.split("or");
        String[][] sep_by_and = new String[and][];
        Integer counter = 0;
        Boolean result = true;
        for (String i : right.split("or")) {
            for (String j : i.split("and")) {
                j = j.trim();

                if (j.contains("id") || j.contains("age")
                        || j.contains("cost")) {
                    String[] arg1arg2;
                    String operator;

                    if (j.contains("<=")) {
                        j = j.replace("<=", " ");
                        operator = "<=";
                        arg1arg2 = j.split("\\s+");
                    } else if (j.contains(">=")) {
                        j = j.replace(">=", " ");
                        operator = ">=";
                        arg1arg2 = j.split("\\s+");
                    } else if (j.contains("<")) {
                        j = j.replace("<", " ");
                        operator = "<";
                        arg1arg2 = j.split("\\s+");

                    } else if (j.contains(">")) {
                        j = j.replace(">", " ");
                        operator = ">";
                        arg1arg2 = j.split("\\s+");
                    } else if (j.contains("=")) {

                        j = j.replace("=", " ");
                        operator = "=";
                        arg1arg2 = j.split("=");
                    } else {
                        return false;
                    }

                    if (arg1arg2.length != 2) {
                        throw new NullPointerException("Arguement amount error");
                    }
                    String value1 = row.get(arg1arg2[0].replaceAll("\\s+",
                            "")) == null ? "null"
                                    : row.get(arg1arg2[0].replaceAll("\\s+",
                                            "")).toString();

                    String value2 = arg1arg2[1];
                    Double val;
                    if (value1.equals("null") || value2.equals("null")) {
                        if (!(operator.equals("=")
                                && (value2.equals("null")
                                        || value2.equals("0")))) {
                            throw new NullPointerException("Arguement error");
                        }
                    }
                    val = Double.parseDouble(row.get(arg1arg2[0].replaceAll("\\s+",
                            "")).toString());

                    if (value2.equals("null")) {
                        if (!(val == 0 && operator.equals("="))) {
                            throw new NullPointerException("Arguement error");
                        }
                    }
                    result = logicNumber(val, arg1arg2[1], operator);

                } else {
                    if (j.contains("active")) {
                        String[] arg1arg2;
                        String operator;
                        if (j.contains("!=")) {
                            j = j.replace("!=", " ");
                            operator = "!=";
                            arg1arg2 = j.split("\\s+");
                        } else if (j.contains("=")) {
                            j = j.replace("=", " ");
                            operator = "=";
                            arg1arg2 = j.split("\\s+");
                        } else {
                            throw new NullPointerException("Operator error");

                        }
                        if (arg1arg2.length != 2) {
                            throw new NullPointerException("Arguement amount error");
                        }
                        String value1 = row.get(arg1arg2[0].replaceAll("\\s+",
                                "")) == null ? "null"
                                        : row.get(arg1arg2[0].replaceAll("\\s+",
                                                "")).toString();
                        String value2 = arg1arg2[1];
                        Boolean val;

                        if (value1.equals("null")) {
                            if (!(operator.equals("=") &&
                                    (value2.equals("null")
                                            || value2.equals("0")))) {
                                throw new NullPointerException("Arguement error");
                            }
                        } else {
                            val = Boolean.parseBoolean(value1);
                            if (value2.equals("null")) {
                                if (!(val && operator.equals("="))) {
                                    throw new NullPointerException("Arguement error");
                                }
                            }
                            if (operator.equals("=")) {
                                result = val == Boolean.parseBoolean(arg1arg2[1]);
                            } else {
                                result = val != Boolean.parseBoolean(arg1arg2[1]);
                            }
                        }
                    } else {
                        if (j.contains("lastName")) {
                            String[] arg1arg2;
                            String operator;
                            if (j.contains("!=")) {
                                j = j.replace("!=", " ");
                                operator = "!=";
                                arg1arg2 = j.split("\\s+");
                            } else if (j.contains("=")) {
                                j = j.replace("=", " ");
                                operator = "=";
                                arg1arg2 = j.split("\\s+");
                            } else {
                                if (j.contains("ilike")) {
                                    j = j.replace("ilike", " ");
                                    operator = "ilike";
                                    arg1arg2 = j.split("\\s+");
                                } else if (j.contains("like")) {
                                    j = j.replace("!=", " ");
                                    operator = "like";
                                    arg1arg2 = j.split("\\s+");
                                } else {
                                    return false;
                                }

                            }
                            if (arg1arg2.length != 2) {
                                throw new NullPointerException("Arguement amount error");
                            }
                            String value1 = row.get(arg1arg2[0].replaceAll("\\s+",
                                    "")) == null ? "null"
                                            : row.get(arg1arg2[0].replaceAll("\\s+",
                                                    "")).toString();

                            String value2 = arg1arg2[1];

                            if (value1.equals("null")) {
                                if (!(operator.equals("=") &&
                                        (value2.equals("null")
                                                || value2.equals("0")))) {
                                    throw new NullPointerException("Arguement error");
                                }
                            } else if (value2.equals("null")) {
                                if (!(operator.equals("=")
                                        && (value1.equals("null")
                                                || value1.equals("0")))) {
                                    throw new NullPointerException("Arguement error");
                                }
                            }
                            result = logicString(value1.replace("'",
                                    ""),
                                    arg1arg2[1].replace("'",
                                            ""),
                                    operator);

                        }
                    }
                }
                if (!result) {
                    break;
                }
            }
            if (result) {
                return true;
            }
        }

        return false;
    }
}
