package lotto.service;

import lotto.db.dao.LottoGameDAO;
import lotto.db.dao.LottoTicketDAO;
import lotto.domain.*;
import lotto.domain.dto.LottoGameResultDto;
import lotto.domain.dto.WinningLottoDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lotto.db.dao.LottoGameDAO.addWinningLotto;
import static lotto.db.dao.LottoGameDAO.addWinningLottoIdIntoLottoGame;
import static lotto.db.dao.LottoTicketDAO.*;

public class LottoGameService {
    private static final int MONEY_OFFSET = 1_000;
    private static final int TYPE_OF_NORMAL_LOTTO = 0;
    private static final int TYPE_OF_WINNING_LOTTO = 1;
    private static final int ZERO_COUNT = 0;

    public static void addLottoTicket(LottoTickets inputLottoTickets) throws SQLException {
        List<LottoTicket> lottoTickets = inputLottoTickets.getLottoTickets();
        for (LottoTicket lottoTicket : lottoTickets) {
            int lotto_id = addLotto(TYPE_OF_NORMAL_LOTTO);
            addLottoNumbers(lotto_id, lottoTicket.getLottoNumbers());
            addLottoGame(lotto_id);
        }
    }

    public static void addWinningLottoTicket(String lottoNumbers, String bonusBall) throws SQLException {
        WinningLotto winningLotto = WinningLotto.of(lottoNumbers, Integer.parseInt(bonusBall));
        List<LottoNumber> parsed_lottoNumbers = winningLotto.getWinningNumbers().getLottoNumbers();

        int lotto_id = addLotto(TYPE_OF_WINNING_LOTTO);
        addLottoNumbers(lotto_id, parsed_lottoNumbers);
        int winning_id = addWinningLotto(winningLotto, lotto_id);
        addWinningLottoIdIntoLottoGame(winning_id);
    }

    public static LottoGameResultDto findLatestLottoGameResult() throws SQLException {
        WinningLottoDto winningLotto = LottoGameDAO.findLatestWinningLotto();
        return getLottoGameResultDto(winningLotto);
    }

    public static LottoGameResultDto findResultByWinningLottoId(String winningLottoId) throws SQLException {
        WinningLottoDto winningLotto = LottoGameDAO.findByWinningLottoId(winningLottoId);
        return getLottoGameResultDto(winningLotto);
    }

    private static LottoGameResultDto getLottoGameResultDto(WinningLottoDto winningLotto) throws SQLException {
        List<LottoTicket> lottoTickets = LottoTicketDAO.findLottosByLottoId(winningLotto.getWinningLottoId());
        WinStatistics winStatistics = new WinStatistics(lottoTickets, WinningLotto.of(winningLotto.getWinningNumbers(), winningLotto.getBonusBall()));
        double incomingRate = lottoTickets.size() > ZERO_COUNT ? winStatistics.calculateProfitRate(lottoTickets.size() * MONEY_OFFSET) : ZERO_COUNT;

        return new LottoGameResultDto(
                winningLotto.getWinningLottoId(), winningLotto.getWinningNumbers(), winningLotto.getBonusBall(),
                lottoTickets, getEachRank(winStatistics), winStatistics.getProfit(), incomingRate);
    }

    private static List<String> getEachRank(WinStatistics winStatistics) {
        List<String> results = new ArrayList<>();
        for (RankType rankType : RankType.values()) {
            int matchingCount = rankType.getMatchingCount();
            int prize = rankType.getPrize();
            int count = winStatistics.getCountOfResult().get(rankType);

            if (rankType.equals(RankType.SECOND)) {
                results.add(String.format("%d개 일치, 보너스 볼 일치(%d원) - %d개", matchingCount, prize, count));
                continue;
            }
            if (rankType.equals(RankType.NOTHING)) {
                continue;
            }
            results.add(String.format("%d개 일치 (%d원) - %d개", matchingCount, prize, count));
        }
        return results;
    }
}
