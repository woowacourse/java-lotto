package lotto.domain.lottos.winnerlotto;

import lotto.domain.lottos.LottoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoWinnerTest {

    LottoTicket lottoWinnerTicket;
    LottoBonusNumber lottoBonusNumber;

    @BeforeEach
    void setUp() {
        lottoWinnerTicket = LottoTicket.createManualLottoTicket("1,2,3,4,5,45");
        lottoBonusNumber = LottoBonusNumber.of("9", lottoWinnerTicket);
    }

    @Test
    @DisplayName("로또 당첨 티켓을 생성한다.")
    public void createWinnerLottoTicketTest() {
        assertThat(new LottoWinner(lottoWinnerTicket, lottoBonusNumber)).isInstanceOf(LottoWinner.class);
    }

    @Test
    @DisplayName("Null은 생성자의 매개변수로 허용하지 않는다.")
    public void nullNotAllowedTest() {

        assertThatThrownBy(() -> {
            new LottoWinner(null, null);
        }).isInstanceOf(NullPointerException.class).hasMessage(LottoWinner.NULL_ERROR_MESSAGE);
    }
}
