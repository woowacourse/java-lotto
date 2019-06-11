package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import lotto.domain.lotto.Number;
import lotto.domain.lotto.Lotto;
import lotto.utils.InputParser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameDAOTest {
    private GameDAO gameDAO;

    @BeforeEach
    void setUp() {
        gameDAO = new GameDAO();
    }

    @Test
    void connection() {
        Connection con = gameDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    void addGameInformation_check() throws SQLException {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setWinningNumbers(new Lotto(InputParser.parseLotto("1, 2, 3, 4, 5, 6")));
        gameDTO.setBonusNumber(Number.of(7));
        gameDTO.setResult("1111");
        gameDTO.setReturnRate("10%");
        gameDTO.setReturnAmount("1000");
        gameDAO.addGameInformation(gameDTO);
    }

    @Test
    void maxId() throws SQLException {
        assertThat(gameDAO.getCount()).isEqualTo(1);
    }

    @Test
    void addLottoNumbers() throws SQLException {
        List<Lotto> lottoList = Arrays.asList(
                new Lotto(InputParser.parseLotto("1, 2, 3, 4, 5, 6")),
                new Lotto(InputParser.parseLotto("2, 3, 4, 5, 6, 7")),
                new Lotto(InputParser.parseLotto("3, 4, 5, 6, 7, 8"))
        );
        gameDAO.addLottoNumbers(lottoList);
    }

    @Test
    void getGamesTotalColumns() throws SQLException {
        assertThat(gameDAO.getGamesTotalColumns()).isEqualTo(7);
    }

    @Test
    void getGameInformation() throws SQLException {
        gameDAO.getGameInformation(2);
    }
}