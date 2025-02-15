package model;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @DisplayName("로또 번호와 일치하는 경우 True를 반환한다")
    @Test
    void shouldReturnTrueWhenDuplicateNumbers() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        Assertions.assertThat(lotto.checkDuplication(Arrays.asList(1, 2, 3, 4, 5, 6))).isTrue();
    }

    @DisplayName("로또 번호와 중복되지 않는 번호를 가진 경우 False를 반환한다")
    @Test
    void shouldReturnFalseWhenNoDuplicateNumbers() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        Assertions.assertThat(lotto.checkDuplication(Arrays.asList(1, 2, 3, 4, 5, 7))).isFalse();
    }
}
