package lotto;

import lotto.dao.GameResultDao;
import lotto.dao.TurnDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.domain.exception.*;
import lotto.dto.GameResultDto;
import lotto.dto.LottoDto;
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
    private static final String RESTART = "restart";

    public static void main(String[] args) {
        final LottoService service = new LottoService();
        handleException();
        get("/", (req, res) -> renderMain());
        get("/turn/:index", (req, res) ->
                renderTurnInfo(service, req)
        );
        post("/money", (req, res) ->
                renderLottoShopping(service, req)
        );
        post("/lottos", (req, res) ->
                renderLottos(service, req)
        );
        post("/result", (req, res) ->
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

    private static String renderMain() {
        Map<String, Object> model = new HashMap<>();
        TurnDao turnDao = TurnDao.getInstance();
        model.put("current_turn", turnDao.findNext());
        model.put("turns", turnDao.findAll());
        return render(model, "main.html");
    }

    private static String renderTurnInfo(final LottoService service, final Request req) {
        Map<String, Object> model = new HashMap<>();
        int turn = Integer.parseInt(req.params("index"));
        GameResultDto result = findResultByTurn(turn);
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

    private static GameResultDto findResultByTurn(final int turn) {
        return GameResultDao.getInstance().findByTurn(turn);
    }

    private static String renderLottoShopping(final LottoService service, final Request req) {
        Map<String, Object> model = new HashMap<>();
        int money = Integer.parseInt(req.queryParams("money"));
        service.charge(money);
        return render(model, "lotto_shopping.html");
    }

    private static String renderLottos(final LottoService service, final Request req) {
        Map<String, Object> model = new HashMap<>();

        String origin = req.queryParams("numbers");
        List<String> numbers = splitSentences(origin);
        int manualCount = assignManualPurchaseCount(service, numbers);
        int autoCount = assignAutoPurchaseCount(service);

        model.put("manualCount", manualCount);
        model.put("autoCount", autoCount);

        List<LottoDto> lottos = service.getLottos();
        model.put("lottos", lottos);
        return render(model, "lottos.html");
    }

    private static List<String> splitSentences(String origin) {
        return Arrays.asList(origin.split(SENTENCE_DELIMITER));
    }

    private static String renderResult(final LottoService service, final Request req) {
        Map<String, Object> model = new HashMap<>();

        Lotto lotto = parseLotto(req.queryParams("winninglotto"));
        LottoNumber lottoNumber = parseLottoNumber(Integer.parseInt(req.queryParams("bonusnumber")));
        WinningLotto winningLotto = WinningLotto.of(lotto, lottoNumber);
        addWinningLotto(winningLotto);

        GameResult gameResult = service.gameResult();
        gameResult.match(winningLotto);
        model.put("profit", stringifyProfit(gameResult));
        model.put("stat", stringifyResult(convertResultToDto(gameResult)));
        addGameResult(gameResult);

        return render(model, "result.html");
    }

    private static void addGameResult(GameResult gameResult) {
        GameResultDao.getInstance().add(convertResultToDto(gameResult), findNextTurn());
    }

    private static GameResultDto convertResultToDto(GameResult gameResult) {
        return GameResultDto.of(gameResult);
    }

    private static void addWinningLotto(final WinningLotto winningLotto) {
        WinningLottoDao.getInstance().add(winningLotto, findNextTurn());
    }

    private static LottoNumber parseLottoNumber(int lottonumber) {
        return new LottoParser().parseLottoNumber(lottonumber);
    }

    private static Lotto parseLotto(String winninglotto) {
        return new LottoParser().parseLotto(winninglotto);
    }

    private static String stringifyProfit(GameResult gameResult) {
        return String.format("%.1f", gameResult.profit(LottoMachine.LOTTO_MONEY));
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

    private static String backToInitial(final LottoService service, Map<String, Object> model) {
        GameResultDao.getInstance().deleteAll();
        WinningLottoDao.getInstance().deleteAll();
        service.deleteAll();
        TurnDao.getInstance().deleteAll();
        return render(model, "main.html");
    }

    private static String backToMain(Map<String, Object> model) {
        TurnDao.getInstance().add();
        model.put("current_turn", findNextTurn());
        model.put("turns", TurnDao.getInstance().findAll());
        return render(model, "main.html");
    }

    private static Integer findNextTurn() {
        return TurnDao.getInstance().findNext();
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

    private static int getManualCount(final LottoService service, int manualCount, String number) {
        if (service.canBuy()) {
            Lotto lotto = parseLotto(number);
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
}
