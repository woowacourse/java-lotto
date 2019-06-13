package lotto;

import lotto.controller.LottoController;
import lotto.controller.StatController;
import lotto.dao.GameStatDao;
import lotto.dao.TurnDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.exception.*;
import lotto.dto.GameStatDto;
import lotto.service.LottoService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final String GO_BACK_ELEMENT = "<button onclick=\"history.back()\">메인 페이지로 돌아가기</button>";
    private static final String RESTART = "restart";

    public static void main(String[] args) {
        final LottoService service = LottoService.getInstance();
        final LottoController lottoController = LottoController.getInstance();
        final StatController statController = StatController.getInstance();
        handleException();
        get("/", (req, res) -> renderMain());
        get("/turn/:index", (req, res) ->
                renderTurnInfo(service, req)
        );
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
                renderEndPage(service, req)
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

    //TODO TurnDao만 쓴다
    private static String renderMain() {
        Map<String, Object> model = new HashMap<>();
        TurnDao turnDao = TurnDao.getInstance();
        model.put("current_turn", turnDao.findNext());
        model.put("turns", turnDao.findAll());
        return render(model, "main.html");
    }

    // TODO 회차당 정보
    private static String renderTurnInfo(final LottoService service, final Request req) {
        Map<String, Object> model = new HashMap<>();
        int turn = Integer.parseInt(req.params("index"));
        GameStatDto result = findResultByTurn(turn);
        model.put("turn", turn);
        model.put("lottos", service.findAllByTurn(turn));
        model.put("winning_lotto", findWinningLottoByTurn(turn));
        model.put("result", stringifyResult(result));
        model.put("profit", result.getProfit());
        return render(model, "turn_info.html");
    }

    private static WinningLotto findWinningLottoByTurn(final int turn) {
        return WinningLottoDao.getInstance().findByTurn(turn);
    }

    private static GameStatDto findResultByTurn(final int turn) {
        return GameStatDao.getInstance().findByTurn(turn);
    }

    private static List<String> stringifyResult(final GameStatDto result) {
        List<String> results = new ArrayList<>();
        for (Rank rank : Rank.reverseValues()) {
            results.add(stringifyRank(rank) + result.getCount(rank) + "개");
        }
        return results;
    }

    private static String stringifyRank(final Rank rank) {
        StringBuilder sb = new StringBuilder();
        sb.append(rank.getMatchCount() + "개 일치");
        if (rank == Rank.SECOND) {
            sb.append(", 보너스 볼 일치");
        }
        sb.append("(" + rank.getMoney() + ")- ");
        return sb.toString();
    }

    private static void renderError(final String message, final Response res) {
        res.body(message + "<br/>" + GO_BACK_ELEMENT);
    }

    private static String renderEndPage(final LottoService service, final Request req) {
        Map<String, Object> model = new HashMap<>();
        service.vacateMoney();
        if (req.queryParams("token").equals(RESTART)) {
            return backToMain(model);
        }
        return backToInitial(service, model);
    }

    //TODO
    private static String backToInitial(final LottoService service, Map<String, Object> model) {
        GameStatDao.getInstance().deleteAll();
        WinningLottoDao.getInstance().deleteAll();
        service.deleteAll();
        TurnDao.getInstance().deleteAll();
        return render(model, "main.html");
    }

    //TODO
    private static String backToMain(Map<String, Object> model) {
        TurnDao.getInstance().add();
        model.put("current_turn", findNextTurn());
        model.put("turns", TurnDao.getInstance().findAll());
        return render(model, "main.html");
    }

    //TODO
    private static Integer findNextTurn() {
        return TurnDao.getInstance().findNext();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
