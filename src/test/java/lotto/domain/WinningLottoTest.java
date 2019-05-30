package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    @Test
    void 보너스_번호_중복() {
        assertThrows(IllegalArgumentException.class, () -> {
            WinningLotto winningLotto = new WinningLotto(
                    new Lotto(Arrays.asList(
                            Number.valueOf(1),
                            Number.valueOf(2),
                            Number.valueOf(3),
                            Number.valueOf(4),
                            Number.valueOf(5),
                            Number.valueOf(6)
                    )),
                    Number.valueOf(6)
            );
        });

    }

    @Test
    void 등수_구하기() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        Number.valueOf(1),
                        Number.valueOf(2),
                        Number.valueOf(3),
                        Number.valueOf(4),
                        Number.valueOf(5),
                        Number.valueOf(6)
                )),
                Number.valueOf(7)
        );

        assertThat(winningLotto.matchLotto(new Lotto(Arrays.asList(
                Number.valueOf(1),
                Number.valueOf(2),
                Number.valueOf(3),
                Number.valueOf(4),
                Number.valueOf(5),
                Number.valueOf(6)
        )))).isEqualTo(WinningType.FIRST);
    }
}