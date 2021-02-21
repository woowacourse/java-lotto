package lotto.domain.ticketgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.UserPurchase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {
    @DisplayName("전체 수동 구매 결과 테스트")
    @Test
    void Should_Return_LottoTicketsWithExactTickets_When_PurchaseAllManually() {
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        LottoTicket manuallyPurchasedLottoTicket1
            = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        ));
        LottoTicket manuallyPurchasedLottoTicket2
            = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        ));
        manuallyPurchasedLottoTickets.add(manuallyPurchasedLottoTicket1);
        manuallyPurchasedLottoTickets.add(manuallyPurchasedLottoTicket2);

        UserPurchase userPurchase
            = new UserPurchase(new PurchasePrice(2_000), manuallyPurchasedLottoTickets);
        LottoGenerator lottoGenerator = new LottoGenerator();

        AllPurchasedLottoTickets allPurchasedLottoTickets
            = lottoGenerator.purchaseTickets(userPurchase);

        assertThat(allPurchasedLottoTickets.getNumberOfManuallyPurchasedLottoTickets())
            .isEqualTo(2);
        assertThat(allPurchasedLottoTickets.getNumberOfAutomaticallyPurchasedLottoTickets())
            .isEqualTo(0);
    }

    @DisplayName("전체 자동 구매 결과 테스트")
    @Test
    void Should_Return_LottoTicketsWithExactTickets_When_PurchaseAllAutomatically() {
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        UserPurchase userPurchase
            = new UserPurchase(new PurchasePrice(2_000), manuallyPurchasedLottoTickets);
        LottoGenerator lottoGenerator = new LottoGenerator();

        AllPurchasedLottoTickets allPurchasedLottoTickets
            = lottoGenerator.purchaseTickets(userPurchase);

        assertThat(allPurchasedLottoTickets.getNumberOfManuallyPurchasedLottoTickets())
            .isEqualTo(0);
        assertThat(allPurchasedLottoTickets.getNumberOfAutomaticallyPurchasedLottoTickets())
            .isEqualTo(2);
    }

    @DisplayName("일부 수동 구매 결과 테스트")
    @Test
    void Should_Return_LottoTicketsWithExactTickets_When_PurchaseSomeTicketsManually() {
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        LottoTicket manuallyPurchasedLottoTicket1
            = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        ));
        LottoTicket manuallyPurchasedLottoTicket2
            = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        ));
        manuallyPurchasedLottoTickets.add(manuallyPurchasedLottoTicket1);
        manuallyPurchasedLottoTickets.add(manuallyPurchasedLottoTicket2);

        UserPurchase userPurchase
            = new UserPurchase(new PurchasePrice(5_000), manuallyPurchasedLottoTickets);
        LottoGenerator lottoGenerator = new LottoGenerator();

        AllPurchasedLottoTickets allPurchasedLottoTickets
            = lottoGenerator.purchaseTickets(userPurchase);

        assertThat(allPurchasedLottoTickets.getNumberOfManuallyPurchasedLottoTickets())
            .isEqualTo(2);
        assertThat(allPurchasedLottoTickets.getNumberOfAutomaticallyPurchasedLottoTickets())
            .isEqualTo(3);
    }
}
