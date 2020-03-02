package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;
import domain.result.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("당첨 번호 객체 테스트")
public class WinningNumbersTest {

    @Test
    @DisplayName("당첨 결과 확인")
    void getLottoResultTest() {
        WinningNumbers winningNumbers = generateWinningNumbers(3, 4, 5, 6, 7, 8, 9);

        assertThat(winningNumbers.findLottoRank(generateLottoTicket(3, 4, 5, 6, 7, 8))).isEqualTo(LottoRank.FIRST);
        assertThat(winningNumbers.findLottoRank(generateLottoTicket(3, 4, 5, 6, 7, 9))).isEqualTo(LottoRank.SECOND);
        assertThat(winningNumbers.findLottoRank(generateLottoTicket(3, 4, 5, 6, 7, 10))).isEqualTo(LottoRank.THIRD);
        assertThat(winningNumbers.findLottoRank(generateLottoTicket(3, 4, 5, 6, 10, 11))).isEqualTo(LottoRank.FOURTH);
        assertThat(winningNumbers.findLottoRank(generateLottoTicket(3, 4, 5, 10, 11, 12))).isEqualTo(LottoRank.FIFTH);
        assertThat(winningNumbers.findLottoRank(generateLottoTicket(10, 11, 12, 13, 14, 15))).isEqualTo(LottoRank.NO_WIN);
    }

    WinningNumbers generateWinningNumbers(int number1, int number2, int number3, int number4, int number5, int number6, int bonus) {
        return new WinningNumbers(generateLottoTicket(number1, number2, number3, number4, number5, number6), LottoNumber.of(bonus));
    }

    LottoTicket generateLottoTicket(int number1, int number2, int number3, int number4, int number5, int number6) {
        List<Integer> integers = Arrays.asList(number1, number2, number3, number4, number5, number6);

        return integers.stream()
                .map(LottoNumber::of)
                .collect(collectingAndThen(toSet(), LottoTicket::new));
    }
}