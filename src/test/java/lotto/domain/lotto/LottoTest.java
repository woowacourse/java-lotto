package lotto.domain.lotto;

import java.util.Arrays;
import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    public void 로또번호_개수_검증() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 숫자 입력입니다.");
    }

    @Test
    public void 로또번호_중복_검증() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 숫자 입력입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void 로또번호_체크_성공(int value) {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto.contains(value)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 7})
    public void 로또번호_체크_실패(int value) {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto.contains(value)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 로또번호_빈값_검증(String value) {
        assertThatThrownBy(() -> new Lotto(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
