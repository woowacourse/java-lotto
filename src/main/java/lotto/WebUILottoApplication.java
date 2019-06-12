package lotto;

import lotto.domain.dao.LottoDao;
import lotto.domain.dto.ResultDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {

        // TODO: 2019-06-12 Transaction? -> auto commit X -> last logic
        // TODO: 2019-06-12 apply DTO? (or VO?) -> setter or builder pattern
        staticFileLocation("/static");
        port(80);

        // connect -> index page
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<ResultDTO> lottoGames = LottoDao.getInstance().selectAllLottoGame();

            model.put("lottoGames", lottoGames);
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
