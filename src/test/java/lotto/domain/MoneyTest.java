package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.exception.MoneyException;
import lotto.exception.PieceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("Money 생성 및 구입 가능 로또 매수 반환 테스트")
    void getLottoPieces() {
        int sampleMoney = 14500;
        int expectedLottoPieces = 14;
        Money money = new Money(sampleMoney);
        assertThat(money.getLottoPieces()).isEqualTo(expectedLottoPieces);
    }

    @Test
    @DisplayName("잔액이 모자랄 때, 에러 발생 테스트")
    void lackMoneyToLottoPieces() {
        int sampleMoney = 100;
        assertThatThrownBy(() -> new Money(sampleMoney))
            .isInstanceOf(MoneyException.class)
            .hasMessageContaining(Money.NOT_ENOUGH_MONEY);
    }

    @Test
    @DisplayName("수동 구매 매수가 구매 가능 매수 초과시, 예외 처리 테스트")
    void excessAvailableLottoPieces() {
        int sampleMoney = 2000;
        int manualPieceNumber = 3;
        Money money = new Money(sampleMoney);
        assertThatThrownBy(() -> new Piece(money, manualPieceNumber))
            .isInstanceOf(PieceException.class)
            .hasMessageContaining(Piece.EXCESS_AVAILABLE_MESSAGE);
    }
}
