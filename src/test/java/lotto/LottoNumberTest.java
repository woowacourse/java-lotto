package lotto;

import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @DisplayName("범위 밖의 로또 번호 생성 시 예외 발생 확인")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void checkLottoRangeTest(int number) {
        assertThatThrownBy(() -> {
            LottoNumber.newLottoNumber(number);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자 범위를 넘어섰습니다.");
    }
}
