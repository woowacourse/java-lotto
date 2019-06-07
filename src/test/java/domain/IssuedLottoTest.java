package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IssuedLottoTest {
    private LottoNumber one;
    private LottoNumber two;
    private LottoNumber three;
    private LottoNumber four;
    private LottoNumber five;
    private LottoNumber six;
    private Set<LottoNumber> lottoNumberFromOneToSix;

    @BeforeEach
    void setUp() {
        one = new LottoNumber(1);
        two = new LottoNumber(2);
        three = new LottoNumber(3);
        four = new LottoNumber(4);
        five = new LottoNumber(5);
        six = new LottoNumber(6);
        lottoNumberFromOneToSix = new TreeSet<>(Arrays.asList(one, two, three, four, five, six));
    }

    @Test
    void 로또_발급시_숫자가_6개가_아니면_예외를_던지는지_테스트() {
        Set<LottoNumber> fiveLottoNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five));

        assertThrows(IllegalArgumentException.class, () -> new IssuedLotto(fiveLottoNumbers));
    }

    @Test
    void 로또가_해당_숫자의_포함_여부를_제대로_알려주는지_테스트() {
        IssuedLotto testIssuedLotto = new IssuedLotto(lottoNumberFromOneToSix);

        assertThat(testIssuedLotto.contains(one)).isTrue();
    }
}
