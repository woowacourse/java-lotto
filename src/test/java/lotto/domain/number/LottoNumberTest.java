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
        LottoNumber lottoNumber = LottoNumber.valueOf("1");
        assertThat(lottoNumber.unwrap()).isEqualTo(1);

        lottoNumber = LottoNumber.valueOf("45");
        assertThat(lottoNumber.unwrap()).isEqualTo(45);
    }

    @ParameterizedTest
    @DisplayName("불가능한 로또 번호 생성시 예외")
    @ValueSource(strings = {"-1", "0", "46", "a", "abc"})
    void inputInvalidRangeStringLottoNumber(String input) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumber.valueOf(input))
            .withMessage("불가능한 로또 번호입니다.");
    }
}