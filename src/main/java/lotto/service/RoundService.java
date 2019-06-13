package lotto.service;

import lotto.dao.LottoTicketsDao;
import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResults;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.domain.factory.LottoResultsFactory;

import java.sql.Connection;
import java.sql.SQLException;


public class RoundService {
    private final Connection connection;

    public RoundService(Connection connection) {
        this.connection = connection;
    }

    public int getRound() throws SQLException {
        RoundDao roundDao = new RoundDao(connection);
        return roundDao.findMaxRound();
    }

    public LottoResults getLottoResults(String inputRound) throws SQLException {
        int round = Integer.parseInt(inputRound);
        WinningLotto winningLotto = getWinningLotto(round);
        LottoTickets lottoTickets = getLottoTickets(round);
        LottoMoney lottoMoney = new LottoMoney(getMoney(round));
        return LottoResultsFactory.create(lottoTickets, winningLotto, lottoMoney);
    }

    private int getMoney(int round) throws SQLException {
        RoundDao roundDao = new RoundDao(connection);
        return roundDao.findMoneyByRound(round);
    }

    private WinningLotto getWinningLotto(int round) throws SQLException {
        WinningLottoDao winningLottoDao = new WinningLottoDao(connection);
        return winningLottoDao.findWinningLottoByRound(round);
    }

    private LottoTickets getLottoTickets(int round) throws SQLException {
        LottoTicketsDao lottoTicketsDao = new LottoTicketsDao(connection);
        return lottoTicketsDao.findLottoByRound(round);
    }
}
