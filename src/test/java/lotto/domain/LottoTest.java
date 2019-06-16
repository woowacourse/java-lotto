package lotto.domain;

import lotto.domain.exception.DuplicateLottoException;
import lotto.domain.exception.LottoSizeException;
import lotto.domain.generator.LottoNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.LottoNumber.getLottoNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(4), getLottoNumber(5), getLottoNumber(6)));
    }

    @Test
    void init() {
        assertThat(new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(4), getLottoNumber(5), getLottoNumber(6))))
                .isEqualTo(new Lotto(Arrays.asList(
                        getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                        getLottoNumber(4), getLottoNumber(5), getLottoNumber(6))
                ));
    }

    @Test
    void 번호가_6개가_아닌_경우_테스트() {
        assertThrows(LottoSizeException.class,
                () -> new Lotto(Arrays.asList(
                        getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                        getLottoNumber(4), getLottoNumber(5))));
    }

    @Test
    void lotto안에_5가_있는지_여부_테스트() {
        assertThat(lotto.checkNumber(getLottoNumber(5))).isEqualTo(1);
    }

    @Test
    void 보너스_번호가_lotto번호로_있는_경우_테스트() {
        BonusBall bonusBall = new BonusBall(5);
        assertThat(lotto.hasBonusBall(bonusBall)).isTrue();
    }

    @Test
    void 중복된_로또_숫자가_있는_경우_에러_테스트() {
        assertThrows(DuplicateLottoException.class, () -> {
            new Lotto(LottoNumbersGenerator.generateLottoNumbers("1, 2, 2, 3, 4, 5"));
        });
    }
}
