package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoWinnerTest {

    @Test
    @DisplayName("로또 당첨 티켓을 생성한다.")
    public void createWinnerLottoTicketTest() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        LottoWinnerTicket lottoWinnerTicket = new LottoWinnerTicket(lottoWinnerNumbers);
        LottoWinnerBonusNumber lottoWinnerBonusNumber = new LottoWinnerBonusNumber(9);

        List<LottoNumber> expectedLottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        LottoWinnerTicket expectedLottoWinnerTicket = new LottoWinnerTicket(expectedLottoWinnerNumbers);
        LottoWinnerBonusNumber expectedLottoWinnerBonusNumber = new LottoWinnerBonusNumber(9);

        assertThat(new LottoWinner(lottoWinnerTicket, lottoWinnerBonusNumber))
                .isEqualTo(new LottoWinner(expectedLottoWinnerTicket, expectedLottoWinnerBonusNumber));
    }

    @Test
    @DisplayName("6개의 당첨 숫자 내에 중복이 있으면 예외 발생")
    public void duplicateWinnerNumberTest() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> {
            new LottoWinner(new LottoWinnerTicket(lottoWinnerNumbers), new LottoWinnerBonusNumber(9));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 숫자가 6개의 당첨 숫자 내에 중복이 있으면 예외 발생")
    public void WhenBonusNumberInWinnerNumberThrowsException() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        assertThatThrownBy(() -> {
            new LottoWinner(new LottoWinnerTicket(lottoWinnerNumbers), new LottoWinnerBonusNumber(6));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
