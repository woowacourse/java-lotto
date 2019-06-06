package lotto.domain;

import lotto.domain.customlotto.DefaultCustomLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
class LotteriesTest {
    private Lotteries lotteries;

    @BeforeEach
    void setUp() {
        lotteries = new Lotteries();
        lotteries.addCustomLotto(Arrays.asList(1, 2, 3, 4, 5, 6), new DefaultCustomLotto());
    }

    @Test
    void create_생성() {
        Lotteries lotteries2 = new Lotteries();
        lotteries2.addCustomLotto(Arrays.asList(1, 2, 3, 4, 5, 6), new DefaultCustomLotto());
        assertThat(lotteries).isEqualTo(lotteries2);
    }
}