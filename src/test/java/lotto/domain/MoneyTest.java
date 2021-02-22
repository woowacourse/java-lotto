package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.utils.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("돈으로 살 수 있는 티켓 갯수 반환 - 나누어 떨어지는 경우")
    void getPossibleTicketCount() {
        assertThat(new Money("3000").getPossibleTicketCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("돈으로 살 수 있는 티켓 갯수 반환 - 나누어 떨어지지 않는 경우")
    void getPossibleTicketCount1() {
        assertThat(new Money("3100").getPossibleTicketCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("실패 - 돈이 정수가 아닌 경우")
    void generate() {
        assertThatThrownBy(() -> new Money("3100.1"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 돈이 숫자가 아닌 경우")
    void generate1() {
        assertThatThrownBy(() -> new Money("12ls"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("수동발행")
    void generate_analog() {
        List<LottoTicket> lottoTickets = Arrays.asList(
            new LottoTicket("11,12,13,14,15,16"),
            new LottoTicket("21,22,23,24,25,26"),
            new LottoTicket("31,32,33,34,35,36"));

        assertThatCode(() -> new Money("3000", lottoTickets))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 수동발행이 구매금액을 넘어가는 경우")
    void generate_analog1() {
        List<LottoTicket> lottoTickets = Arrays.asList(
            new LottoTicket("11,12,13,14,15,16"),
            new LottoTicket("21,22,23,24,25,26"),
            new LottoTicket("31,32,33,34,35,36"));

        assertThatThrownBy(() -> new Money("2000", lottoTickets))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("수동발행갯수만큼 차감")
    void getPossibleTicket() {
        List<LottoTicket> lottoTickets = Arrays.asList(
            new LottoTicket("11,12,13,14,15,16"),
            new LottoTicket("21,22,23,24,25,26"),
            new LottoTicket("31,32,33,34,35,36"));

        final Money money = new Money("14000", lottoTickets);
        assertThat(money.getPossibleTicketCount()).isEqualTo(11);
    }

    @Test
    @DisplayName("수동발행갯수만큼 차감 - 자동발행 불가능 경우")
    void getPossibleTicket1() {
        List<LottoTicket> lottoTickets = Arrays.asList(
            new LottoTicket("11,12,13,14,15,16"),
            new LottoTicket("21,22,23,24,25,26"),
            new LottoTicket("31,32,33,34,35,36"));

        final Money money = new Money("3000", lottoTickets);
        assertThat(money.getPossibleTicketCount()).isEqualTo(0);
    }

}