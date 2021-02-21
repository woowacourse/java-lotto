package domain.lotto;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @DisplayName("WinningLotto 정상 생성된다.")
    @Test
    void winningLotto_생성_테스트() {
        assertThatCode(() -> new WinningLotto(LottoBalls.generate(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoBall(7)))
                .doesNotThrowAnyException();
    }

    @DisplayName("WinningLotto내 LottoNumbers와 BonusNumber 중복 검사")
    @Test
    void winningLotto_중복_테스트() {
        assertThatThrownBy(() -> new WinningLotto(LottoBalls.generate(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoBall(6)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
