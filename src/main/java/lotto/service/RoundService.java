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
import java.util.HashMap;
import java.util.Map;

import static lotto.ServiceMessage.*;


public class RoundService {
    private final Connection connection;

    public RoundService(Connection connection) {
        this.connection = connection;
    }

    //TODO 맵에 넣는걸 서비스에서 할지 컨트롤러에서 할지?
    public Map<String, Object> getRound() throws SQLException {
        Map<String, Object> model = new HashMap<>();
        RoundDao roundDao = new RoundDao(connection);
        int round = roundDao.findMaxRound();
        model.put(ROUND.type(), round);
        return model;
    }

    public Map<String, Object> getLottoResults(String inputRound) throws SQLException {
        int round = Integer.parseInt(inputRound);
        WinningLotto winningLotto = getWinningLotto(round);
        LottoTickets lottoTickets = getLottoTickets(round);
        LottoMoney lottoMoney = new LottoMoney(getMoney(round));
        LottoResults lottoResults = LottoResultsFactory.create(lottoTickets, winningLotto, lottoMoney);

        Map<String, Object> model = new HashMap<>();
        model.put(LOTTO_RESULTS.type(), lottoResults);
        model.put(REWARD_MONEY.type(), lottoResults.getYield());
        model.put(ROUND.type(), round);

        return model;
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
