import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class P2 {

    public static void main(String args[]) {
        try {
            new P2(args).process();
            return;
        } catch (Exception excp) {
            excp.printStackTrace();
            System.err.printf("Error: %s%n", excp.getMessage());
        }
        System.exit(1);
    }

    P2(String[] args) {
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

        int index = 1;

        for (int i = 0; i < inputs.length; i = i + 2, index++) {
            if (inputs[i].equals("#")) {
                i--;
                index--;
                //System.out.println();
                continue;
            }
            String pre = inputs[i];
            String in = inputs[i + 1];
            String toprint = processInputs(pre, in);
            System.out.println("Case " + index + ": " + toprint);
            System.out.println();
        }
    }

    private String processInputs(String preorder, String inorder) {
        if (preorder.length() == 1) {
            return preorder;
        } else if (preorder.length() == 0) {
            return "";
        } else {
            String root = String.valueOf(preorder.charAt(0));

            int index = inorder.indexOf(root);
            String in1 = inorder.substring(0, index);
            String in2 = inorder.substring(index + 1, inorder.length());
            String pre1 = preorder.substring(1, in1.length() + 1);
            String pre2 = preorder.substring(in1.length() + 1, preorder.length());
            return (processInputs(pre1, in1) + processInputs(pre2, in2) + root);
        }
    }

    private Scanner _input;
}
