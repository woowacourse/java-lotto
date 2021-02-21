package lotto.domain.ticketgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AllPurchasedLottoTicketsTest {
    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        lottoTicket = new LottoTicket(
            Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
            )
        );
    }

    @DisplayName("수동 구매 티켓, 자동 구매 티켓 구분 테스트 - 수동 2개, 자동 0개")
    @Test
    void Should_Return_ExactNumberOfTickets_When_Manually2Automatically0() {
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        manuallyPurchasedLottoTickets.add(lottoTicket);
        manuallyPurchasedLottoTickets.add(lottoTicket);

        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();

        AllPurchasedLottoTickets allPurchasedLottoTickets
            = new AllPurchasedLottoTickets(manuallyPurchasedLottoTickets,
            automaticallyPurchasedLottoTickets);

        assertThat(allPurchasedLottoTickets.getNumberOfManuallyPurchasedLottoTickets())
            .isEqualTo(2);
        assertThat(allPurchasedLottoTickets.getNumberOfAutomaticallyPurchasedLottoTickets())
            .isEqualTo(0);
        assertThat(allPurchasedLottoTickets.getAllTickets().size())
            .isEqualTo(2);
    }

    @DisplayName("수동 구매 티켓, 자동 구매 티켓 구분 테스트 - 수동 0개, 자동 3개")
    @Test
    void Should_Return_ExactNumberOfTickets_When_Manually0Automatically3() {
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();

        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();
        automaticallyPurchasedLottoTickets.add(lottoTicket);
        automaticallyPurchasedLottoTickets.add(lottoTicket);
        automaticallyPurchasedLottoTickets.add(lottoTicket);

        AllPurchasedLottoTickets allPurchasedLottoTickets
            = new AllPurchasedLottoTickets(manuallyPurchasedLottoTickets,
            automaticallyPurchasedLottoTickets);

        assertThat(allPurchasedLottoTickets.getNumberOfManuallyPurchasedLottoTickets())
            .isEqualTo(0);
        assertThat(allPurchasedLottoTickets.getNumberOfAutomaticallyPurchasedLottoTickets())
            .isEqualTo(3);
        assertThat(allPurchasedLottoTickets.getAllTickets().size())
            .isEqualTo(3);
    }

    @DisplayName("수동 구매 티켓, 자동 구매 티켓 구분 테스트 - 수동 2개, 자동 3개")
    @Test
    void Should_Return_ExactNumberOfTickets_When_Manually2Automatically3() {
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        manuallyPurchasedLottoTickets.add(lottoTicket);
        manuallyPurchasedLottoTickets.add(lottoTicket);

        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();
        automaticallyPurchasedLottoTickets.add(lottoTicket);
        automaticallyPurchasedLottoTickets.add(lottoTicket);
        automaticallyPurchasedLottoTickets.add(lottoTicket);

        AllPurchasedLottoTickets allPurchasedLottoTickets
            = new AllPurchasedLottoTickets(manuallyPurchasedLottoTickets,
            automaticallyPurchasedLottoTickets);

        assertThat(allPurchasedLottoTickets.getNumberOfManuallyPurchasedLottoTickets())
            .isEqualTo(2);
        assertThat(allPurchasedLottoTickets.getNumberOfAutomaticallyPurchasedLottoTickets())
            .isEqualTo(3);
        assertThat(allPurchasedLottoTickets.getAllTickets().size())
            .isEqualTo(5);
    }
}