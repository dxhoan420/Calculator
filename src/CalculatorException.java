class CalculatorException extends ArithmeticException {

}

class CalculatorWrongFormatException extends CalculatorException {
    CalculatorWrongFormatException(String line) {
        super();
        System.out.println("Wrong format of Roman number: \"" + line + "\"");
    }
}

class CalculatorWrongSignException extends CalculatorException {
    CalculatorWrongSignException(String line) {
        super();
        System.out.println("Wrong type of arithmetical sign: \"" + line + "\"");
    }
}

class CalculatorWrongExpressionLengthException extends CalculatorException {
    CalculatorWrongExpressionLengthException(String line) {
        super();
        System.out.println("Supported expressions: a + b, a - b, a * b, a / b.");
        System.out.println("Your expression is not supported: \"" + line + "\"");
    }
}

class CalculatorDifferentTypeException extends CalculatorException {
    CalculatorDifferentTypeException(String line) {
        super();
        System.out.println("Number types are non equal: \"" + line + "\"");
    }
}

class CalculatorRomanNotNaturalException extends CalculatorException {
    CalculatorRomanNotNaturalException(int number) {
        super();
        System.out.println("Number cannot be represented as Roman: \"" + number + "\"");
    }
}

