package domain;


import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.Number.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @Test
    public void from_시작값_1_캐쉬된_인스턴스리턴() {
        int no = 1;
        Number preConstructedNumber = Number.from(no);

        assertThat(Number.from(no) == preConstructedNumber).isTrue();
    }

    @Test
    public void from_마지막값_45_캐쉬된_인스턴스리턴() {
        int no = 45;
        Number preConstructedNumber = Number.from(no);

        assertThat(Number.from(no) == preConstructedNumber).isTrue();
    }

    @Test
    public void from_시작범위초과_예외발생() {
        assertThrows(IllegalArgumentException.class, () -> Number.from(NUMBER_FROM - 1));
    }

    @Test
    public void from_끝범위초과_예외발생() {
        assertThrows(IllegalArgumentException.class, () -> Number.from(NUMBER_TO + 1));
    }

    @Test
    public void generateAllNumbers_시작값_포함여부() {
        List<Number> allNumbers = generateAllNumbers();

        assertThat(allNumbers.contains(Number.from(NUMBER_FROM))).isTrue();
    }

    @Test
    public void generateAllNumbers_마지막값_포함여부() {
        List<Number> allNumbers = generateAllNumbers();

        assertThat(allNumbers.contains(Number.from(NUMBER_TO))).isTrue();
    }

    @Test
    public void generateAllNumbers_올바른개수_리턴() {
        List<Number> allNumbers = generateAllNumbers();

        assertThat(allNumbers.size()).isEqualTo(NUMBER_TO - NUMBER_FROM + 1);
    }
}