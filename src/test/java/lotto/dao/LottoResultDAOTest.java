package lotto.dao;

import lotto.domain.lottoresult.LottoRank;
import lotto.domain.lottoresult.LottoResult;
import lotto.dto.LottoResultDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static lotto.dao.DBUtil.getConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoResultDAOTest {
    LottoResultDAO lottoResultDAO;
    Connection connection;
    LottoResult lottoResult;
    LottoResultDTO lottoResultDTO;

    @BeforeEach
    void setUp() throws Exception {
        connection = getConnection();
        connection.setAutoCommit(false);
        lottoResultDAO = new LottoResultDAO(connection);

        lottoResult = new LottoResult(Arrays.asList(LottoRank.FIFTH));
        lottoResultDTO = new LottoResultDTO();
        lottoResultDTO.setEarningRate(lottoResult.getEarningRate());
        lottoResultDTO.setWinningReward(lottoResult.getRewards());
        lottoResultDTO.setRankCounts(lottoResult.getRankCounts());
    }

    //에러 발생1 (해당 라운드 값이 round 테이블에 존재하지 않아 외래키가 없음)
    @Test
    public void insertTest1() {
        assertThrows(SQLException.class, () ->
                lottoResultDAO.insertResult(100, lottoResultDTO)
        );
    }


    //에러 발생2 (해당 라운드의 로또 결과 이미 존재)
    @Test
    public void insertTest2() {
        assertThrows(SQLException.class, () ->
                lottoResultDAO.insertResult(1, lottoResultDTO)
        );
    }

    //정상 실행 (외래키 존재, 해당 라운드 로또 결과 존재하지 않음)
    @Test
    public void insertTest3() {
        assertDoesNotThrow(() -> {
            new LottoRoundDAO(connection).insertRound(200);
            lottoResultDAO.insertResult(200, lottoResultDTO);
        });
    }

    //존재하는 라운드 결과
    @Test
    public void selectTest1() throws Exception {
        new LottoRoundDAO(connection).insertRound(200);
        lottoResultDAO.insertResult(200, lottoResultDTO);

        LottoResultDTO testLottoResultDTO = lottoResultDAO.selectLottoResultByRound(200);

        assertThat(testLottoResultDTO.getEarningRate())
                .isEqualTo(lottoResultDTO.getEarningRate());
        assertThat(testLottoResultDTO.getWinningReward())
                .isEqualTo(lottoResultDTO.getWinningReward());
        assertThat(testLottoResultDTO.getRankCounts())
                .isEqualTo(lottoResultDTO.getRankCounts());
    }

    //존재하지 않는 경우
    @Test
    public void selectTest2() throws SQLException {
        LottoResultDTO testLottoResultDTO = lottoResultDAO.selectLottoResultByRound(200);

        assertThat(testLottoResultDTO.getEarningRate()).isNull();
        assertThat(testLottoResultDTO.getWinningReward()).isNull();
        assertThat(testLottoResultDTO.getRankCounts()).isNull();
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }
}