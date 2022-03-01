package lotto.domain;

import lotto.domain.lotto.Lottos;
import lotto.exception.InvalidException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottosTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10})
    public void 로또번호들_생성_성공(int count) {
        new Lottos(count);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void 로또번호들_생성_실패(int count) {
        assertThatThrownBy(() -> new Lottos(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InvalidException.ERROR_WRONG_INPUT_MONEY);
    }
}
