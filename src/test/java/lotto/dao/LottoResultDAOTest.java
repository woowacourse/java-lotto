package lotto.dao;

import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.dto.LottoResultDTO;
import lotto.database.DBConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultDAOTest {
    private LottoResultDAO lottoResultDao;

    @BeforeEach
    public void setUp() {
        lottoResultDao = new LottoResultDAO(DBConnector.getConnection());
    }

    @Test
    public void addTest() throws SQLException {
        LottoResultDTO lottoResultDto = new LottoResultDTO();
        lottoResultDto.setRound(100);
        lottoResultDto.setFirst(0);
        lottoResultDto.setSecond(1);
        lottoResultDto.setThird(0);
        lottoResultDto.setFourth(0);
        lottoResultDto.setFifth(0);
        lottoResultDto.setMiss(9);

        lottoResultDao.addLottoResult(lottoResultDto);
    }

    @Test
    public void findByRoundTest() throws SQLException {
        LottoResult lottoResult = new LottoResult();
        lottoResult.add(Rank.MISS, 5);

        assertThat(lottoResultDao.findByRound(3)).isEqualTo(lottoResult);
    }
}
