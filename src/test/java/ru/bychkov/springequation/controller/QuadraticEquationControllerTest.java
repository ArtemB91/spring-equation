package ru.bychkov.springequation.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import ru.bychkov.springequation.TestUtils;
import ru.bychkov.springequation.dto.EquationResultDto;
import ru.bychkov.springequation.model.QuadraticEquation;
import ru.bychkov.springequation.model.QuadraticEquationId;
import ru.bychkov.springequation.repository.QuadraticEquationRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class QuadraticEquationControllerTest {

    @Autowired
    QuadraticEquationRepository equationRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testEvaluateEquations() throws Exception {
        MockHttpServletResponse response1 = mockMvc.perform(
                        get("/v1/quadratic-equation?a={a}&b={b}&c={c}", 2, 5, -3))
                .andReturn()
                .getResponse();

        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());

        EquationResultDto result1 = TestUtils.fromJson(response1.getContentAsString(), new TypeReference<EquationResultDto>() {
        });

        assertThat(result1.getX1()).isEqualTo(0.5);
        assertThat(result1.getX2()).isEqualTo(-3);

        QuadraticEquation equation1 = equationRepository.findById(new QuadraticEquationId(2, 5, -3)).orElse(null);
        assertThat(equation1).isNotNull();


        MockHttpServletResponse response2 = mockMvc.perform(
                        get("/v1/quadratic-equation?a={a}&b={b}&c={c}", 1, -5, 4))
                .andReturn()
                .getResponse();

        EquationResultDto result2 = TestUtils.fromJson(response2.getContentAsString(), new TypeReference<EquationResultDto>() {
        });

        assertThat(response2.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result2.getX1()).isEqualTo(4);
        assertThat(result2.getX2()).isEqualTo(1);

        QuadraticEquation equation2 = equationRepository.findById(new QuadraticEquationId(1, -5, 4)).orElse(null);
        assertThat(equation2).isNotNull();
    }

    @Test
    void testEvaluateWithAEqual0_negative() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                        get("/v1/quadratic-equation?a={a}&b={b}&c={c}", 0, 1, 1))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());

        QuadraticEquation equation = equationRepository.findById(new QuadraticEquationId(0, 1, 1)).orElse(null);
        assertThat(equation).isNull();
    }

    @Test
    void testEvaluateWithNoSolutions_negative() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                        get("/v1/quadratic-equation?a={a}&b={b}&c={c}", 4, 1, 5))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());

        QuadraticEquation equation = equationRepository.findById(new QuadraticEquationId(4, 1, 5)).orElse(null);
        assertThat(equation).isNotNull();
        assertThat(equation.isNoSolutions()).isTrue();

    }


}
