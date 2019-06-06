package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.persistence.dto.WinningLottoDto;
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
        assertThat(new WinningLotto(LottoNumberGroup.of(winningNums), 44)).isEqualTo(new WinningLotto(LottoNumberGroup.of(winningNums), 44));
    }

    @Test
    void matchLotto() {
        Set<Integer> winningNums = new HashSet<>(Arrays.asList(5, 9, 14, 33, 42, 45));
        WinningLotto win = new WinningLotto(LottoNumberGroup.of(winningNums), 44);
        assertThat(win.match(new Lotto(LottoNumberGroup.of(Arrays.asList(14, 33, 2, 9, 41, 24)))))
            .isEqualTo(LottoResult.FIFTH);
    }

    @Test
    void winningNumbersContainBonus() {
        Set<Integer> winningNums = new HashSet<>(Arrays.asList(5, 9, 14, 33, 42, 45));
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(LottoNumberGroup.of(winningNums), 42));
    }

    @Test
    void convertToDto() {
        WinningLotto winningLotto = new WinningLotto(LottoNumberGroup.of(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);
        WinningLottoDto dto = winningLotto.toDto();
        assertThat(dto.getWinningNumber0()).isEqualTo(1);
        assertThat(dto.getWinningNumber1()).isEqualTo(2);
        assertThat(dto.getWinningNumber2()).isEqualTo(3);
        assertThat(dto.getWinningNumber3()).isEqualTo(4);
        assertThat(dto.getWinningNumber4()).isEqualTo(5);
        assertThat(dto.getWinningBonusNumber()).isEqualTo(7);
    }
}
