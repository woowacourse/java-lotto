package domain;

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
        one = new LottoNumber(1);
        two = new LottoNumber(2);
        three = new LottoNumber(3);
        four = new LottoNumber(4);
        five = new LottoNumber(5);
        six = new LottoNumber(6);
        bonusNumber = new LottoNumber(7);
    }

    @Test
    void 로또_발급시_숫자가_6개가_아니면_예외를_던지는지_테스트() {
        Set<LottoNumber> fiveLottoNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five));

        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(fiveLottoNumbers, bonusNumber));
    }

    @Test
    void 당첨_로또와_맞춰본_결과를_제대로_알려주는지_테스트() {
        Set<LottoNumber> sixWinningNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five, six));
        Set<LottoNumber> sixIssuedNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five, new LottoNumber(7)));

        WinningLotto winningLotto = new WinningLotto(sixWinningNumbers, bonusNumber);
        IssuedLotto issuedLotto = new IssuedLotto(sixIssuedNumbers);

        assertThat(winningLotto.matchUpLottoNumbersOf(issuedLotto)).isEqualTo(Rank.SECOND);
    }
}
