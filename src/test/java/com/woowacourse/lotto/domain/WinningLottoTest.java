package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void create() {
        Set<Integer> winningNums = new HashSet<>(Arrays.asList(1, 5, 2, 6, 24, 32));
        assertThat(new WinningLotto(winningNums, 44)).isEqualTo(new WinningLotto(winningNums, 44));
    }

    @Test
    void matchLotto() {
        Set<Integer> winningNums = new HashSet<>(Arrays.asList(5, 9, 14, 33, 42, 45));
        WinningLotto win = new WinningLotto(winningNums, 44);
        assertThat(win.match(new Lotto(new HashSet<>(Arrays.asList(14, 33, 2, 9, 41, 24)))))
            .isEqualTo(LottoResult.FIFTH);
    }

    @Test
    void winningNumbersContainBonus() {
        Set<Integer> winningNums = new HashSet<>(Arrays.asList(5, 9, 14, 33, 42, 45));
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(winningNums, 42));
    }
}
