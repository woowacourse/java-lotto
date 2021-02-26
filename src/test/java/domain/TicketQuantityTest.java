package domain;

import exception.InsufficientMoneyException;
import exception.NegativeTicketQuantityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class TicketQuantityTest {
    private LottoMoney lottoMoney;

    @BeforeEach
    void setUp() {
        lottoMoney = new LottoMoney("14000");
    }

    @DisplayName("유효한 값이면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 14})
    void create_validValue_success(final int manualTicketQuantity) {
        assertThatCode(() -> new TicketQuantity(lottoMoney, manualTicketQuantity))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 수동 티켓의 수가 음수인 경우")
    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3})
    void create_negativeNumber_exceptionThrown(final int manualTicketQuantity) {
        assertThatThrownBy(() -> new TicketQuantity(lottoMoney, manualTicketQuantity))
                .isInstanceOf(NegativeTicketQuantityException.class)
                .hasMessageContaining("음수가 올 수 없습니다");
    }

    @DisplayName("객체 생성 실패 : 돈이 부족한 경우")
    @ParameterizedTest
    @ValueSource(ints = {15, 20, 100})
    void create_lackOfMoney_exceptionThrown(final int manualTicketQuantity) {
        assertThatThrownBy(() -> new TicketQuantity(lottoMoney, manualTicketQuantity))
                .isInstanceOf(InsufficientMoneyException.class)
                .hasMessageContaining("돈이 부족합니다");
    }
}
