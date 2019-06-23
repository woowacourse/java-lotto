package lotto.persistence;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.ManualLottoNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDAOTest {
    private LottoDAO lottoDao;
    private RoundDAO roundDao;
    private Lottos lottos;

    @BeforeEach
    void setUp() {
        this.lottoDao = LottoDAO.getInstance();
        this.roundDao = RoundDAO.getInstance();

        this.lottos = new Lottos(Arrays.asList(
                Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1, 2, 3, 4, 5, 6)))
        ));
    }

    @Test
    void addLottoTest() {
        roundDao.addRound(100, 1.0);
        int roundId = roundDao.getLatestRoundId();
        lottoDao.addLotto(roundId, lottos);
        assertThat(lottoDao.findLottosByRoundId(roundId)).isEqualTo(lottos);
        roundDao.removeRoundById(roundId);
    }
}
