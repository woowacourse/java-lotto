package lotto.domain;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

public class LottosTest {
    private Lottos lottos;

    @BeforeEach
    void setUp() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 20, 21, 40));
        Lotto lotto2 = new Lotto(Arrays.asList(1, 2, 20, 25, 29, 45));
        lottos = new Lottos(Arrays.asList(lotto1, lotto2));
    }


}
