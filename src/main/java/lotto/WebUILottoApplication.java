package lotto;

import lotto.dao.GameResultDao;
import lotto.dao.LottosDao;
import lotto.dao.TurnDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.util.InputParser;
import lotto.util.LottoDtoConverter;
import lotto.util.RandomNumbersGenerator;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final int START_COUNT = 0;

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        LottoService service = new LottoService();
        WinningLottoDao winningDao = new WinningLottoDao();
        GameResultDao resultDao = new GameResultDao();
        LottosDao lottosDao = new LottosDao();
        TurnDao turnDao = new TurnDao();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("current_turn", turnDao.findNext());
            model.put("turns", turnDao.findAll());
            return render(model, "main.html");
        });

        get("/turn", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int turn = Integer.parseInt(req.queryParams("current_turn"));
            GameResultDto result = resultDao.findByTurn(turn);
            model.put("turn", turn);
            model.put("lottos", lottosDao.findAllByTurn(turn));
            model.put("winning_lotto", winningDao.findByTurn(turn));
            model.put("result", stringifyResult(result));
            model.put("profit", result.getProfit());
            return render(model, "turn_info.html");
        });

        post("/money", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int money = Integer.parseInt(req.queryParams("money"));
            service.charge(money);
            return render(model, "ask.html");
        });

        post("/buy", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int manualCount = Integer.parseInt(req.queryParams("manualCount"));
            model.put("manualCount", manualCount);
            return render(model, "lotto_shopping.html");
        });

        post("/lottos", (req, res) -> {
            //TODO 리팩토링 필수
            Map<String, Object> model = new HashMap<>();
            InputParser parser = new InputParser();

            String origin = req.queryParams("numbers");
            int manualCount = 0;
            List<String> numbers = Arrays.asList(origin.split("\\n"));

            for (String number : numbers) {
                if (service.canBuy()) {
                    manualCount++;
                    Lotto lotto = parser.parseLotto(number);
                    service.buy(lotto);
                }
            }

            int autoCount = assignAutoPurchaseCount(service);
            model.put("manualCount", manualCount);
            model.put("autoCount", autoCount);

            List<Lotto> lottos = service.getLottos();
            model.put("lottos", new LottoDtoConverter().convertLottosToDto(lottos));

            return render(model, "lottos.html");
        });

        post("/winning", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            InputParser parser = new InputParser();

            Lotto lotto = parser.parseLotto(req.queryParams("winninglotto"));
            LottoNumber lottoNumber = parser.parseLottoNumber(Integer.parseInt(req.queryParams("bonusnumber")));
            WinningLotto winningLotto = WinningLotto.of(lotto, lottoNumber);
            winningDao.add(winningLotto, turnDao.findNext());

            LottoGameResult gameResult = service.gameResult();
            gameResult.match(winningLotto);
            model.put("profit", String.format("%.1f", gameResult.profit(LottoMachine.LOTTO_MONEY)));
            model.put("stat", stringifyResult(new LottoDtoConverter().convertResultToDto(gameResult)));

            resultDao.add(new LottoDtoConverter().convertResultToDto(gameResult), turnDao.findNext());

            return render(model, "result.html");
        });

        post("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if (req.queryParams("token").equals("restart")) {
                turnDao.add();
                model.put("current_turn", turnDao.findNext());
                model.put("turns", turnDao.findAll());
                return render(model, "main.html");
            }
            resultDao.deleteAll();
            winningDao.deleteAll();
            lottosDao.deleteAll();
            turnDao.deleteAll();
            return render(model, "main.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static int assignAutoPurchaseCount(final LottoService service) {
        RandomNumbersGenerator generator = RandomNumbersGenerator.getInstance();
        LottoFactory lottoFactory = new LottoFactory();
        int autoPurchaseCount = START_COUNT;
        int temp;
        //TODO 공통된 부분 뽑아내기
        for (; service.canBuy(); autoPurchaseCount++) {
            Lotto lotto = lottoFactory.create(generator.generate());
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
