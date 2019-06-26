package domain;

import domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

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
    void 당첨_로또와_맞춰본_결과를_제대로_알려주는지_테스트() {
        Set<LottoNumber> sixWinningNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five, six));
        WinningLotto winningLotto = new WinningLotto(new Lotto(sixWinningNumbers), bonusNumber);

        Set<LottoNumber> sixIssuedNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five, bonusNumber));
        Lotto lotto = new Lotto(sixIssuedNumbers);

        assertThat(winningLotto.matchUpLottoNumbersWith(lotto)).isEqualTo(Rank.SECOND);
    }
}
