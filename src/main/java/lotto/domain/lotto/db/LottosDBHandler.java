package lotto.domain.lotto.db;

import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.db.dao.LottosDAO;

import java.sql.SQLException;

public class LottosDBHandler implements DBHandler<Lottos> {
    @Override
    public void add(Lottos lottos) throws SQLException {

    }

    public void add(Lottos lottos, int round) throws SQLException {
        LottosDAO lottosDAO = new LottosDAO
                (new ConnectionHandler("lottoGame"));
        lottosDAO.create();
        lottosDAO.add(lottos, round);
        lottosDAO.closeConnection();
    }

    @Override
    public Lottos foundById(int id) throws SQLException {
        LottosDAO lottosDAO = new LottosDAO
                (new ConnectionHandler("lottoGame"));
        lottosDAO.create();
        Lottos lottos = lottosDAO.foundById(id);
        lottosDAO.closeConnection();
        return lottos;
    }
}
