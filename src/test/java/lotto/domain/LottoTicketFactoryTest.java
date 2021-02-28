package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketFactoryTest {

    @DisplayName("수동로또 생성")
    @Test
    void createManualLottoTickets() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        assertThat(LottoTicketFactory.createManualLottoTicket(numbers).getLottoTicket()).containsExactly(
                LottoNumber.of("1"),
                LottoNumber.of("2"),
                LottoNumber.of("3"),
                LottoNumber.of("4"),
                LottoNumber.of("5"),
                LottoNumber.of("6"));
    }

    @DisplayName("수동로또 정렬기능 검증")
    @Test
    void sortManualLottoTickets() {
        List<String> numbers = new ArrayList<>(Arrays.asList("45", "2", "3", "5", "4", "1"));
        assertThat(LottoTicketFactory.createManualLottoTicket(numbers).getLottoTicket()).containsExactly(
                LottoNumber.of("1"),
                LottoNumber.of("2"),
                LottoNumber.of("3"),
                LottoNumber.of("4"),
                LottoNumber.of("5"),
                LottoNumber.of("45"));
    }

    @Test
    @DisplayName("입력받은 금액만큼 수동티켓과 자동티켓 생성")
    void createAutoAndManualLottoTickets() {
        List<String> lottoTickets = new ArrayList<>();
        lottoTickets.add("1,2,3,4,5,6");
        lottoTickets.add("11,12,13,14,15,16");
        assertThat(LottoTicketFactory.createLottoTicketsIncludingManualTickets(new Money(10000), lottoTickets).getLottoTickets())
                .hasSize(10);
    }
}