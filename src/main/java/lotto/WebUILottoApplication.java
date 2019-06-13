package lotto;

import lotto.controller.RoundController;
import lotto.controller.LottoController;
import lotto.controller.InitialViewController;
import lotto.controller.StatController;
import lotto.domain.exception.*;
import lotto.service.LottoService;
import spark.ModelAndView;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final String GO_BACK_ELEMENT = "<button onclick=\"history.back()\">메인 페이지로 돌아가기</button>";

    public static void main(String[] args) {
        final LottoService service = LottoService.getInstance();
        final LottoController lottoController = LottoController.getInstance();
        final StatController statController = StatController.getInstance();
        final InitialViewController initialViewController = InitialViewController.getInstance();
        final RoundController roundController = RoundController.getInstance();

        handleException();
        get("/", (req, res) ->
                render(initialViewController.processMain(), "main.html")
        );
        get("/turn/:index", (req, res) -> render(roundController.processTurnInfo(req), "turn_info.html"));
        post("/money", (req, res) ->
                render(lottoController.processLottoShopping(req), "lotto_shopping.html")
        );
        post("/lottos", (req, res) ->
                render(lottoController.processLottos(req), "lottos.html")
        );
        post("/result", (req, res) ->
                render(statController.processResult(req), "result.html")
        );
        post("/end", (req, res) ->
                render(roundController.processEndPage(req), "main.html")
        );
    }

    private static void handleException() {
        exception(LackOfMoneyException.class, (e, req, res) ->
                renderError("돈을 1000원 이상 입력하세요", res));
        exception(CountsOfLottoException.class, (e, req, res) ->
                renderError("6개의 로또 번호를 입력하세요", res));
        exception(BoundOfNumberException.class, (e, req, res) ->
                renderError("1에서 45사이의 번호를 입력하세요", res));
        exception(NumberDuplicationException.class, (e, req, res) ->
                renderError("서로 다른 로또 번호를 입력하세요", res));
        exception(WinningLottoHasBonusException.class, (e, req, res) ->
                renderError("당첨 번호중에 보너스 번호가 있습니다", res)
        );
    }

    private static void renderError(final String message, final Response res) {
        res.body(message + "<br/>" + GO_BACK_ELEMENT);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
