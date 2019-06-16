package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.LottoNumber.getLottoNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {

    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        Lotto lotto = new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(4), getLottoNumber(5), getLottoNumber(6)
        ));
        winningLotto = new WinningLotto(lotto);
    }

    @Test
    void 번호_5개가_일치하는_경우() {
        Lotto lotto = new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(4), getLottoNumber(5), getLottoNumber(7)
        ));
        assertThat(winningLotto.countOfMatchLottoNumber(lotto)).isEqualTo(5);
    }
}
