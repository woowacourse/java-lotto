package lotto.domain;

import lotto.exception.InvalidException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottosTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 5000, 14000})
    public void 로또번호들_생성_성공(int money) {
        new Lottos(money);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 300, 2500})
    public void 로또번호들_생성_실패(int money) {
        assertThatThrownBy(() -> new Lottos(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InvalidException.ERROR_WRONG_INPUT_MONEY);
    }
}
