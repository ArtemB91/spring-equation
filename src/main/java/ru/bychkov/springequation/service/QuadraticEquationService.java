package ru.bychkov.springequation.service;

import ru.bychkov.springequation.dto.EquationResultDto;

public interface QuadraticEquationService {
    EquationResultDto evaluate(final float a, final float b, final float c);
}
