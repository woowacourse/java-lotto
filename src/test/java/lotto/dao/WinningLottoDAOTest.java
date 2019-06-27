package lotto.dao;

import lotto.domain.lotto.LottoNumberGroup;
import lotto.domain.lottoresult.WinningLotto;
import lotto.dto.WinningLottoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static lotto.dao.DBConnection.getConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoDAOTest {
    WinningLottoDao winningLottoDAO;
    Connection connection;
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() throws Exception {
        connection = getConnection();
        connection.setAutoCommit(false);
        jdbcTemplate = JdbcTemplate.getInstance(connection);
        winningLottoDAO = WinningLottoDao.getInstance(jdbcTemplate);
    }

    //에러 발생1 (해당 라운드 값이 round 테이블에 존재하지 않아 외래키가 없음)
    @Test
    public void insertTest1() {
        assertThrows(SQLException.class, () ->
                winningLottoDAO.insertWinningLotto(100, WinningLotto.create("1, 2, 3, 4, 5, 6", "7"))
        );
    }


    //에러 발생2 (해당 라운드의 당첨 로또 이미 존재)
    @Test
    public void insertTest2() {
        assertThrows(SQLException.class, () ->
                winningLottoDAO.insertWinningLotto(1, WinningLotto.create("1, 2, 3, 4, 5, 6", "7"))
        );
    }

    //정상 실행 (외래키 존재, 해당 라운드 당첨 로또 존재하지 않음)
    @Test
    public void insertTest3() {
        assertDoesNotThrow(() -> {
            LottoRoundDao.getInstance(jdbcTemplate).insertRound(200);
            winningLottoDAO.insertWinningLotto(200, WinningLotto.create("1, 2, 3, 4, 5, 6", "7"));
        });
    }

    //존재하는 라운드 결과
    @Test
    public void selectTest1() throws Exception {
        LottoRoundDao.getInstance(jdbcTemplate).insertRound(200);
        winningLottoDAO.insertWinningLotto(200, WinningLotto.create("1, 2, 3, 4, 5, 6", "7"));

        WinningLottoDTO winningLottoDTO = winningLottoDAO.selectByLottoRound(200);

        assertThat(winningLottoDTO.getBonusNumber()).isEqualTo(7);
        assertThat(winningLottoDTO.getWinningNumbers())
                .isEqualTo(LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    //존재하지 않는 경우
    @Test
    public void selectTest2() throws SQLException {
        WinningLottoDTO winningLottoDTO = winningLottoDAO.selectByLottoRound(200);

        assertThat(winningLottoDTO.getBonusNumber()).isEqualTo(0);
        assertThat(winningLottoDTO.getWinningNumbers())
                .isEqualTo(null);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }
}