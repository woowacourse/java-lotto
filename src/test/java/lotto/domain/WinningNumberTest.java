package lotto.domain;

import lotto.domain.exception.WinningLottoContainBonusException;
import lotto.domain.generator.LottoNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.LottoNumber.getLottoNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningNumberTest {

    WinningNumber winningNumber;

    @BeforeEach
    void setUp() {
        winningNumber = new WinningNumber(new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(4), getLottoNumber(5), getLottoNumber(6))), 7);
    }

    @Test
    void init() {
        assertThat(winningNumber)
                .isEqualTo(new WinningNumber(new Lotto(Arrays.asList(
                        getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                        getLottoNumber(4), getLottoNumber(5), getLottoNumber(6))), 7));
    }

    @Test
    void 모두_일치하는_경우_테스트() {
        assertThat(winningNumber.prize(new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(4), getLottoNumber(5), getLottoNumber(6)))))
                .isEqualTo(Prize.FIRST);
    }

    @Test
    void 번호_5개_및_보너스볼_일치하는_경우_테스트() {
        assertThat(winningNumber.prize(new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(4), getLottoNumber(5), getLottoNumber(7)))))
                .isEqualTo(Prize.SECOND);
    }

    @Test
    void 번호_5개_일치하는_경우_테스트() {
        assertThat(winningNumber.prize(new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(4), getLottoNumber(5), getLottoNumber(8)))))
                .isEqualTo(Prize.THIRD);
    }

    @Test
    void 번호_4개_일치하는_경우_테스트() {
        assertThat(winningNumber.prize(new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(4), getLottoNumber(7), getLottoNumber(8)))))
                .isEqualTo(Prize.FOURTH);
    }

    @Test
    void 번호_3개_일치하는_경우_테스트() {
        assertThat(winningNumber.prize(new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                getLottoNumber(7), getLottoNumber(8), getLottoNumber(9)))))
                .isEqualTo(Prize.FIFTH);
    }

    @Test
    void 당첨되지_않는_경우() {
        assertThat(winningNumber.prize(new Lotto(Arrays.asList(
                getLottoNumber(1), getLottoNumber(2), getLottoNumber(7),
                getLottoNumber(8), getLottoNumber(9), getLottoNumber(10)))))
                .isEqualTo(Prize.NONE);
    }

    @Test
    void 보너스볼이_로또_번호와_겹치는_경우_에러_처리() {
        assertThrows(WinningLottoContainBonusException.class, () -> {
            new WinningNumber(new Lotto(LottoNumbersGenerator.generateLottoNumbers("1, 2, 3, 4, 5, 6")), 1);
        });
    }
}
