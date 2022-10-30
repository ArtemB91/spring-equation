package ru.bychkov.springequation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bychkov.springequation.dto.EquationResultDto;
import ru.bychkov.springequation.exceptions.IllegalEquationParamException;
import ru.bychkov.springequation.model.QuadraticEquation;
import ru.bychkov.springequation.model.QuadraticEquationId;
import ru.bychkov.springequation.repository.QuadraticEquationRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class QuadraticEquationServiceImpl implements QuadraticEquationService{

    private final QuadraticEquationRepository repository;

    @Override
    public EquationResultDto evaluate(final float a, final float b, final float c) {

        if (a == 0)
            throw IllegalEquationParamException.A_REQUIRED_NON_ZERO;

        Optional<QuadraticEquation> cacheEquation = findById(a, b, c);
        if (cacheEquation.isPresent()) {
            QuadraticEquation equation = cacheEquation.get();
            if (equation.isNoSolutions())
                throw IllegalEquationParamException.NO_SOLUTIONS;
            return convertToEquationResultDto(equation);
        }

        double[] xs = calculateResult(a, b, c);
        if (xs.length == 0) {
            QuadraticEquation equation = new QuadraticEquation(a, b, c, 0, 0, true);
            repository.save(equation);
            throw IllegalEquationParamException.NO_SOLUTIONS;
        }

        QuadraticEquation equation = new QuadraticEquation(a, b, c, xs[0], xs[1]);
        return convertToEquationResultDto(repository.save(equation));
    }

    private Optional<QuadraticEquation> findById(final float a, final float b, final float c) {
        QuadraticEquationId equationId = new QuadraticEquationId(a, b, c);
        return repository.findById(equationId);
    }


    // Возврат лучше сделать через Pair<Double, Double>, если подключить зависимость Apache Common
    // Движок для расчета уравнений лучше инкапсулировать отдельно, но такой небольшой объем кода решил не обособлять
    private double[] calculateResult(final float a, final float b, final float c) {

        if (a == 0)
            throw new IllegalArgumentException("a must not be zero");

        double x1, x2, discriminant;

        discriminant = (double) b * b - (double) 4 * a * c;

        if (discriminant > 0) {
            x1 = (-b + Math.sqrt(discriminant)) / (2 * a) + 0.0;
            x2 = (-b - Math.sqrt(discriminant)) / (2 * a) + 0.0;
            return new double[]{x1, x2};
        }

        if (discriminant == 0) {
            x1 = (double) -b / (2 * a) + 0.0;
            return new double[]{x1, x1};
        }

        return new double[0];
    }

    private EquationResultDto convertToEquationResultDto(final QuadraticEquation equation) {
        return new EquationResultDto(equation.getX1(), equation.getX2());
    }


}
