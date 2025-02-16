package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @DisplayName("로또가 정상적으로 저장된다")
    @Test
    void saveLotto() {
        assertDoesNotThrow(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)));
    }

    @DisplayName("로또 한 개는 여섯 개가 아닌 숫자로 이루어져 있을 시 예외가 발생한다")
    @Test
    void containsSixNumbersInlotto() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 일 이상 사십오 이하의 숫자가 아닐 경우 예외가 발생한다")
    @Test
    void containsNumbersInRange() {
        assertThatThrownBy(() -> new Lotto(List.of(-1, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 2_100_000_000)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 사이의 중복이 있을 시 예외가 발생한다.")
    @Test
    void verifyNoDuplicationInOneLotto() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호와 일치하는 경우 True를 반환한다")
    @Test
    void shouldReturnTrueWhenDuplicateNumbers() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        Assertions.assertThat(lotto1.isSameWith(Arrays.asList(1, 2, 3, 4, 5, 6))).isTrue();

        Lotto lotto2 = new Lotto(Arrays.asList(6, 5, 4, 3, 2, 1));

        Assertions.assertThat(lotto2.isSameWith(Arrays.asList(1, 2, 3, 4, 5, 6))).isTrue();

        Lotto lotto3 = new Lotto(Arrays.asList(1, 3, 5, 2, 4, 6));

        Assertions.assertThat(lotto3.isSameWith(Arrays.asList(1, 2, 3, 4, 5, 6))).isTrue();
    }

    @DisplayName("로또 번호와 중복되지 않는 번호를 가진 경우 False를 반환한다")
    @Test
    void shouldReturnFalseWhenNoDuplicateNumbers() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        Assertions.assertThat(lotto.isSameWith(Arrays.asList(1, 2, 3, 4, 5, 7))).isFalse();
    }
}
