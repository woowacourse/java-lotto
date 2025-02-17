package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("로또 번호의 개수가 6개가 아닌 예외")
    @Test
    public void lottoSize() {
        Set<Integer> lottoNumbers = Set.of(1, 2, 3, 4, 5, 6, 7);
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1-45의 범위가 아닌 예외")
    @Test
    public void lottoRange() {
        Set<Integer> lottoNumbers = Set.of(1, 2, 3, 4, 5, 46);
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
