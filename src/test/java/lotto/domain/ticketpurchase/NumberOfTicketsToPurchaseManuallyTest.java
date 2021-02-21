package lotto.domain.ticketpurchase;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberOfTicketsToPurchaseManuallyTest {
    @DisplayName("0개 이상, 총 구매 티켓 개수 이하의 티켓을 수동구매할 때 정상")
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 10})
    void Should_Not_ThrowException_When_PurchaseValidNumberOfTicketsManually(
        int numberOfTicketsToPurchaseManually) {

        PurchasePrice purchasePrice = new PurchasePrice(10_000);

        Assertions.assertThatCode(
            () -> new NumberOfTicketsToPurchaseManually(
                numberOfTicketsToPurchaseManually, purchasePrice)
        ).doesNotThrowAnyException();
    }

    @DisplayName("0개 미만의 티켓을 수동구매할 때 예외 발생")
    @Test
    void Should_ThrowException_When_PurchaseLessThanZeroTicketsManually() {
        int numberOfTicketsToPurchaseManually = -1;
        PurchasePrice purchasePrice = new PurchasePrice(1000);

        Assertions.assertThatThrownBy(
            () -> new NumberOfTicketsToPurchaseManually(
                numberOfTicketsToPurchaseManually, purchasePrice)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 구매 티켓 개수 초과의 티켓을 수동구매할 때 예외 발생")
    @Test
    void Should_ThrowException_When_PurchaseGreaterThanAllNumberOfTicketsManually() {
        int numberOfTicketsToPurchaseManually = 2;
        PurchasePrice purchasePrice = new PurchasePrice(1000);

        Assertions.assertThatThrownBy(
            () -> new NumberOfTicketsToPurchaseManually(
                numberOfTicketsToPurchaseManually, purchasePrice)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}