package ru.bychkov.springequation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quadratic_equations")
@IdClass(QuadraticEquationId.class)
@Getter
public class QuadraticEquation {

    @Id
    private float a;

    @Id
    private float b;

    @Id
    private float c;

    private double x1;

    private double x2;

    private boolean noSolutions;

    public QuadraticEquation(float a, float b, float c, double x1, double x2) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.x1 = x1;
        this.x2 = x2;
    }
}
