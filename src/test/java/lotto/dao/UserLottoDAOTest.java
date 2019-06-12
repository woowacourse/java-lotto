package lotto.dao;

import lotto.domain.Lotteries;
import lotto.domain.Lotto;
import lotto.domain.customcreatelotto.DefaultCustomCreateCreateLotto;
import lotto.dto.UserLottoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author heebg
 * @version 1.0 2019-06-12
 */
class UserLottoDAOTest {
    List<UserLottoDTO> userLottoDTOS;
    Lotteries lotteries;

    @BeforeEach
    void setUp() {
        userLottoDTOS = new ArrayList<>();
        lotteries = new Lotteries();
        lotteries.addCustomLotto(Arrays.asList(1,2,3,4,5,6), new DefaultCustomCreateCreateLotto());
        lotteries.addCustomLotto(Arrays.asList(11,12,13,14,15,16), new DefaultCustomCreateCreateLotto());
        lotteries.addCustomLotto(Arrays.asList(21,12,33,14,5,16), new DefaultCustomCreateCreateLotto());
    }

    @Test
    void connection() {
        Connection connection = UserLottoDAO.getConnection();
        assertNotNull(connection);
    }

    @Test
    void insert() throws SQLException {
        UserLottoDAO.addUserLotto(Lotto.customLotto(Arrays.asList(1,2,3,4,5,6), new DefaultCustomCreateCreateLotto()), 1);
    }

    @Test
    void insert_lotteries() throws SQLException {
        UserLottoDAO.addUserLotteries(lotteries, 2);
    }
}