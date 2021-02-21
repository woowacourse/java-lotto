package domain.lotto;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @DisplayName("WinningLotto 정상 생성된다.")
    @Test
    void winningLotto_생성_테스트() {
        //given
        int[] lottoNumbers = {1, 2, 3, 4, 5, 6};
        int bonusNumber = 7;

        //when
        Set<Integer> winningNumbers = Arrays.stream(lottoNumbers)
                .boxed()
                .collect(Collectors.toSet());

        //then
        assertThatCode(() -> new WinningLotto(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoNumbers와 BonusNumber 중복 검사")
    @Test
    void winningLotto_중복_테스트() {
        //given
        int[] lottoNumbers = {1, 2, 3, 4, 5, 6};
        int bonusNumber = 6;

        //when
        Set<Integer> winningNumbers = Arrays.stream(lottoNumbers)
                .boxed()
                .collect(Collectors.toSet());

        //then
        assertThatCode(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("WinningBalls 갯수가 5개 이하이면 에러가 발생한다.")
    @Test
    void winningLottoWiningBallSizeTest() {
        //given
        int[] lottoNumbers = {1, 2, 3, 5, 5, 6};
        int bonusNumber = 6;

        //when
        Set<Integer> winningNumbers = Arrays.stream(lottoNumbers)
                .boxed()
                .collect(Collectors.toSet());

        //then
        assertThatCode(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
