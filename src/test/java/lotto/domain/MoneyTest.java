package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.exception.LottoPiecesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    private final static String NULL_MESSAGE = "로또를 사기에 금액이 모자랍니다.";
    private final static int LOTTO_PRICE = 1000;

    @Test
    @DisplayName("Money 생성 및 구입 가능 로또 매수 반환 테스트")
    void getLottoPieces() {
        int sampleMoney = 14500;
        int expectedLottoPieces = 14;
        Money money = new Money(sampleMoney);
        assertThat(money.getLottoPieces(LOTTO_PRICE)).isEqualTo(expectedLottoPieces);
    }

    @Test
    @DisplayName("잔액이 모자랄 때, 에러 발생 테스트")
    void lackMoneyToLottoPieces() {
        int sampleMoney = 100;
        Money money = new Money(sampleMoney);
        assertThatThrownBy( ()-> money.getLottoPieces(LOTTO_PRICE))
            .isInstanceOf(LottoPiecesException.class)
            .hasMessageContaining(NULL_MESSAGE);
    }
}
