package lotto.domain.ticketpurchase;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.exception.InvalidManualTicketsSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ManualTicketsSizeTest {
    @DisplayName("수동구매 티켓 개수가 0개 이상, 총 구매 티켓 개수 이하일 때 정상")
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 10})
    void Should_Not_ThrowException_When_ManualTicketsSizeIsValid(int manualTicketsSize) {
        PurchasePrice purchasePrice = new PurchasePrice(10_000);

        assertThatCode(
            () -> new ManualTicketsSize(manualTicketsSize, purchasePrice)
        ).doesNotThrowAnyException();
    }

    @DisplayName("수동구매 티켓 개수가 0개 미만일 때 예외 발생")
    @Test
    void Should_ThrowException_When_ManualTicketsSizeIsLessThanZero() {
        int manualTicketsSize = -1;
        PurchasePrice purchasePrice = new PurchasePrice(1_000);

        assertThatThrownBy(
            () -> new ManualTicketsSize(manualTicketsSize, purchasePrice)
        ).isInstanceOf(InvalidManualTicketsSizeException.class);
    }

    @DisplayName("수동구매 티켓 개수가 총 구매 티켓 개수보다 클 때 예외 발생")
    @Test
    void Should_ThrowException_When_ManualTicketsSizeIsGreaterThanAllTickets() {
        int manualTicketsSize = 2;
        PurchasePrice purchasePrice = new PurchasePrice(1_000);

        assertThatThrownBy(
            () -> new ManualTicketsSize(manualTicketsSize, purchasePrice)
        ).isInstanceOf(InvalidManualTicketsSizeException.class);
    }

    @DisplayName("수동구매 티켓 개수 확인 테스트")
    @Test
    void Should_Return_ExpectedSize_When_GetSize() {
        // given
        int manualTicketsSizeInput = 2;
        PurchasePrice purchasePrice = new PurchasePrice(5_000);

        // when
        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(manualTicketsSizeInput, purchasePrice);

        // then
        assertThat(manualTicketsSize.size()).isEqualTo(manualTicketsSizeInput);
    }

    @DisplayName("수동구매 티켓이 0개일 때")
    @Test
    void Should_Return_False_When_ManualTicketsSizeZero() {
        // given
        int manualTicketsSizeInput = 0;
        PurchasePrice purchasePrice = new PurchasePrice(5_000);

        // when
        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(manualTicketsSizeInput, purchasePrice);

        // then
        assertThat(manualTicketsSize.isNotZero()).isFalse();
    }

    @DisplayName("수동구매 티켓이 0개가 아닐 때")
    @Test
    void Should_Return_True_When_ManualTicketsSizeNotZero() {
        // given
        int manualTicketsSizeInput = 1;
        PurchasePrice purchasePrice = new PurchasePrice(5_000);

        // when
        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(manualTicketsSizeInput, purchasePrice);

        // then
        assertThat(manualTicketsSize.isNotZero()).isTrue();
    }
}