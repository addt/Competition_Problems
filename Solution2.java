import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution2 {

    public static void main(String[] args) {

        final String _buffer = "*****";
        final String _phone = "P:";
        final String _email = "E:";

        Scanner _input = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();


        while (_input.hasNextLine()) {
            String interim = _input.nextLine().trim();
            try {
                String type = interim.substring(0, 2);

                switch (type) {
                    case "E:": {
                        String totrim = interim.substring(2, interim.length()).trim();
                        int index = totrim.indexOf('@');
                        int size = totrim.length();
                        String masked = "" + _email + totrim.charAt(0) + _buffer
                                + totrim.charAt(index - 1) + totrim.substring(index, size);
                        builder.append(masked + "\n");
                        break;
                    }

                    case "P:": {
                        String end = interim.substring(interim.length() - 4, interim.length());
                        String start = interim.substring(2, interim.length() - 4);
                        StringBuilder bldr = new StringBuilder(_phone);
                        boolean check = false;

                        for (char chars : start.toCharArray()) {
                            if (chars == '+') {
                                bldr.append("+");
                                check = true;
                                continue;
                            } else if (chars == '-') {
                                bldr.append("-");
                                continue;
                            } else if (chars == '(') {
                                if (check) {
                                    bldr.append("-");
                                }
                                continue;
                            } else if (chars == ')') {
                                bldr.append("-");
                                continue;
                            } else if (chars == ' ') {
                                continue;
                            } else {
                                bldr.append("*");
                            }
                        }
                        int checker = 0;

                        StringBuilder toappend = new StringBuilder();

                        for (char convert : bldr.toString().toCharArray()) {
                            if (convert == '*') {
                                checker += 1;
                                if (checker > 3) {
                                    toappend.append("-");
                                    toappend.append("*");
                                    checker = 0;
                                } else {
                                    toappend.append("*");
                                }
                            } else {
                                checker = 0;
                                toappend.append(convert);
                            }
                        }
                        if  (toappend.toString().endsWith("-")) {
                            toappend.append(end);
                        } else {
                            toappend.append("-");
                            toappend.append(end);
                        }
                        builder.append(toappend.toString() + "\n");
                        break;
                    }

                    default: {
                        break;
                    }
                }
            } catch (StringIndexOutOfBoundsException ex) {
                continue;
            }
        }
        System.out.println(builder.toString().trim());
    }
}
