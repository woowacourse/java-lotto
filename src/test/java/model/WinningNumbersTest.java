package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumbersTest {
    @Test
    void autoTest() {
        assertThat(new Lotto("10, 34, 38, 40, 42, 43").match(new WinningNumbersWeb())).get().isEqualTo(LottoRank.FIRST);
    }

    @Test
    void factoryTestA() {
        assertThat(new Lotto("10, 34, 38, 40, 42, 43").match(WinningNumbersFactory.of(862))).get().isEqualTo(LottoRank.FIRST);
    }

    @Test
    void factoryTestB() {
        assertThat(new Lotto("11, 17, 19, 21, 22, 25").match(WinningNumbersFactory.of(861))).get().isEqualTo(LottoRank.FIRST);
    }

    @Test
    void factoryTestC() {
        assertThat(new Lotto("8, 10, 18, 23, 27, 33").match(WinningNumbersFactory.of(457))).get().isEqualTo(LottoRank.SECOND);
    }
}