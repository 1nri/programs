/*
 * Any license applies.
 */
package tests.bankproject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author henrijuvonen
 * 
 * stolen on 11.1.2020
 * see the following guide for original reference:
 * https://stackoverflow.com/questions/46224683/how-to-display-output-to-jtextfield-from-system-out-println
 */
public class TestFrame {
    public static void main(String[] args) {
    JFrame frame = new JFrame();

    JTextField field = new JTextField();
    frame.add(field);

    frame.pack();
    frame.setVisible(true);

    OutputStream out = new OutputStream() {
        @Override
        public void write(int b) throws IOException {
        }
    };

    class JTextFieldPrintStream extends PrintStream {
        public JTextFieldPrintStream(OutputStream out) {
            super(out);
        }
        @Override
        public void println(String x) {
            field.setText(x);
        }
    };
    JTextFieldPrintStream print = new JTextFieldPrintStream(out);
    System.setOut(print);

    System.out.println("hello");
}
}
