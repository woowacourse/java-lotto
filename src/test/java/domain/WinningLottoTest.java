package domain;

import domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {
    private LottoNumber one;
    private LottoNumber two;
    private LottoNumber three;
    private LottoNumber four;
    private LottoNumber five;
    private LottoNumber six;
    private LottoNumber bonusNumber;

    @BeforeEach
    void setUp() {
        one = LottoNumber.valueOf(1);
        two = LottoNumber.valueOf(2);
        three = LottoNumber.valueOf(3);
        four = LottoNumber.valueOf(4);
        five = LottoNumber.valueOf(5);
        six = LottoNumber.valueOf(6);
        bonusNumber = LottoNumber.valueOf(7);
    }

    @Test
    void 로또_발급시_숫자가_6개가_아니면_예외를_던지는지_테스트() {
        Set<LottoNumber> fiveLottoNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five));

        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(fiveLottoNumbers, bonusNumber));
    }

    @Test
    void 당첨_로또와_맞춰본_결과를_제대로_알려주는지_테스트() {
        Set<LottoNumber> sixWinningNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five, six));
        WinningLotto winningLotto = new WinningLotto(sixWinningNumbers, bonusNumber);

        Set<LottoNumber> sixIssuedNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five, bonusNumber));
        IssuedLotto issuedLotto = new IssuedLotto(sixIssuedNumbers);

        assertThat(winningLotto.matchUpLottoNumbersWith(issuedLotto)).isEqualTo(Rank.SECOND);
    }
}
