package lotto.domain.lottos.winnerlotto;

import lotto.domain.lottos.winnerlotto.LottoWinnerTicket;
import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.winnerlotto.LottoWinnerBonusNumber;
import lotto.domain.lottos.winnerlotto.LottoWinner;
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
        LottoWinnerBonusNumber lottoWinnerBonusNumber = new LottoWinnerBonusNumber(9, lottoWinnerTicket);

        assertThat(new LottoWinner(lottoWinnerTicket, lottoWinnerBonusNumber)).isInstanceOf(LottoWinner.class);
    }

    @Test
    @DisplayName("Null은 생성자의 매개변수로 허용하지 않는다.")
    public void nullNotAllowedTest() {

        assertThatThrownBy(() -> {
            new LottoWinner(null, null);
        }).isInstanceOf(NullPointerException.class).hasMessage("null 값은 허용하지 않습니다.");
    }
}
