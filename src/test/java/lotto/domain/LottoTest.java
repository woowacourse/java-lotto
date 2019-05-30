package lotto.domain;

import lotto.domain.exception.LottoSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.LottoNumber.*;
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
        assertThat(lotto.hasNumber(getLottoNumber(5))).isEqualTo(1);
    }

    @Test
    void 보너스_번호가_lotto번호로_있는_경우_테스트() {
        BonusBall bonusBall = new BonusBall(5);
        assertThat(lotto.hasBonusBall(bonusBall)).isTrue();
    }
}
