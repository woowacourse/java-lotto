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
    void matchingTest() {
        assertThat(new Lotto("11,17 , 19 ,21,,22    ,24").match(new WinningNumbers())).get().isEqualTo(LottoRank.SECOND);
    }
}