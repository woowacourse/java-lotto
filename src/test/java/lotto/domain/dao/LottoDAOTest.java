package lotto.domain.dao;

import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

public class LottoDAOTest {
    @Test
    void 로또들_번호_저장하기() throws SQLException {
        Lotto manualLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(Arrays.asList(manualLotto), 3);
        LottoDAO.addTotalLottos(lottos);
    }
}
