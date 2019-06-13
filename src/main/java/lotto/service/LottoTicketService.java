package lotto.service;

import lotto.dao.LottoTicketsDao;
import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResults;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.domain.factory.LottoResultsFactory;
import lotto.domain.factory.LottoTicketsFactory;
import lotto.domain.factory.WinningLottoFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LottoTicketService {
    private final Connection connection;

    public LottoTicketService(Connection connection) {
        this.connection = connection;
    }

    public LottoTickets showLotto(String inputManuals, String manualAmount, String inputMoney) {
        int manualAmounts = Integer.parseInt(manualAmount);
        LottoMoney lottoMoney = getMoney(inputMoney);
        List<String> manualLottoTickets = getManualLottoTickets(inputManuals);
        return LottoTicketsFactory.create(manualAmounts, manualLottoTickets, lottoMoney);
    }

    private List<String> getManualLottoTickets(String inputManuals) {
        return Arrays.asList(inputManuals.replaceAll("\r", "").split("\n"));
    }

    public LottoMoney getMoney(String inputMoney) {
        return new LottoMoney(Long.parseLong(inputMoney));
    }

    public WinningLotto getWinningLotto(String inputWinningLotto, String inputBonusBall) {
        return WinningLottoFactory.create(inputWinningLotto, Integer.parseInt(inputBonusBall));
    }

    public LottoResults getResults(LottoTickets lottoTickets, WinningLotto winningLotto, LottoMoney money) {
        return LottoResultsFactory.create(lottoTickets, winningLotto, money);
    }

    public void addDataBase(LottoTickets lottoTickets, LottoMoney money, WinningLotto winningLotto) throws SQLException {
        int round = findThisRound() + 1;
        addRoundToDB(money, round);
        addLottoTicketsToDB(lottoTickets, round);
        addWinningLottoToDB(winningLotto, round);
    }

    private int findThisRound() throws SQLException {
        RoundDao roundDao = new RoundDao(connection);
        return roundDao.findMaxRound();
    }

    private void addRoundToDB(LottoMoney lottoMoney, int round) throws SQLException {
        RoundDao roundDao = new RoundDao(connection);
        roundDao.addRound(round, lottoMoney);
    }

    private void addWinningLottoToDB(WinningLotto winningLotto, int round) throws SQLException {
        WinningLottoDao winningLottoDao = new WinningLottoDao(connection);
        winningLottoDao.addWinningLotto(round, winningLotto);
    }

    private void addLottoTicketsToDB(LottoTickets lottoTickets, int round) throws SQLException {
        LottoTicketsDao lottoTicketsDao = new LottoTicketsDao(connection);
        lottoTicketsDao.addLottoTickets(round, lottoTickets);
    }
}
