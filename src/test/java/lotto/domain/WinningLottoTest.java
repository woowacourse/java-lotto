package lotto.domain;

import lotto.domain.generator.LottoNosManualGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    Lotto lotto;

    @BeforeEach
    void setUp() {
        List<LottoNo> lottoNos = new LottoNosManualGenerator("1,2,3,4,5,6").generate();
        lotto = Lotto.of(lottoNos);
    }

    @Test
    void 원시값_입력_생성자_테스트() {
        final int bonusNo = 7;
        WinningLotto actual = new WinningLotto(lotto, LottoNo.from(bonusNo));

        WinningLotto expected = new WinningLotto("1,2,3,4,5,6", bonusNo);

        assertThat(actual.toString()).isEqualTo(expected.toString());
    }

    @Test
    void 보너스_중복_테스트() {
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(lotto, LottoNo.from(6)));
    }

    @Test
    void 등수_얻기_확인() {
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNo.from(7));

        assertThat(Rank.FIRST).isEqualTo(winningLotto.match(lotto));
    }
}
