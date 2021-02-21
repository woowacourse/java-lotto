package lotto.domain.ticketpurchase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserPurchaseTest {
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

    @DisplayName("모든 함수 반환 결과 확인 - 수동 구매 0개, 자동 구매 5개")
    @Test
    void Should_Return_AllCorrectResult_When_PurchaseManually0Tickets() {
        PurchasePrice purchasePrice = new PurchasePrice(5000);
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();

        UserPurchase userPurchase = new UserPurchase(purchasePrice, manuallyPurchasedLottoTickets);

        assertThat(userPurchase.getNumberOfAllTickets()).isEqualTo(5);
        assertThat(userPurchase.getManuallyPurchasedLottoTickets())
            .isEqualTo(manuallyPurchasedLottoTickets);
        assertThat(userPurchase.getNumberOfAutomaticallyPurchasedLottoTickets()).isEqualTo(5);
        assertThat(userPurchase.getPurchasePrice()).isEqualTo(purchasePrice);
    }

    @DisplayName("모든 함수 반환 결과 확인 - 수동 구매 5개, 자동 구매 0개")
    @Test
    void Should_Return_AllCorrectResult_When_PurchaseManually5Tickets() {
        PurchasePrice purchasePrice = new PurchasePrice(5000);
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        manuallyPurchasedLottoTickets.add(lottoTicket);
        manuallyPurchasedLottoTickets.add(lottoTicket);
        manuallyPurchasedLottoTickets.add(lottoTicket);
        manuallyPurchasedLottoTickets.add(lottoTicket);
        manuallyPurchasedLottoTickets.add(lottoTicket);

        UserPurchase userPurchase = new UserPurchase(purchasePrice, manuallyPurchasedLottoTickets);

        assertThat(userPurchase.getNumberOfAllTickets()).isEqualTo(5);
        assertThat(userPurchase.getManuallyPurchasedLottoTickets())
            .isEqualTo(manuallyPurchasedLottoTickets);
        assertThat(userPurchase.getNumberOfAutomaticallyPurchasedLottoTickets()).isEqualTo(0);
        assertThat(userPurchase.getPurchasePrice()).isEqualTo(purchasePrice);
    }

    @DisplayName("모든 함수 반환 결과 확인 - 수동 구매 2개, 자동 구매 3개")
    @Test
    void Should_Return_AllCorrectResult_When_PurchaseManually2Automatically3Tickets() {
        PurchasePrice purchasePrice = new PurchasePrice(5000);
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        manuallyPurchasedLottoTickets.add(lottoTicket);
        manuallyPurchasedLottoTickets.add(lottoTicket);

        UserPurchase userPurchase = new UserPurchase(purchasePrice, manuallyPurchasedLottoTickets);

        assertThat(userPurchase.getNumberOfAllTickets()).isEqualTo(5);
        assertThat(userPurchase.getManuallyPurchasedLottoTickets())
            .isEqualTo(manuallyPurchasedLottoTickets);
        assertThat(userPurchase.getNumberOfAutomaticallyPurchasedLottoTickets()).isEqualTo(3);
        assertThat(userPurchase.getPurchasePrice()).isEqualTo(purchasePrice);
    }
}
