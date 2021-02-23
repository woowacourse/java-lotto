package lotto.domain.ticketpurchase;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserPurchaseTest {
    private static final int ONE_TICKET_PRICE = 1000;

    @DisplayName("수동티켓 0개, 랜덤티켓 5개 테스트")
    @Test
    void Should_Return_ExpectedResults_When_Manual0Random5() {
        // given
        int expectedManualTicketsSize = 0;
        int expectedRandomTicketsSize = 5;
        int expectedAllTicketsSize = expectedManualTicketsSize + expectedRandomTicketsSize;
        PurchasePrice purchasePrice = new PurchasePrice(expectedAllTicketsSize * ONE_TICKET_PRICE);
        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(expectedManualTicketsSize, purchasePrice);

        // when
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);

        // then
        assertThat(userPurchase.getPurchasePrice()).isEqualTo(purchasePrice);
        assertThat(userPurchase.manualTicketsSize()).isEqualTo(expectedManualTicketsSize);
        assertThat(userPurchase.randomTicketsSize()).isEqualTo(expectedRandomTicketsSize);
        assertThat(userPurchase.allTicketsSize()).isEqualTo(expectedAllTicketsSize);
        assertThat(userPurchase.isPurchaseManually()).isFalse();
        assertThat(userPurchase.isPurchaseRandomly()).isTrue();
    }

    @DisplayName("수동티켓 5개, 랜덤티켓 0개 테스트")
    @Test
    void Should_Return_ExpectedResults_When_Manual5Random0() {
        // given
        int expectedManualTicketsSize = 5;
        int expectedRandomTicketsSize = 0;
        int expectedAllTicketsSize = expectedManualTicketsSize + expectedRandomTicketsSize;
        PurchasePrice purchasePrice = new PurchasePrice(expectedAllTicketsSize * ONE_TICKET_PRICE);
        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(expectedManualTicketsSize, purchasePrice);

        // when
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);

        // then
        assertThat(userPurchase.getPurchasePrice()).isEqualTo(purchasePrice);
        assertThat(userPurchase.manualTicketsSize()).isEqualTo(expectedManualTicketsSize);
        assertThat(userPurchase.randomTicketsSize()).isEqualTo(expectedRandomTicketsSize);
        assertThat(userPurchase.allTicketsSize()).isEqualTo(expectedAllTicketsSize);
        assertThat(userPurchase.isPurchaseManually()).isTrue();
        assertThat(userPurchase.isPurchaseRandomly()).isFalse();
    }

    @DisplayName("수동티켓 3개, 랜덤티켓 2개 테스트")
    @Test
    void Should_Return_ExpectedResults_When_Manual3Random2() {
        // given
        int expectedManualTicketsSize = 3;
        int expectedRandomTicketsSize = 2;
        int expectedAllTicketsSize = expectedManualTicketsSize + expectedRandomTicketsSize;
        PurchasePrice purchasePrice = new PurchasePrice(expectedAllTicketsSize * ONE_TICKET_PRICE);
        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(expectedManualTicketsSize, purchasePrice);

        // when
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);

        // then
        assertThat(userPurchase.getPurchasePrice()).isEqualTo(purchasePrice);
        assertThat(userPurchase.manualTicketsSize()).isEqualTo(expectedManualTicketsSize);
        assertThat(userPurchase.randomTicketsSize()).isEqualTo(expectedRandomTicketsSize);
        assertThat(userPurchase.allTicketsSize()).isEqualTo(expectedAllTicketsSize);
        assertThat(userPurchase.isPurchaseManually()).isTrue();
        assertThat(userPurchase.isPurchaseRandomly()).isTrue();
    }
}
