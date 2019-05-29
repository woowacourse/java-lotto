package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
class LottoGenerateBaseTest {
    @Test
    void create_경계값_확인_첫값() {
        assertThat(LottoGenerateBase.lottoGenerateBase.get(0)).isEqualTo(new LottoNumber(1));
    }

    @Test
    void create_경계값_확인_마지막() {
        assertThat(LottoGenerateBase.lottoGenerateBase.get(44)).isEqualTo(new LottoNumber(45));
    }

    @Test
    void shuffle_확인() {
        Collections.shuffle(LottoGenerateBase.lottoGenerateBase);
        List<LottoNumber> lotto = LottoGenerateBase.lottoGenerateBase.subList(0,6);
        assertThat(lotto.size()).isEqualTo(6);
    }
}