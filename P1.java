import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.atomic.LongAdder;

public class P1 {

    public static void main(String args[]) {
        try {
            new P1(args).process();
            return;
        } catch (Exception excp) {
            excp.printStackTrace();
            System.err.printf("Error: %s%n", excp.getMessage());
        }
        System.exit(1);
    }

    P1(String[] args) {
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

        int nox = 0;
        int gap = 0;
        _imageno += 1;

        _notch = new ArrayList<String>();

        _dist = new ArrayList<Integer>();

        String input = "";
        while (_input.hasNextLine()) {
            String interim = _input.nextLine();
            if (interim.equals("")) {
                input = input + "#" + "\n";
            } else {
                input = input + interim + "\n";
            }
        }

        String[] inputdetails = input.trim().split("\n");

        for (String i : inputdetails) {
            if (i.startsWith("#")) {
                nox = 0;
                gap = 0;
                printImage();
                _imageno += 1;
                _notch = new ArrayList<String>();
                _dist = new ArrayList<Integer>();
            } else {
                _notch.add(i);
            }
        }
        printImage();
    }

    void printImage() {
        if (!(_notch.size() == 0)) {

            int nox = 0;
            int gap = 0;

            for (String s : _notch) {
                gap = 0;
                nox = 0;
                for (char c : s.trim().toCharArray()) {
                    if (c == 'X') {
                        nox += 1;
                    } else {
                        gap += 1;
                    }
                }
                _dist.add(gap);
            }

            int min = Collections.min(_dist);

            for (int i = 0; i < _dist.size(); i++) {
                _dist.set(i, _dist.get(i) - min);
            }

            int max = _dist.stream().mapToInt(Integer::intValue).sum();

            System.out.println("Image " + _imageno + ": " + max);
            System.out.println();
        }
    }

    private Scanner _input;

    private ArrayList<String> _notch;

    private ArrayList<Integer> _dist;

    private int _imageno;
}
