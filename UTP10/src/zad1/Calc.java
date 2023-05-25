/**
 *
 *  @author Woźnicki Piotr SO0139
 *
 */

package zad1;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Calc {
    private BigDecimal number1;
    private BigDecimal number2;
    private String answer;
    private final Map<String, Runnable> messageProcessors = new HashMap<String,Runnable>() {{
       put(Constants.addition, () -> addition());
       put(Constants.subtraction, () -> subtraction());
       put(Constants.multiplication, () -> multiplication());
       put(Constants.division, () -> division());
    }};

    public String doCalc(String arg) {
        String[] tokens = arg.split(" ");
        number1 = new BigDecimal(tokens[0]);
        number2 = new BigDecimal(tokens[2]);

        messageProcessors.getOrDefault(tokens[1], () -> answer = "Invalid command to calc").run();

        return answer;
    }

    private void subtraction() {
        BigDecimal result = number1.subtract(number2);
        answer = result.toString();
    }

    private void addition() {
        BigDecimal result = number1.add(number2);
        answer = result.toString();
    }

    private void multiplication() {
        BigDecimal result = number1.multiply(number2);
        answer = result.toString();
    }

    private void division() {
        try {
            BigDecimal result = number1.divide(number2);
            answer = result.toString();
        } catch (ArithmeticException e) {
            BigDecimal result = number1.divide(number2,7, RoundingMode.HALF_UP);
            answer = result.toString();
        }
    }
}  
