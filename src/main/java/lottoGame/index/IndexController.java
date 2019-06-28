package lottoGame.index;

import domain.LottoGroup;
import domain.LottoSimulator;
import domain.RankAnalysis;
import domain.WinningLotto;
import dto.GameResult;
import lottoGame.lotto.LottoDAO;
import lottoGame.lotto.WinningLottoDAO;
import lottoGame.util.ViewName;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexController {
    public static Route serveIndexPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("lottoGameResults", generateGameResults());

        return new HandlebarsTemplateEngine().render(new ModelAndView(model, ViewName.Handlebars.INDEX));
    };

    private static List<GameResult> generateGameResults() {
        List<GameResult> results = new ArrayList<>();

        for (int token : WinningLottoDAO.getInstance().findValidTokens()) {
            LottoGroup lottoGroup = LottoDAO.getInstance().findLottoGroup(token);
            WinningLotto winningLotto = WinningLottoDAO.getInstance().findByToken(token);
            RankAnalysis rankAnalysis = LottoSimulator.analyze(winningLotto, lottoGroup);
            results.add(GameResult.of(
                    token,
                    lottoGroup,
                    winningLotto,
                    rankAnalysis.toDTO()
            ));
        }

        return results;
    }
}
