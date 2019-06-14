package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.dto.LottoDTO;
import lotto.database.DBConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDAOTest {
    private Lotto lotto;
    private LottoDAO lottoDao;

    @BeforeEach
    public void setUp() {
        lottoDao = new LottoDAO(DBConnector.getConnection());

        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    public void addTest() throws SQLException {
        LottoDTO lottoDto = lotto.toDTO(100);

        List<LottoDTO> lottoDtos = new ArrayList<>();
        lottoDtos.add(lottoDto);

        lottoDao.addLotto(lottoDtos);
    }

    @Test
    public void findByRoundTest() throws SQLException {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lottos.add(lotto);
        }
        assertThat(lottoDao.findByRound(19)).isEqualTo(lottos);
    }
}
