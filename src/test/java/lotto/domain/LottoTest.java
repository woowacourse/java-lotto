package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoTest {
    @Test
    public void match_1등() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        int result = userLotto.match(winningLotto);
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void match_3등() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 7));

        int result = userLotto.match(winningLotto);

        assertThat(result).isEqualTo(5);
    }

    @Test
    public void of_중복_값() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 5));
        });
    }
}
