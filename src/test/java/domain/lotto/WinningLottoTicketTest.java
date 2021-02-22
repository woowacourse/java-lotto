package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatCode;

public class WinningLottoTicketTest {

    @DisplayName("WinningLotto 정상 생성 테스트.")
    @Test
    void winningLottoGenerateTest() {
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

    @DisplayName("winningLotto LottoBalls의 수와 BonusBall의 수거 중복일 시 에러가 발생한다.")
    @Test
    void winningLottoDuplicateErrorTest() {
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
    void winningLottoBallSizeErrorTest() {
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
