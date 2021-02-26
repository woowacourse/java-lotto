package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @DisplayName("로또 티켓들 생성하기")
    @Test
    public void create() {
        Lottos lottos = new Lottos(Arrays.asList
                (new Lotto(1, 2, 3, 4, 5, 6),
                        new Lotto(2, 3, 4, 5, 6, 7)));

        assertThat(lottos.toList())
                .contains(new Lotto(1, 2, 3, 4, 5, 6), new Lotto(2, 3, 4, 5, 6, 7));
    }
}
