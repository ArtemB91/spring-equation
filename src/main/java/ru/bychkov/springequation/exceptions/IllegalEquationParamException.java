package ru.bychkov.springequation.exceptions;

public class IllegalEquationParamException extends RuntimeException {

    public static final IllegalEquationParamException NO_SOLUTIONS = new IllegalEquationParamException("There are no solutions to the equation");
    public static final IllegalEquationParamException A_REQUIRED_NON_ZERO = new IllegalEquationParamException("a must not be zero");

    public IllegalEquationParamException(String message) {
        super(message);
    }
}
