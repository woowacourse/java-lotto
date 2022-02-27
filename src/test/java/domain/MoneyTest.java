package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @Test
    @DisplayName("로또 게임 머니를 생성하는 기능")
    void createMoney() {
        LottoGameMoney money = new LottoGameMoney(1000);

        assertThat(money).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, 0, 8800})
    @DisplayName("로또 게임 머니를 생성하지 못하는 경우")
    void createInvalidMoney(int amount) {
        assertThatThrownBy(() -> new LottoGameMoney(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
