package lotto.domain.lotto;

import lotto.dao.LottoDAO;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDAOTest {
    private Lotto testLotto;
    private LottoDAO lottoDAO;

    @BeforeEach
    void setUp() {
        testLotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoDAO = LottoDAO.getInstance();
    }

    @Test
    void DB에_lotto를_insert하는지_확인() throws SQLException {
        lottoDAO.addLotto(testLotto, 1);
    }

    @Test
    void DB의_lotto를_select하는지_확인() throws SQLException {
        List<Lotto> lottos = lottoDAO.selectByRound(1);
        assertThat(lottos.get(0)).isEqualTo(testLotto);
    }
}
