package lotto.domain.result.win;

import lotto.LottoHelper;
import lotto.domain.result.MatchResult;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.number.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static lotto.LottoHelper.lottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    private static Stream<Arguments> numberProvider() {
        return Stream.of(
                Arguments.of(lottoNumbers(11, 12, 13, 18, 19, 20), new MatchResult(0, false)),
                Arguments.of(lottoNumbers(1, 12, 13, 18, 19, 20), new MatchResult(1, false)),
                Arguments.of(lottoNumbers(1, 2, 13, 18, 19, 20), new MatchResult(2, false)),
                Arguments.of(lottoNumbers(1, 2, 3, 8, 9, 10), new MatchResult(3, false)),
                Arguments.of(lottoNumbers(1, 2, 3, 4, 9, 10), new MatchResult(4, false)),
                Arguments.of(lottoNumbers(1, 2, 3, 4, 5, 10), new MatchResult(5, false)),
                Arguments.of(lottoNumbers(1, 2, 3, 4, 7, 10), new MatchResult(5, true)),
                Arguments.of(lottoNumbers(1, 2, 3, 4, 5, 6), new MatchResult(6, false))
        );
    }

    @DisplayName("우승 로또 티켓과 비교하기")
    @ParameterizedTest
    @MethodSource("numberProvider")
    void getResult(Set<LottoNumber> numbers, MatchResult expectedResult) {
        //given
        Set<Integer> winningNumbers = LottoHelper.numbers(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoTicket buyLottoTicket = new LottoTicket(numbers);

        //when
        MatchResult matchResult = winningLotto.getResult(buyLottoTicket);

        //then
        assertThat(matchResult).isEqualTo(expectedResult);
    }

    @DisplayName("우승 로또번호의 갯수가 잘못된 경우 Exception")
    @Test
    void validateWinningNumbers() {
        Set<Integer> winningNumbers = LottoHelper.numbers(1, 2, 3, 4, 5, 6, 7);

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, 11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력받은 갯수 %d : 우승 번호는 6개입니다.", 7);
    }

    @DisplayName("보너스 번호가 우승로또 번호와 겹칠 경우 Exception")
    @Test
    void validateBonusNumber() {
        Set<Integer> winningNumbers = LottoHelper.numbers(1, 2, 3, 4, 5, 6);
        int duplicatedBonusNumber = 6;

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, duplicatedBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호 %d는 중복된 번호입니다.", duplicatedBonusNumber);
    }
}
