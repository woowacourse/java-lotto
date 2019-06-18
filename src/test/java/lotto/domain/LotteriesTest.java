package lotto.domain;

import com.google.gson.Gson;
import lotto.domain.customcreatelotto.DefaultCustomCreateCreateLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
class LotteriesTest {
    public static Lotteries lotteries;

    @BeforeEach
    void setUp() {
        lotteries = new Lotteries();
        lotteries.addCustomLotto(Arrays.asList(1, 2, 3, 4, 5, 6), new DefaultCustomCreateCreateLotto());
    }

    @Test
    void create_생성() {
        Lotteries lotteries2 = new Lotteries();
        lotteries2.addCustomLotto(Arrays.asList(1, 2, 3, 4, 5, 6), new DefaultCustomCreateCreateLotto());
        assertThat(lotteries).isEqualTo(lotteries2);
    }

    @Test
    void jsonTest() {
        System.out.println(new Gson().toJson(lotteries));
    }
}