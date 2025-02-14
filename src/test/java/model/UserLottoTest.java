package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserLottoTest {

    @DisplayName("구매 금액이 1000원 단위이면 로또를 생성한다")
    @Test
    void purchaseLottoSuccess() {
        String rawPurchaseAmount = "10000";
        UserLotto userLotto = new UserLotto(rawPurchaseAmount);

        assertEquals(userLotto.getLottos().size(), 10);
    }

    @DisplayName("구매 금액이 1000원 단위가 아니면 예외를 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1234", "0", "-1000"})
    void purchaseLottoFailure(String rawPurchaseAmount) {
        assertThrows(IllegalArgumentException.class, () -> new UserLotto(rawPurchaseAmount));
    }
}