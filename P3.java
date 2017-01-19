import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.function.Predicate;

public class P3 {

    public static void main(String args[]) {
        try {
            new P3(args).process();
            return;
        } catch (Exception excp) {
            excp.printStackTrace();
            System.err.printf("Error: %s%n", excp.getMessage());
        }
        System.exit(1);
    }

    P3(String[] args) {
        if (args.length > 0) {
            try {
                _input = getInput(args[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            _input = new Scanner(System.in);
        }
    }

    /**
     * Return a Scanner reading from the file named NAME.
     */
    private Scanner getInput(String name) throws IOException {
        return new Scanner(new File(name));
    }

    private void process() {

        String input = "";
        while (_input.hasNextLine()) {
            String interim = _input.nextLine();
            String append = "";
            if (interim.equals("")) {
                input = input + "#" + "\n";
            } else {
                for (String j : interim.split("\\s+")) {
                    input = input + j + "\n";
                }
            }
        }

        String[] inputs = input.split("\n");

        String[] parts = {"1", "2", "3"};

        _torem = 0;

        for (String i : inputs) {
            _torem = 0;
            int size = Integer.parseInt(i);
            ArrayList<String> interim = new ArrayList<>();
            Predicate<String> filter = new Predicate<String>() {
                @Override
                public boolean test(String s) {
                    return (s.length() <= _torem);
                }
            };

            for (String k : parts) {
                interim.add(k);
            }

            for (int j = 1; j < size; j++) {
                ArrayList<String> toadd = new ArrayList<>();
                for (String z : interim) {
                    for (String t : parts) {
                        if (!check(z + t)) {
                            toadd.add(z + t);
                        }
                    }
                }
                _torem += 1;
                interim.removeIf(filter);
                for (String to : toadd) {
                    interim.add(to);
                }
            }

            String minvalue = Collections.min(interim);
            System.out.println("The smallest good numeral of length " + i + " is " + minvalue + ".");
        }
    }

    private boolean check(String message) {
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < i; j++) {
                if ((i + i - j) > message.length()) {
                    continue;
                }
                String interim1 = message.substring(j, i);
                String interim2 = message.substring(i, i + i - j);
                if (interim1.equals(interim2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Scanner _input;

    private int _torem;

}
