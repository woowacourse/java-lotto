package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.generator.LottoNosManualGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoDaoTest {

    @Test
    void addTest() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(Lotto.of(new LottoNosManualGenerator("1,2,3,4,5,6").generate()));
        lottos.add(Lotto.of(new LottoNosManualGenerator("1,2,3,4,12,19").generate()));

        assertTrue(new LottoDao().add(lottos, 1));
    }

    @Test
    void findByRoundTest() {
        assertThat(0).isNotEqualTo(new LottoDao().findByRound(0).size());
    }
}