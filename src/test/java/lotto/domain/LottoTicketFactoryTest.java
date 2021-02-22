package lotto.domain;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketFactoryTest {
    private LottoTicket firstTicket;
    private LottoTicket secondTicket;
    private List<LottoTicket> manualLottoTickets;

    @BeforeEach
    void setUp() {
        firstTicket = new LottoTicket(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("7")));

        secondTicket = new LottoTicket(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("8")));

        manualLottoTickets = Arrays.asList(firstTicket, secondTicket);
    }

    @Test
    @DisplayName("입력받은 금액만큼 티켓 생성 - 자동 로또만")
    void createLottoTickets1() {
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();
        assertThat(lottoTicketFactory.generateLottoTickets(10, manualLottoTickets)
                .lottoTickets().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("입력받은 금액만큼 티켓 생성 - 수동 로또만")
    void createLottoTickets2() {
        LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();
        assertThat(lottoTicketFactory.generateLottoTickets(0, manualLottoTickets)
                .lottoTickets().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("입력받은 금액만큼 티켓 생성 - 수동, 자동 로또")
    void createLottoTickets3() {
        LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();
        assertThat(lottoTicketFactory.generateLottoTickets(2, manualLottoTickets)
                .lottoTickets().size()).isEqualTo(4);
    }
}