package ru.bychkov.springequation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bychkov.springequation.model.QuadraticEquation;
import ru.bychkov.springequation.model.QuadraticEquationId;

@Repository
public interface QuadraticEquationRepository extends CrudRepository<QuadraticEquation, QuadraticEquationId> {

}
