package lottoGame.analysis;

import domain.LottoSimulator;
import domain.RankAnalysis;
import dto.RankAnalysisDTO;
import lottoGame.lotto.LottoDAO;
import lottoGame.lotto.WinningLottoDAO;
import lottoGame.util.Path;
import lottoGame.util.ViewName;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class LottoAnalysisController {
    public static Route serveLottoAnalysis = (req, res) -> {
        int token = Integer.parseInt(req.queryParams("token"));

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);

        RankAnalysis rankAnalysis = LottoSimulator.analyze(
                WinningLottoDAO.getInstance().findByToken(token),
                LottoDAO.getInstance().findLottoGroup(token)
        );

        // TODO: RankAnalysis 와 RankAnalysisDTO 의존관계 분리 (Assembler 만들자)
        RankAnalysisDTO dto = rankAnalysis.toDTO();
        model.put("rankAnalysis", dto);

        return new HandlebarsTemplateEngine().render(
                new ModelAndView(model, ViewName.Handlebars.LOTTO_ANALYSIS));
    };

    public static Route handleLottoAnalysisPost = (req, res) -> {
        res.redirect(Path.Web.INDEX);
        return null;
    };
}
