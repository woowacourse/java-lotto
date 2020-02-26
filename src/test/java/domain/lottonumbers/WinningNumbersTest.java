package domain.lottonumbers;

import domain.result.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("당첨 번호 객체 테스트")
public class WinningNumbersTest {

    @Test
    @DisplayName("당첨 결과 확인")
    void getLottoResultTest() {
        WinningNumbers winningNumbers = createWinningNumbers(3, 4, 5, 6, 7, 8, 9);

        assertThat(winningNumbers.findLottoRank(createLottoTicket(3, 4, 5, 6, 7, 8))).isEqualTo(LottoRank.FIRST);
        assertThat(winningNumbers.findLottoRank(createLottoTicket(3, 4, 5, 6, 7, 9))).isEqualTo(LottoRank.SECOND);
        assertThat(winningNumbers.findLottoRank(createLottoTicket(3, 4, 5, 6, 7, 10))).isEqualTo(LottoRank.THIRD);
        assertThat(winningNumbers.findLottoRank(createLottoTicket(3, 4, 5, 6, 10, 11))).isEqualTo(LottoRank.FOURTH);
        assertThat(winningNumbers.findLottoRank(createLottoTicket(3, 4, 5, 10, 11, 12))).isEqualTo(LottoRank.FIFTH);
        assertThat(winningNumbers.findLottoRank(createLottoTicket(10, 11, 12, 13, 14, 15))).isEqualTo(LottoRank.NO_WIN);
    }

    private WinningNumbers createWinningNumbers(int number1, int number2, int number3, int number4, int number5, int number6, int bonus) {
        Set<Integer> expectedRawInput = new HashSet<>(Arrays.asList(number1, number2, number3, number4, number5, number6));
        return new WinningNumbers(new WinningNumbersDto(expectedRawInput, bonus));
    }

    private LottoTicket createLottoTicket(int number1, int number2, int number3, int number4, int number5, int number6) {
        Set<Integer> expectedRawInput = new HashSet<>(Arrays.asList(number1, number2, number3, number4, number5, number6));
        return new LottoTicket(new LottoTicketDto(expectedRawInput));
    }
}