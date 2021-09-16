import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("I'm calculator! Please enter arithmetical expression.");
        System.out.println("Example: a + b, a - b, a * b, a / b.");
        System.out.println("Remember \"a\" and \"b\" be can be only Arabic or only Roman numbers");
        System.out.println("Use \"Ctrl + D\" for exit!");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            //Read from terminal until Ctrl+D
            while ((line = reader.readLine()) != null) {
                try {
                    Expression expression = new Expression(line);
                    System.out.println(expression.compute());
                } catch (ArithmeticException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
