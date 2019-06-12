package lotto.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lotto.domain.*;
import lotto.domain.autocreatelotto.MockAutoCreateLotto;
import lotto.dto.WinnerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author heebg
 * @version 1.0 2019-06-12
 */
class WinnerDAOTest {
    private WinnerDAO winnerDAO;
    private RankResult rankResult;
    private Lotteries lotteries;
    private WinnerDTO winnerDTO;

    @BeforeEach
    void setUp() {
        winnerDAO = new WinnerDAO();

        lotteries = new Lotteries();
        lotteries.addAutoLotteries(1, new MockAutoCreateLotto());
        rankResult = new RankResult(lotteries, WinnerTest.winner, new Money(1000));
        winnerDTO = new WinnerDTO(rankResult);
    }

    @Test
    void connection() {
        Connection con = WinnerDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    void insert() throws SQLException {
        WinnerDAO.addWinner(winnerDTO);
    }

    @Test
    void findWinnerByTurn() throws SQLException {
        JsonObject jsonObject = WinnerDAO.findWinnerByTurn(2);
        System.out.println(new Gson().toJson(jsonObject));
    }
}