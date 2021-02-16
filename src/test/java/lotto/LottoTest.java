package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("잘못된 개수의 로또 번호")
    void generateIllegalNumberCountLotto() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("잘못된 개수");
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("잘못된 개수");
    }

    @Test
    @DisplayName("범위 초과 로또 번호")
    void generateOutRangeLotto() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5, 46)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("범위가 초과");
    }

    @Test
    @DisplayName("중복 로또 번호")
    void generateDuplicateLotto() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 1, 3, 4, 5, 45)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("중복");
    }

}
