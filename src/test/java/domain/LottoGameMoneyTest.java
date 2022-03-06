package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoGameMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 14000})
    @DisplayName("로또 게임 머니를 생성하는 기능")
    void createMoney(int amount) {
        LottoGameMoney money = new LottoGameMoney(amount);

        assertThat(money).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 990, 1500})
    @DisplayName("로또 게임 머니를 생성하지 못하는 경우")
    void createInvalidMoney(int amount) {
        assertThatThrownBy(() -> new LottoGameMoney(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 5})
    @DisplayName("구매하려는 수동 로또 갯수가 유효한 경우")
    void checkPurchasableLottoCount(int lottoCount) {
        LottoGameMoney money = new LottoGameMoney(5000);
        money.getPurchaseLottoCount(lottoCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 6, 7})
    @DisplayName("구매하려는 수동 로또 갯수가 유효하지 않은 경우")
    void checkNotPurchasableLottoCount(int lottoCount) {
        LottoGameMoney money = new LottoGameMoney(5000);
        assertThatThrownBy(() -> money.getPurchaseLottoCount(lottoCount))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
