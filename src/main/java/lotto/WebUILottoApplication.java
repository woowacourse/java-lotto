package lotto;

import lotto.domain.*;
import lotto.view.LottosDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final int START_COUNT = 0;

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        LottoService service = new LottoService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            // round
            return render(model, "form.html");
        });

        post("/money", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int money = Integer.parseInt(req.queryParams("money"));
            service.charge(money);
            return render(model, "ask.html");
        });

        post("/choose", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int manualCount = Integer.parseInt(req.queryParams("manualCount"));
            model.put("manualCount", manualCount);
            return render(model, "lottobuy.html");
        });

        post("/lottos", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            InputParser parser = new InputParser();
            Lotto lotto = parser.makeLotto(req.queryParams("lotto"));
            service.buy(lotto);

            int autoCount = assignAutoPurchaseCount(service);
            model.put("manualCount", req.queryParams("manualCount"));
            model.put("autoCount", autoCount);

            DtoConverter converter = new DtoConverter();
            LottosDto dtos = converter.convertLottosToDto(service.getLottos());
            model.put("lottoList", dtos.getLottos());

            return render(model, "lottolist.html");
        });

        post("/winning", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            InputParser parser = new InputParser();
            Lotto lotto = parser.makeLotto(req.queryParams("winninglotto"));
            LottoNumber lottoNumber = parser.makeLottoNumber(Integer.parseInt(req.queryParams("bonusnumber")));
            WinningLotto winningLotto = WinningLotto.of(lotto, lottoNumber);
            LottoGameResult gameResult = service.gameResult();
            gameResult.match(winningLotto);
            model.put("profit", String.format("%.1f", gameResult.profit(1000)));
            model.put("stat", showGameResult(gameResult));
            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static int assignAutoPurchaseCount(final LottoService service) {
        RandomNumbersGenerator generator = RandomNumbersGenerator.getInstance();
        LottoFactory lottoFactory = new LottoFactory();
        int autoPurchaseCount = START_COUNT;
        for (; service.canBuy(); autoPurchaseCount++) {
            Lotto lotto = lottoFactory.create(generator.generate());
            service.buy(lotto);
        }
        return autoPurchaseCount;
    }

    private static List<String> showGameResult(final LottoGameResult gameResult) {
        List<String> results = new ArrayList<>();

        for (Rank rank : Rank.reverseValues()) {
            results.add(showRank(rank) + gameResult.getRankCount(rank) + "개");
        }
        return results;
    }

    private static String showRank(final Rank rank) {
        StringBuilder sb = new StringBuilder();
        sb.append(rank.getMatchCount() + "개 일치");
        if (rank == Rank.SECOND) {
            sb.append(", 보너스 볼 일치");
        }
        sb.append("(" + rank.getMoney() + ")- ");
        return sb.toString();
    }
}
