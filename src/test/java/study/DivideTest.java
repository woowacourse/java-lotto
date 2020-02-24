package study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DivideTest {

    @Test
    void studyDivide() {
        double number1 =  5_000;
        int number2 =  14_000;
        int result = (int)((number1 / number2) * 100);
        Assertions.assertThat(result).isEqualTo(35);
    }
}
