package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @Test
    @DisplayName("돈을 생성하는 기능")
    void createMoney() {
        LottoMoney lottoMoney = new LottoMoney(1000);

        assertThat(lottoMoney).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, 0})
    @DisplayName("돈을 생성하지 못하는 경우")
    void createInvalidMoney(int amount) {
        assertThatThrownBy(() -> new LottoMoney(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
