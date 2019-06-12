package lotto.database;

import lotto.domain.LottoGame;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningInformation;
import lotto.view.OutputViewFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RoundInformationService {
    private RoundInformationService() {
        throw new AssertionError();
    }

    public static void saveRoundInformation(Lottos lottos, WinningInformation winningInformation) throws Exception {
        try (Connection connection = DatabaseConnection.getConnection()) {
            WinningInformationDAO winningInformationDAO = WinningInformationDAO.getInstance(connection);
            LottoDAO lottoDAO = LottoDAO.getInstance(connection);

            int round = winningInformationDAO.addWinningInformation(winningInformation);
            lottoDAO.addAllLottos(lottos, round);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static Map<String, Object> loadRoundInformation(int round) throws Exception {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Map<String, Object> model = new HashMap<>();

            WinningInformationDAO winningInformationDAO = WinningInformationDAO.getInstance(connection);
            WinningInformation winningInformation = winningInformationDAO.findWinningInformationByRound(round);
            model.put("winningInfo", OutputViewFactory.ouputWinningInfo(winningInformation));

            LottoDAO lottoDAO = LottoDAO.getInstance(connection);
            Lottos lottos = lottoDAO.findLottosByRound(round);
            model.put("lottos", OutputViewFactory.outputLottos(lottos));

            LottoGame lottoGame = new LottoGame(winningInformation);
            LottoResult lottoResult = lottoGame.play(lottos);
            model.put("result", OutputViewFactory.outputResult(lottoResult));
            model.put("yieldMessage", OutputViewFactory.outputYield(lottoResult));
            return model;
        } catch (Exception e) {
            throw e;
        }
    }

}
