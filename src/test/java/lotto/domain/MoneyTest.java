package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.exception.LottoPiecesException;
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
            .isInstanceOf(LottoPiecesException.class);
    }
}
