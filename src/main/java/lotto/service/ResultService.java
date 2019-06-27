package lotto.service;

import lotto.database.JdbcConnector;
import lotto.database.dao.ResultDAO;
import lotto.database.dao.WinningLottoDAO;
import lotto.domain.game.Rank;
import lotto.domain.game.WinningLotto;
import lotto.domain.game.WinningResult;
import lotto.domain.ticket.LottoTickets;
import lotto.dto.ResultDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultService {


    public static ResultDTO createResult(int round, LottoTickets lottoTickets, WinningLotto winningLotto) {
        WinningResult winningResult = new WinningResult(lottoTickets, winningLotto);
        ResultDTO resultDTO = new ResultDTO();
        List<Integer> ranks = new ArrayList<>();

        Map<Rank, Integer> rank = winningResult.getRankInformation();

        ranks.add(rank.get(Rank.MISS));
        ranks.add(rank.get(Rank.FIFTH));
        ranks.add(rank.get(Rank.FOURTH));
        ranks.add(rank.get(Rank.THIRD));
        ranks.add(rank.get(Rank.SECOND));
        ranks.add(rank.get(Rank.FIRST));

        resultDTO.setWinningRate(winningResult.getWinningRate());
        resultDTO.setRanks(ranks);
        resultDTO.setRound(round);
        return resultDTO;
    }

    public static void saveResult(ResultDTO resultDTO) throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        new ResultDAO(connection).addResult(resultDTO);
    }

    public static void saveWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        new WinningLottoDAO(connection).addWinningLotto(round, winningLotto.getLottoTicket().getLottoNumbers(), winningLotto.getBonusNumber().getNumber());
    }

    public static Map<String, Object> getLastestResult() throws SQLException {
        Connection connection = JdbcConnector.getConnection();
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("winningRate", new ResultDAO(connection).getLastestRate());
        resMap.put("prize", new ResultDAO(connection).getLastestPrize());
        return resMap;
    }


}
