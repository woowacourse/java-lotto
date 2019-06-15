package lotto.dao;

import lotto.config.TableCreator;
import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import lotto.domain.WinningLotto;
import lotto.domain.generator.LottoNosManualGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDaoTest {
    @BeforeAll
    static void createTable() throws Exception {
        TableCreator.create();
    }

    @Test
    void addTest() {
        Lotto lotto = Lotto.of(new LottoNosManualGenerator("1,2,3,4,5,6").generate());
        LottoNo bonus = LottoNo.from(10);
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        assertThat(1).isEqualTo(new WinningLottoDao().save(winningLotto, 0));
    }

    @Test
    void findByRoundTest() {
        assertThat(new WinningLottoDao().findAllByRound(0)).isNotNull();
    }
}