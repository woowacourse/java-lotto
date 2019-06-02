package lotto.domain;

import lotto.domain.customlotto.DefaultCustomLotto;
import lotto.domain.makeuplotto.MockCreateLotto;
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
    CustomLotto customLotto;
    CreateLotto createLotto;

    @BeforeEach
    void setUp() {
        customLotto = new DefaultCustomLotto();
        createLotto = new MockCreateLotto();

        lotto = new Lotto();
        lotto.setCustomLotto(customLotto);
        lotto.setCreateLotto(createLotto);

        lotteries = new Lotteries(lotto);
        lotteries.addNoFormedLotto(Arrays.asList(1,2,3,4,5,6));
    }

    @Test
    void create_생성() {
        Lotteries lotteries2 = new Lotteries(lotto);
        lotteries2.addNoFormedLotto(Arrays.asList(1,2,3,4,5,6));
        assertThat(lotteries).isEqualTo(lotteries2);
    }
}