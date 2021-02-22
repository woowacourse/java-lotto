package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketFactoryTest {
    @Test
    @DisplayName("입력받은 금액만큼 자동티켓 생성")
    void createLottoTickets() {
        LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();
        assertThat(lottoTicketFactory.buyLottoTickets(new Money(10000)).size()).isEqualTo(10);
    }

    @DisplayName("수동로또 생성")
    @Test
    void createManualLottoTickets() {
        LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();
        List<String> numbers = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7"));
        assertThat(lottoTicketFactory.createManualLottoTicket(numbers)).containsExactly(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("7"));
    }
}