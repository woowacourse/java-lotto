package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
    @Test
    public void 당첨_번호_로또_생성_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5),
                LottoNumber.get(6)
        );
        assertThat(new WinningLotto(new Lotto(numbers))).isEqualTo(new WinningLotto(new Lotto(numbers)));
    }

    @Test
    public void 로또_당첨_결과_계산_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5),
                LottoNumber.get(6)
        );
        assertThat(new WinningLotto(new Lotto(numbers)).calculateRank(new Lotto(numbers)))
                .isEqualTo(LottoRank.FIRST);
    }
}
