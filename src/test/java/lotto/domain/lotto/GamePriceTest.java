package lotto.domain.lotto;

import lotto.domain.number.PayOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class GamePriceTest {
    private GamePrice gamePrice;

    @BeforeEach
    void init() {
        gamePrice = new GamePrice();
    }

    @ParameterizedTest
    @DisplayName("입력 금액에 따른 게임 횟수를 반환한다.")
    @CsvSource(value = {"14000:14", "11231:11", "1:0", "10101:10"}, delimiter = ':')
    void getGameCount(int input, int expected) {
        assertThat(gamePrice.getGameCount(new PayOut(input))).isEqualTo(expected);
    }

    @Test
    @DisplayName("게임 횟수와 구입 금액을 넣으면 게임 횟수만큼 구입 금액을 차감한다.")
    void subtractPayOutByGameCount() {
        PayOut payOut = new PayOut(5000);
        assertThat(gamePrice.subtractPayOutByGameCount(payOut, 3))
                .isEqualTo(new PayOut(2000));
    }

    @Test
    @DisplayName("구입 금액보다 많은 게임 횟수를 넣으면 예")
    void getGameCount_errorOccurWhenMoreGameCountThenPurchasedMoney() {
        PayOut payOut = new PayOut(3000);
        assertThatIllegalArgumentException().isThrownBy(
                () -> gamePrice.subtractPayOutByGameCount(payOut, 5)
        ).withMessage("구입 금액보다 게임 횟수가 더 클 순 없습니다.");
    }
}
