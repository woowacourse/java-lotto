package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "-1"})
    @DisplayName("숫자가 범위 내에 존재하지 않는 경우 예외 발생 생성자 사용")
    void incorrectCase1(int invalidLottoNumber) {
        assertThatThrownBy(() -> new LottoNumber(invalidLottoNumber)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자는 1 이상 45 이하의 숫자만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "-1"})
    @DisplayName("숫자가 범위 내에 존재하지 않는 경우 예외 발생 정적 팩토리 메소드 사용")
    void incorrectCase2(int invalidLottoNumber) {
        assertThatThrownBy(() -> LottoNumber.valueOf(invalidLottoNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "25", "45"})
    @DisplayName("숫자가 범위 내에서 생성됨 테스트 통과")
    void correctCase1(int validLottoNumber) {
        assertThatNoException().isThrownBy(() -> new LottoNumber(validLottoNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "25", "45"})
    @DisplayName("숫자가 범위 내에서 생성됨 테스트 통과")
    void correctCase2(int validLottoNumber) {
        assertThatNoException().isThrownBy(() -> LottoNumber.valueOf(validLottoNumber));
    }
}
