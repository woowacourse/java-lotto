package domain.lotto;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoTest {
    @DisplayName("로또가 정상적으로 생성되는지 테스트")
    @Test
    void LottoConstructorTest() {
        assertThatCode(() -> new Lotto(Arrays.asList(
                LottoBall.valueOf(1),
                LottoBall.valueOf(2),
                LottoBall.valueOf(3),
                LottoBall.valueOf(4),
                LottoBall.valueOf(5),
                LottoBall.valueOf(6))))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호가 6개가 아니라면 에러를 발생시킨다")
    @Test
    void LottoCheckContainSixLottoBalls() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(
                LottoBall.valueOf(1),
                LottoBall.valueOf(2),
                LottoBall.valueOf(3),
                LottoBall.valueOf(4),
                LottoBall.valueOf(5))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 로또 번호가 필요합니다.");
    }

    @DisplayName("로또 번호가 중복된다면 에러를 발생시킨다")
    @Test
    void LottoCheckDuplicate() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(
                LottoBall.valueOf(1),
                LottoBall.valueOf(1),
                LottoBall.valueOf(2),
                LottoBall.valueOf(3),
                LottoBall.valueOf(4),
                LottoBall.valueOf(5))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호가 중복됩니다");
    }
}
