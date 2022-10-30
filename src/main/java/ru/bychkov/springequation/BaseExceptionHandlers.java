package ru.bychkov.springequation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bychkov.springequation.exceptions.IllegalEquationParamException;

import java.util.Map;

@ResponseBody
@ControllerAdvice
public class BaseExceptionHandlers {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IllegalEquationParamException.class)
    public Map<String, String> handleIllegalEquationException(IllegalEquationParamException e) {
        return Map.of("error", e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Map<String, String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return Map.of("error", e.getMessage());
    }

}
