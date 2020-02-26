package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 로또와 로또 비교")
    void match() {
        //given
        Set<LottoNumber> winningNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        Set<LottoNumber> lottoNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(40)));

        Lotto lotto = new Lotto(lottoNumbers);
        LottoNumber bounusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bounusNumber);
        //when & then
        assertThat(winningLotto.match(lotto)).isEqualTo(Rank.THIRD);
    }
}
