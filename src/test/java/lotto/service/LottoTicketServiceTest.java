package lotto.service;

import lotto.domain.lottos.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketServiceTest {

    @Test
    @DisplayName("로또 티켓을 생성한다.")
    public void createLottoTicket() {
        assertThat(LottoTicketService.createAutoLottoTicket()).isInstanceOf(LottoTicket.class);
    }

    @Test
    @DisplayName("당첨 번호를 생성한다.")
    public void createLottoWinnerTicket() {
        LottoTicket lottoWinnerTicket = LottoTicketService.createLottoWinnerTicket("1,2,3,4,5,6");

        assertThat(lottoWinnerTicket).isInstanceOf(LottoTicket.class);
    }

    @Test
    @DisplayName("당첨 번호를 5개 입력하면 IllegalArgumanetException 발생")
    public void invalidLottoNumbersTest() {
        assertThatThrownBy(() -> {
            LottoTicketService.createLottoWinnerTicket("1,2,3,4,5");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(LottoTicketService.COUNT_ERROR_MESSAGE, LottoTicket.LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("당첨 번호 입력에 숫자이외의 것을 입력하면 NumberFormatException 발생")
    public void invalidNumberFormatTest() {
        assertThatThrownBy(() -> {
            LottoTicketService.createLottoWinnerTicket("1,2,3,4,이건문자열이다,6");
        }).isInstanceOf(NumberFormatException.class)
                .hasMessage(String.format(LottoTicketService.NUMBER_FORMAT_ERROR_MESSAGE, LottoTicket.LOTTO_NUMBER_SIZE));
    }
}
