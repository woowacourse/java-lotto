package lotto.database.dao;

import lotto.database.JdbcConnector;
import lotto.dto.ResultDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

class ResultDAOTest {
    private Connection con;
    private ResultDAO resultDAO;

    @BeforeEach
    public void setUp() throws Exception {
        this.con = JdbcConnector.getConnection();
        this.resultDAO = new ResultDAO(con);
        con.setAutoCommit(false);
    }

    @Test
    void addResult() throws SQLException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setRound(2);
        resultDTO.setWinningRate(0.22);
        resultDTO.setRanks(Arrays.asList(1, 2, 3, 4, 5, 6));
        resultDAO.addResult(resultDTO);
    }


    @AfterEach
    void tearDown() throws SQLException {
        con.rollback();
        con.close();
    }

}