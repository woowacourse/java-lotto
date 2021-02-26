package domain.result;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WinningLottoTest {
    @DisplayName("WinningLotto 생성 테스트")
    @Test
    void WinningLottoConstructor() {
        Lotto lotto = makeProperLotto();
        LottoBall lottoBall = LottoBall.valueOf(7);
        assertThatCode(() -> new WinningResult(lotto, lottoBall))
                .doesNotThrowAnyException();
    }

    @DisplayName("WinningLotto 보너스 볼이 당첨 번호에 포함된 경우 에러 발생")
    @Test
    void WinningLottoErrorTest() {
        Lotto lotto = makeProperLotto();
        LottoBall lottoBall = LottoBall.valueOf(1);
        assertThatThrownBy(() -> new WinningResult(lotto, lottoBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
    }

    private Lotto makeProperLotto() {
        return Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
    }
}
