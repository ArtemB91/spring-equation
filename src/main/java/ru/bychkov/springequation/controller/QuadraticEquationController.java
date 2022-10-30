package ru.bychkov.springequation.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bychkov.springequation.dto.EquationResultDto;
import ru.bychkov.springequation.service.QuadraticEquationService;

@RestController
@RequestMapping(path = "v1/quadratic-equation")
@AllArgsConstructor
public class QuadraticEquationController {

    private final QuadraticEquationService service;

    @GetMapping
    public EquationResultDto evaluate(@RequestParam(name = "a") float a,
                                      @RequestParam(name = "b", required = false, defaultValue = "0") float b,
                                      @RequestParam(name = "c", required = false, defaultValue = "0") float c) {
        return service.evaluate(a, b, c);
    }



}
