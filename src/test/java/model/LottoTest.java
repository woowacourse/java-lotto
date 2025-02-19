package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static testfixture.LottoNumberFixture.convertToLottoNumbers;

import error.ErrorType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("로또 번호가 적절하게 생성된다.")
        @Test
        void createLottoNumbers() {
            // given
            List<LottoNumber> lottoNumbers = convertToLottoNumbers(1, 2, 3, 4, 5, 6);
            // when
            Lotto actual = new Lotto(lottoNumbers);

            // then
            assertThat(actual.getLottoNumbers()).isEqualTo(lottoNumbers);
        }
    }

    @Nested
    @DisplayName("유효하지 않은 경우의 테스트")
    class InvalidCases {

        @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다.")
        @Test
        void validateSize() {
            // given
            List<LottoNumber> lottoNumbers = convertToLottoNumbers(1, 2, 3, 4, 5, 6, 7);
            // when & then
            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.LOTTO_NUMBER_IS_INVALID_SIZE.getMessage());
        }

        @DisplayName("로또 번호에 중복이 존재한다면 예외가 발생한다.")
        @Test
        void validateDuplicate() {
            // given
            List<LottoNumber> lottoNumbers = convertToLottoNumbers(1, 2, 3, 4, 5, 5);

            // when & then
            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.LOTTO_NUMBER_DUPLICATE.getMessage());
        }
    }
}
