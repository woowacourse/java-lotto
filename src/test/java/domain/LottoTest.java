package domain;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumberPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    private LottoNumber one;
    private LottoNumber two;
    private LottoNumber three;
    private LottoNumber four;
    private LottoNumber five;

    @BeforeEach
    void setUp() {
        one = LottoNumberPool.pickLottoNumber(1);
        two = LottoNumberPool.pickLottoNumber(2);
        three = LottoNumberPool.pickLottoNumber(3);
        four = LottoNumberPool.pickLottoNumber(4);
        five = LottoNumberPool.pickLottoNumber(5);
    }

    @Test
    void 로또_발급시_숫자가_6개가_아니면_예외를_던지는지_테스트() {
        Set<LottoNumber> fiveLottoNumbers = new TreeSet<>(Arrays.asList(one, two, three, four, five));

        assertThrows(IllegalArgumentException.class, () -> new Lotto(fiveLottoNumbers));
    }
}
