package lotto.domain.LottoRule;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exceptions.InvalidLottoNumberException;

class LottoBallsTest {

    private static final int LOTTO_BALLS_LENGTH = 45;

    @Test
    @DisplayName("당첨 번호 입력이 유효한 경우 잘 찾아내는지")
    void find() {
        assertThat(LottoBalls.find("1").getValue()).isEqualTo(new LottoNumber(1).getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "abc"})
    @DisplayName("당첨 번호 입력이 유효하지 않은 경우 예외를 발생시키는지")
    void invalidFindWithNumber(String number) {
        assertThatThrownBy(() -> LottoBalls.find(number)
        ).isInstanceOf(InvalidLottoNumberException.class);
    }

    @Test
    @DisplayName("로또 볼이 정확한 갯수대로 생성되었는지")
    void lottoBallSize() {
        assertThat(LottoBalls.getLottoBalls().size()).isEqualTo(LOTTO_BALLS_LENGTH);
    }
}
