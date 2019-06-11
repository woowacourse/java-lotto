package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import lotto.domain.lotto.Number;
import lotto.domain.lotto.Lotto;
import lotto.utils.InputParser;

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
    void name() throws SQLException {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setWinningNumbers(new Lotto(InputParser.parseLotto("1, 2, 3, 4, 5, 6")));
        gameDTO.setBonusNumber(Number.of(7));
        gameDTO.setResult("1111");
        gameDTO.setReturnRate("10%");
        gameDTO.setReturnAmount("1000");
        gameDAO.add(gameDTO);
    }
}