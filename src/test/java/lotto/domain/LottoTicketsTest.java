package lotto.domain;

import lotto.service.LottoTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketsTest {
    public LottoTickets lottoTickets;

    @BeforeEach
    public void initTickets() {
        lottoTickets = new LottoTickets(Arrays.asList(
                LottoTicketService.createTicket(LottoNumber.getRandomNumbers()),
                LottoTicketService.createTicket(LottoNumber.getRandomNumbers()),
                LottoTicketService.createTicket(LottoNumber.getRandomNumbers())
        ));
    }

    @ParameterizedTest(name = "Null은 생성자의 매개변수로 허용하지 않는다.")
    @NullSource
    public void nullParameterTest(List<LottoTicket> lottoTickets) {
        assertThatThrownBy(() -> {
            new LottoTickets(lottoTickets);
        }).isInstanceOf(NullPointerException.class).hasMessage("null 값은 허용하지 않습니다.");
    }

    @Test
    @DisplayName("구매한 여러장의 로또 티켓들을 가진 객체를 생성한다.")
    public void createTicketsTest() {
        assertThat(lottoTickets).isInstanceOf(LottoTickets.class);
    }

    @Test
    @DisplayName("구매한 로또 티켓의 갯수만큼 로또가 생성 되었는지 확인한다.")
    public void ticketsCountTest() {
        assertThat(lottoTickets.getTickets().size()).isEqualTo(3);
    }
}
