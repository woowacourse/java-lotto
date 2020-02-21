package lotto.view.dto;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.exception.ConvertFailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoRequestDTOTest {

    private static final int DONT_CARE_NUMBER = 45;

    @DisplayName("입력받은 우승번호가 6개가 아닌경우")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5|5", "1,2,3,4,5,6,7|7"}, delimiter = '|')
    void getWinningNumbers(String winningNumbers, int size) {
        assertThatThrownBy(() -> new WinningLottoRequestDTO(winningNumbers, DONT_CARE_NUMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("볼 %d개, 입력한 볼의 갯수가 6개가 아닙니다.", size);
    }

    @DisplayName("입력받은 우승번호에 숫자가 아닌 문자가 존재하는 경우")
    @Test
    void getWinningNumbers1() {
        String winningNumbers = "1,2,3,4,5,a";

        assertThatThrownBy(() -> new WinningLottoRequestDTO(winningNumbers, DONT_CARE_NUMBER))
                .isInstanceOf(ConvertFailException.class)
                .hasMessage("%s : 숫자가 아닌 문자가 존재합니다.", winningNumbers);
    }

    @DisplayName("입력받은 우승번호를 Set 으로 반환")
    @Test
    void getWinningNumbers2() {
        //given
        String inputWinningNumbers = "1,2,3,4,5,6";

        //when
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(inputWinningNumbers, DONT_CARE_NUMBER);
        Set<Integer> winningNumbers = winningLottoRequestDTO.getWinningNumbers();

        //then
        assertThat(winningNumbers).contains(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("우승 로또 만들기")
    @Test
    void makeWinningLotto() {
        //given
        Set<LottoBall> lottoBalls = aLottoBalls(1, 2, 3, 4, 5, 6);
        LottoBall bonusBall = LottoBallFactory.findLottoBallByNumber(7);
        WinningLotto expectedLotto = new WinningLotto(lottoBalls, bonusBall);

        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO("1,2,3,4,5,6", 7);

        //when
        WinningLotto winningLotto = winningLottoRequestDTO.toWinningLotto();

        //then
        assertThat(winningLotto).isEqualTo(expectedLotto);
    }

    private Set<LottoBall> aLottoBalls(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoBallFactory::findLottoBallByNumber)
                .collect(Collectors.toSet());
    }
}