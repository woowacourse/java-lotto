package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @Test
    @DisplayName("정상적인 로또 번호 생성")
    void inputLottoNumber() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertThat(lottoNumber.toInt()).isEqualTo(1);

        lottoNumber = new LottoNumber(45);
        assertThat(lottoNumber.toInt()).isEqualTo(45);
    }

    @ParameterizedTest
    @DisplayName("범위 밖의 로또 번호 생성시 예외 (정수)")
    @ValueSource(ints = {-1, 0, 46})
    void inputInvalidRangeIntegerLottoNumber(int input) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoNumber(input))
            .withMessage("범위 밖의 로또 번호 입니다.");
    }

    @ParameterizedTest
    @DisplayName("범위 밖의 로또 번호 생성시 예외 (문자열)")
    @ValueSource(strings = {"-1", "0", "46"})
    void inputInvalidRangeStringLottoNumber(String input) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoNumber(input))
            .withMessage("범위 밖의 로또 번호 입니다.");
    }
}
