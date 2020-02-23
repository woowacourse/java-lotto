package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 당첨숫자와_일치하는_로또번호_갯수_반환() {
        //given
        Set<LottoNumber> lottoNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto = new Lotto(lottoNumbers);
        Set<LottoNumber> winningNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(40)));
        //when
        int matchSize = lotto.matchWinningNumbers(winningNumbers);
        //then
        assertThat(matchSize).isEqualTo(5);
    }

    @Test
    void 당첨숫자와_보너스볼_일치() {
        //given
        LottoNumber bonusBall = new LottoNumber(1);
        Set<LottoNumber> lottoNumbers = new HashSet<>(
            Arrays.asList(bonusBall, new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto = new Lotto(lottoNumbers);

        //when & then
        assertThat(lotto.matchBonusNumber(bonusBall)).isTrue();
    }
}
