package lotto;

import lotto.dao.GameResultDao;
import lotto.dao.TurnDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.domain.exception.*;
import lotto.util.GameResultDtoConverter;
import lotto.util.LottoParser;
import lotto.util.RandomNumbersGenerator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final int START_COUNT = 0;
    private static final String SENTENCE_DELIMITER = "\\n";
    private static final String GO_BACK_ELEMENT = "<button onclick=\"history.back()\">메인 페이지로 돌아가기</button>";

    public static void main(String[] args) {
        LottoService service = new LottoService();
        TurnDao turnDao = new TurnDao();

        handleException();

        get("/", (req, res) -> renderMain(turnDao));
        get("/turn", (req, res) ->
                renderTurnInfo(service, req)
        );
        post("/money", (req, res) ->
                renderLottoShopping(service, req)
        );
        post("/lottos", (req, res) ->
                renderLottos(service, req)
        );
        post("/winning", (req, res) ->
                renderResult(service, req)
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

    private static String renderMain(TurnDao turnDao) {
        Map<String, Object> model = new HashMap<>();
        model.put("current_turn", turnDao.findNext());
        model.put("turns", turnDao.findAll());
        return render(model, "main.html");
    }

    private static String renderTurnInfo(LottoService service, Request req) {
        Map<String, Object> model = new HashMap<>();
        int turn = Integer.parseInt(req.queryParams("current_turn"));
        GameResultDto result = new GameResultDao().findByTurn(turn);
        model.put("turn", turn);
        model.put("lottos", service.findAllByTurn(turn));
        model.put("winning_lotto", new WinningLottoDao().findByTurn(turn));
        model.put("result", stringifyResult(result));
        model.put("profit", result.getProfit());
        return render(model, "turn_info.html");
    }

    private static String renderLottoShopping(LottoService service, Request req) {
        Map<String, Object> model = new HashMap<>();
        int money = Integer.parseInt(req.queryParams("money"));
        service.charge(money);
        return render(model, "lotto_shopping.html");
    }

    private static String renderResult(LottoService service, Request req) {
        Map<String, Object> model = new HashMap<>();
        LottoParser parser = new LottoParser();

        Lotto lotto = parser.parseLotto(req.queryParams("winninglotto"));
        LottoNumber lottoNumber = parser.parseLottoNumber(Integer.parseInt(req.queryParams("bonusnumber")));
        WinningLotto winningLotto = WinningLotto.of(lotto, lottoNumber);
        new WinningLottoDao().add(winningLotto, new TurnDao().findNext());

        GameResult gameResult = service.gameResult();
        gameResult.match(winningLotto);
        model.put("profit", String.format("%.1f", gameResult.profit(LottoMachine.LOTTO_MONEY)));
        model.put("stat", stringifyResult(new GameResultDtoConverter().convertResultToDto(gameResult)));
        new GameResultDao().add(new GameResultDtoConverter().convertResultToDto(gameResult), new TurnDao().findNext());

        return render(model, "result.html");
    }

    private static String renderLottos(LottoService service, Request req) {
        Map<String, Object> model = new HashMap<>();

        String origin = req.queryParams("numbers");
        List<String> numbers = Arrays.asList(origin.split(SENTENCE_DELIMITER));
        int manualCount = assignManualPurchaseCount(service, numbers);
        int autoCount = assignAutoPurchaseCount(service);

        model.put("manualCount", manualCount);
        model.put("autoCount", autoCount);

        List<LottoDto> lottos = service.getLottos();
        model.put("lottos", lottos);

        return render(model, "lottos.html");
    }

    private static void renderError(final String message, final Response res) {
        res.body(message + "<br/>" + GO_BACK_ELEMENT);
    }

    private static String renderEndPage(LottoService service, Request req) {
        Map<String, Object> model = new HashMap<>();
        service.vacateMoney();
        if (req.queryParams("token").equals("restart")) {
            return backToMain(model);
        }
        return backToInitial(service, model);
    }

    private static String backToInitial(LottoService service, Map<String, Object> model) {
        new GameResultDao().deleteAll();
        new WinningLottoDao().deleteAll();
        service.deleteAll();
        new TurnDao().deleteAll();
        return render(model, "main.html");
    }

    private static String backToMain(Map<String, Object> model) {
        new TurnDao().add();
        model.put("current_turn", new TurnDao().findNext());
        model.put("turns", new TurnDao().findAll());
        return render(model, "main.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static int assignManualPurchaseCount(final LottoService service, final List<String> numbers) {
        int manualCount = START_COUNT;
        for (final String number : numbers) {
            manualCount = getManualCount(service, manualCount, number);
        }
        return manualCount;
    }

    private static int getManualCount(LottoService service, int manualCount, String number) {
        if (service.canBuy()) {
            Lotto lotto = new LottoParser().parseLotto(number);
            service.buy(lotto);
            manualCount++;
        }
        return manualCount;
    }

    private static int assignAutoPurchaseCount(final LottoService service) {
        RandomNumbersGenerator generator = RandomNumbersGenerator.getInstance();
        int autoPurchaseCount = START_COUNT;
        for (; service.canBuy(); autoPurchaseCount++) {
            Lotto lotto = new LottoFactory().create(generator.generate());
            service.buy(lotto);
        }
        return autoPurchaseCount;
    }

    private static List<String> stringifyResult(final GameResultDto result) {
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
}
