package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @Test
    void 여섯개_일치() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))));

        LottoNumbers winningLottoNumbers = new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));

        assertThat(lotto.hasLottoNumber(winningLottoNumbers)).isEqualTo(6);
    }

    @Test
    void 세개_일치() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))));

        LottoNumbers winningLottoNumbers = new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(11),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(15),
                LottoNumber.valueOf(6)));

        assertThat(lotto.hasLottoNumber(winningLottoNumbers)).isEqualTo(3);
    }

    @Test
    void 불일치() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))));

        LottoNumbers winningLottoNumbers = new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(11),
                LottoNumber.valueOf(12),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(14),
                LottoNumber.valueOf(15),
                LottoNumber.valueOf(16)));

        assertThat(lotto.hasLottoNumber(winningLottoNumbers)).isEqualTo(0);
    }
}
