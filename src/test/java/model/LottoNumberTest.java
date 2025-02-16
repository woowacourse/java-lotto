package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import error.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("로또 번호가 올바르게 생성된다.")
        @Test
        void createLottoNumber() {
            // given
            int number = 1;

            // when
            LottoNumber actual = new LottoNumber(number);

            // then
            assertThat(actual.getNumber()).isEqualTo(number);
        }

    }

    @Nested
    @DisplayName("유효하지 않은 경우의 테스트")
    class InvalidCases {

        @DisplayName("로또 번호가 범위를 벗어나면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {Lotto.LOTTO_NUMBER_START_INCLUSIVE - 1, Lotto.LOTTO_NUMBER_END_INCLUSIVE + 1})
        void validateRange(final int inclusive) {
            // given & when & then
            assertThatThrownBy(() -> new LottoNumber(inclusive))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.LOTTO_NUMBER_RANGE.getMessage());
        }
    }
}
