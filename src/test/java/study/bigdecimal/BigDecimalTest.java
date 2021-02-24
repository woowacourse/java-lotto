package study.bigdecimal;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BigDecimalTest {
    private static final int SCALE = 2;

    @DisplayName("소수점 셋 째 자리에서 반올림 테스트 : 1 / 3 = 0.33333.. -> 0.33")
    @Test
    void Should_Return_RoundHalfUp_When_Floor() {
        BigDecimal result = new BigDecimal("1")
            .divide(new BigDecimal("3"), SCALE, BigDecimal.ROUND_HALF_UP);

        assertThat(result).isEqualByComparingTo(new BigDecimal("0.33"));
    }

    @DisplayName("소수점 셋 째 자리에서 반올림 테스트 : 5 / 3 = 1.66666.. -> 1.67")
    @Test
    void Should_Return_RoundHalfUp_When_Ceiling() {
        BigDecimal result = new BigDecimal("5")
            .divide(new BigDecimal("3"), SCALE, BigDecimal.ROUND_HALF_UP);

        assertThat(result).isEqualByComparingTo(new BigDecimal("1.67"));
    }

    @DisplayName("Scale 미지정 시, 소수점 첫 째 자리에서 반올림된다. : 1 / 3 = 0.33333.. -> 0")
    @Test
    void Should_Return_RoundHalfUp_When_ScaleEmptyFloor() {
        BigDecimal result = new BigDecimal("1")
            .divide(new BigDecimal("3"), BigDecimal.ROUND_HALF_UP);

        assertThat(result).isEqualByComparingTo(new BigDecimal("0"));
    }

    @DisplayName("Scale 미지정 시, 소수점 첫 째 자리에서 반올림된다. : 5 / 3 = 1.66666.. -> 2")
    @Test
    void Should_Return_RoundHalfUp_When_ScaleEmptyCeiling() {
        BigDecimal result = new BigDecimal("5")
            .divide(new BigDecimal("3"), BigDecimal.ROUND_HALF_UP);

        assertThat(result).isEqualByComparingTo(new BigDecimal("2"));
    }
}
