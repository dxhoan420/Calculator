public class Expression {
    private final DualNumber first;
    private final char sign;
    private final DualNumber second;

    public Expression(String line) {
        String[] expression = line.split("\\s+");
        //Divide into 3 parts and check number
        if (expression.length != 3)
            throw new CalculatorWrongExpressionLengthException(line);
        //Fill each variable in order
        DualNumber first = new DualNumber(expression[0]);
        char sign = checkSign(expression[1]);
        DualNumber second = new DualNumber(expression[2]);
        //Check for only one type of number
        if (!first.equalsType(second))
            throw new CalculatorDifferentTypeException(line);
        this.first = first;
        this.sign = sign;
        this.second = second;
    }

    //Current version support only 4 sign for arithmetical operation: +,-,* or /
    private static char checkSign(String line) {
        if (line.length() != 1 || !"/*-+".contains(line))
            throw new CalculatorWrongSignException(line);
        else
            return line.charAt(0);
    }

    public DualNumber compute() {
        //Put null in type in case of different types of numbers
        DualNumber.DualType type = first.equalsType(second) ? first.getType() : null;
        return switch (sign) {
            case '+' -> new DualNumber(first.getNumber() + second.getNumber(), type);
            case '-' -> new DualNumber(first.getNumber() - second.getNumber(), type);
            case '*' -> new DualNumber(first.getNumber() * second.getNumber(), type);
            case '/' -> new DualNumber(first.getNumber() / second.getNumber(), type);
            //Unreachable piece of code
            default -> throw new CalculatorWrongSignException("WTF: \"" + this.sign + "\"");
        };
    }
}
