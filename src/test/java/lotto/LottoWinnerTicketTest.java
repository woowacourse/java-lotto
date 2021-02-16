package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoWinnerTicketTest {

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
        LottoWinnerBonusNumber lottoWinnerBonusNumber = new LottoWinnerBonusNumber(9);
        LottoWinnerTicket lottoWinnerTicket = new LottoWinnerTicket(lottoWinnerNumbers, lottoWinnerBonusNumber);

        List<LottoNumber> expectedLottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        LottoWinnerBonusNumber expectedLottoWinnerBonusNumber = new LottoWinnerBonusNumber(9);
        LottoWinnerTicket expectedLottoWinnerTicket =
                new LottoWinnerTicket(expectedLottoWinnerNumbers, expectedLottoWinnerBonusNumber);

        assertThat(lottoWinnerTicket).isEqualTo(expectedLottoWinnerTicket);
    }
}
