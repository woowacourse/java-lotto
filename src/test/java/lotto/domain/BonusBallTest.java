package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BonusBallTest {

    BonusBall bonusBall;

    @BeforeEach
    void setUp() {
        bonusBall = new BonusBall(30);
    }

    @Test
    void 번호_1미만_숫자가_주어질때_에러_테스트() {
        assertThrows(InvalidLottoNumberException.class, () -> new BonusBall(0));
    }

    @Test
    void 번호_45초과_숫자가_주어질때_에러_테스트() {
        assertThrows(InvalidLottoNumberException.class, () -> new BonusBall(46));
    }

    @Test
    void 보너스볼과_일치하는_번호가_입력될_경우_테스트() {
        assertThat(bonusBall.isMatch(LottoNumber.getLottoNumber(30))).isTrue();
    }

    @Test
    void 보너스볼과_다른_번호가_입력될_경우_테스트() {
        assertThat(bonusBall.isMatch(LottoNumber.getLottoNumber(20))).isFalse();
    }
}
