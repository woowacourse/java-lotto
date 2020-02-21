package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 당첨숫자와_일치하는_로또번호_갯수_반환() {
        //given
        Set<Integer> lottoNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(lottoNumbers);
        Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 40));
        //when
        int matchSize = lotto.matchWinningNumbers(winningNumbers);
        //then
        assertThat(matchSize).isEqualTo(5);
    }

    @Test
    void 당첨숫자와_보너스볼_일치() {
        //given
        int bonusBall = 1;
        Set<Integer> lottoNumbers = new HashSet<>(Arrays.asList(bonusBall, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(lottoNumbers);

        //when & then
        assertThat(lotto.matchBonusBall(bonusBall)).isTrue();
    }
}
