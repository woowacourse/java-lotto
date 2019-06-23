package lotto.service;

import lotto.persistence.Connector;
import lotto.persistence.RoundDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class RoundService {
    public static int getThisLottoRoundId() throws SQLException {
        try (Connection con = Connector.getConnection()) {
            RoundDAO roundDao = new RoundDAO(con);
            return roundDao.getLatestRoundId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
