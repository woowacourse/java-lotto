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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.ServiceMessage.*;

public class LottoTicketService {
    private final Connection connection;

    public LottoTicketService(Connection connection) {
        this.connection = connection;
    }

    public Map<String, Object> lottoBuy(String inputMoney, String manualAmount) {
        Map<String, Object> model = new HashMap<>();
        model.put(MONEY.type(), inputMoney);
        model.put(MANUAL_AMOUNT.type(), manualAmount);
        return model;
    }

    public Map<String, Object> showLotto(String inputManuals, String manualAmount, String lottoMoney) {
        Map<String, Object> model = new HashMap<>();
        int manualAmounts = Integer.parseInt(manualAmount);
        LottoMoney lottoMoney1 = new LottoMoney(Integer.parseInt(lottoMoney));
        List<String> manualLottoTickets = getManualLottoTickets(inputManuals);
        LottoTickets lottoTickets = LottoTicketsFactory.create(manualAmounts, manualLottoTickets, lottoMoney1);
        model.put(LOTTO_TICKETS.type(), lottoTickets);
        return model;
    }

    private List<String> getManualLottoTickets(String inputManuals) {
        return Arrays.asList(inputManuals.replaceAll("\r", "").split("\n"));
    }

    public Map<String, Object> showResult(String inputWinningLotto, String inputBonusBall, LottoTickets lottoTickets, String inputMoney) throws SQLException {
        LottoMoney money = new LottoMoney(Long.parseLong(inputMoney));
        WinningLotto winningLotto = WinningLottoFactory.create(inputWinningLotto, Integer.parseInt(inputBonusBall));
        LottoResults lottoResults = LottoResultsFactory.create(lottoTickets, winningLotto, money);

        addDataBase(lottoTickets, money, winningLotto);
        Map<String, Object> model = getModel(lottoTickets, money, winningLotto, lottoResults);
        return model;
    }

    private Map<String, Object> getModel(LottoTickets lottoTickets, LottoMoney money, WinningLotto winningLotto, LottoResults lottoResults) {
        Map<String, Object> model = new HashMap<>();
        model.put(LOTTO_TICKETS.type(), lottoTickets);
        model.put(MONEY.type(), money);
        model.put(WINNING_LOTTO.type(), winningLotto);
        model.put(LOTTO_RESULTS.type(), lottoResults);
        model.put(REWARD_MONEY.type(), lottoResults.getYield());
        return model;
    }

    private void addDataBase(LottoTickets lottoTickets, LottoMoney money, WinningLotto winningLotto) throws SQLException {
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
