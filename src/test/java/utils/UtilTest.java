package utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class UtilTest {

    @Test
    void separateNumbers() {
        assertThat(Util.separateNumbers("1, 2, 3, 4, 5, 6"))
                .isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void getProfit() {
        assertThat(Util.getProfit(10000, 20000))
                .isEqualTo(0.5f);
    }
}