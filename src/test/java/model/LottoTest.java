package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void overflowTest() {
        assertThatThrownBy(() -> new Lotto("1,3,7,10,15,99"));
    }

    @Test
    void underflowTest() {
        assertThatThrownBy(() -> new Lotto("1,3,7,10,15,-1"));
    }

    @Test
    void matchingTestAuto() {
        assertThat(new Lotto("10,34 , 38 ,40,,42    ,32").match(new WinningNumbersWeb())).get().isEqualTo(LottoRank.SECOND);
    }

    @Test
    void matchingTestManual() {
        assertThat(
                new Lotto("1,2,    3, ,,,4, 5, 6").match(new WinningNumbersManual("1, 2, 3, 7, 8, 9, 10"))
        ).get().isEqualTo(LottoRank.FIFTH);
    }
}