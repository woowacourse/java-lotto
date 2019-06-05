package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {
    private LottoNumber one;
    private LottoNumber two;
    private LottoNumber three;
    private LottoNumber four;
    private LottoNumber five;
    private LottoNumber six;

    @BeforeEach
    void setUp() {
        one = new LottoNumber(1);
        two = new LottoNumber(2);
        three = new LottoNumber(3);
        four = new LottoNumber(4);
        five = new LottoNumber(5);
        six = new LottoNumber(6);
    }

    @Test
    void 로또_발급시_숫자가_6개가_아니면_예외를_던지는지_테스트() {
        List<LottoNumber> fiveLottoNumbers = new ArrayList<>(Arrays.asList(one, two, three, four, five));

        assertThrows(IllegalArgumentException.class, () -> new Lotto(fiveLottoNumbers));
    }

    @Test
    void 중복되는_숫자가_있는_경우_예외를_던지는지_테스트() {
        List<LottoNumber> sixLottoNumbers = new ArrayList<>(Arrays.asList(one, one, two, three, four, five));

        assertThrows(IllegalArgumentException.class, () -> new Lotto(sixLottoNumbers));
    }

    @Test
    void 당첨_로또와_맞춰본_결과를_제대로_알려주는지_테스트() {
        List<LottoNumber> sixWinningNumbers = new ArrayList<>(Arrays.asList(one, two, three, four, five, six));
        List<LottoNumber> sixIssuedNumbers = new ArrayList<>(sixWinningNumbers);

        Lotto winningLotto = new Lotto(sixWinningNumbers);
        Lotto issuedLotto = new Lotto(sixIssuedNumbers);

        assertThat(issuedLotto.matchUpLottoNumbersWith(winningLotto)).isEqualTo(FIRST);
    }
}
