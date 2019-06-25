package lotto;

import com.google.gson.Gson;
import lotto.domain.LottoResult;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.presentation.UserLottoPresentation;
import lotto.presentation.WinningLottoPresentation;
import lotto.service.LottoResultService;
import lotto.service.UserLottoService;
import lotto.service.WinningLottoService;
import lotto.view.WebView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("templates");
        staticFiles.location("templates");
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/buyLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "buy.html");
        });

        post("/lotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lotto.html");
        });

        post("/userLotto", (req, res) -> {
            UserLottoPresentation presentation = WebView.userLottoPresentation(req);
            UserLottos userLottos = UserLottoService.userLottos(presentation.getLottoMoney(), presentation.getManualCount(), presentation.getManualNumbers());
            return WebView.userLottoJson(userLottos);
        }, gson::toJson);

        post("/winningLotto", (req, res) -> {
            WinningLottoPresentation presentation = new WinningLottoPresentation(req.queryParams("numbers"), req.queryParams("bonus"));
            WinningLotto winningLotto = WinningLottoService.insertWinningLotto(presentation.getNumbers(), presentation.getBonus());
            LottoResultService.insertCurrentLottoResult();
            return WebView.winningLottoJson(winningLotto);
        }, gson::toJson);

        get("/lottoResult", (req, res) -> {
            LottoResult lottoResult = LottoResultService.insertCurrentLottoResult();
            return WebView.lottoResultJson(lottoResult);
        }, gson::toJson);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
