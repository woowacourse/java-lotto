package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
class LotteriesTest {
    Lotteries lotteries;
    Lotto lotto;

    @BeforeEach
    void setUp() {
        lotteries = new Lotteries();
        lotto = Lotto.generate(Arrays.asList(1,2,3,4,5,6));
        lotteries.add(lotto);
    }

    @Test
    void create_생성() {
        assertThat(lotteries).isEqualTo(new Lotteries().add(lotto));
    }
}