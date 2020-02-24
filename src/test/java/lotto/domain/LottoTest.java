package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("유효한 로또번호로 로또 생성")
    void createWithValidLottoNumbers() {
        //given
        Set<LottoNumber> lottoNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));

        assertThat(new Lotto(lottoNumbers)).isNotNull();
    }

    @Test
    @DisplayName("유효하지_않은_로또번호로_로또_생성")
    void createWithInvalidLottoNumbers() {
        //given
        Set<LottoNumber> lottoNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5)));

        assertThatThrownBy(() -> new Lotto(lottoNumbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호의 개수는 6개여야 합니다");
    }

    @Test
    @DisplayName("당첨숫자와 일치하는 로또번호 갯수 반환")
    void matchWinningNumbers() {
        //given
        Set<LottoNumber> lottoNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto = new Lotto(lottoNumbers);
        Set<LottoNumber> winningNumbers = new HashSet<>(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(40)));

        Lotto winningLotto = new Lotto(winningNumbers);
        //when
        int matchSize = lotto.matchWinningNumbers(winningLotto);
        //then
        assertThat(matchSize).isEqualTo(5);
    }

    @Test
    @DisplayName("당첨숫자와 보너스볼 일치")
    void matchBonusNumber() {
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
