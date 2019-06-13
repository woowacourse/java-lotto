package lotto.domain.dao;

import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDAOTest {
    @Test
    void 로또들_번호_저장하기() throws SQLException {
        Lotto manualLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(Arrays.asList(manualLotto), 3);
        LottoDAO.addTotalLottos(lottos);
    }

    @Test
    void 구매한_로또_조회하기() throws SQLException {
        List<Lotto> searchLotto = Arrays.asList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(20, 23, 35, 37, 41, 45)));
        assertThat(LottoDAO.searchLottoNumbers(6)).isEqualTo(searchLotto);
    }
}
