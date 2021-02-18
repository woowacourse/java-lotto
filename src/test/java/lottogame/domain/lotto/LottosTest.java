package lottogame.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    @Test
    void 객체_생성() {
        LottoGenerator.generate();

        List<Lotto> lottoGroup = new ArrayList<>();
        lottoGroup.add(new Lotto(LottoGenerator.makeNumbers()));
        lottoGroup.add(new Lotto(LottoGenerator.makeNumbers()));
        lottoGroup.add(new Lotto(LottoGenerator.makeNumbers()));
        lottoGroup.add(new Lotto(LottoGenerator.makeNumbers()));

        Lottos lottos = new Lottos(lottoGroup);
        for (Lotto lotto : lottoGroup) {
            assertThat(lottos.values()).contains(lotto);
        }
    }
}
