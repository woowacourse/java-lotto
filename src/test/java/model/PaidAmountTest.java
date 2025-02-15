package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PaidAmountTest {

    private static final int VALID_UNIT = 1_000;

    @Test
    void 유효한_단위가_아닌_금액을_입력하는_경우_예외가_발생한다() {
        // given
        int invalidPaidAmount = VALID_UNIT + 1;

        // when & then
        assertThatThrownBy(() -> new PaidAmount(invalidPaidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
    }

    @Test
    void 구입_금액에_따라_개수를_계산한다() {
        // given
        int expectedResult = 14;
        PaidAmount paidAmount = new PaidAmount(expectedResult * VALID_UNIT);

        // when
        int result = paidAmount.getUnitCount();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }
}
