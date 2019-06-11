package controller;

import model.*;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.WebView;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        port(4567);
        staticFiles.location("/static");

        get("/", (req, res) -> indexPage());

        get("/lotto", (req, res) -> {
            try {
                return resultPage(req);
            } catch (IllegalArgumentException e) {
                return WebView.error();
            }
        });

        get("*", (req, res) -> {
            res.redirect("404.html");
            return -1;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String indexPage() {
        Map<String, Object> model = new HashMap<String, Object>() {{
            put("price", Lotto.PRICE);
            put("priceFormatted", NumberFormat.getInstance().format(Lotto.PRICE));
            put("recentRoundMenu", WebView.roundSelect());
        }};
        return render(model, "index.html");
    }

    private static String resultPage(Request req) {
        WinningNumbers winningNumbers = WinningNumbersFactory.of(req.queryParams("round"));
        final List<Lotto> manualLottos = parseManualLottos(req);
        final Lottos lottos = new Lottos(
                manualLottos,
                new LottoPurchaseAmount(new Money(req.queryParams("investment")), manualLottos.size())
        );
        Map<String, Object> model = new HashMap<String, Object>() {{
            put("winningNumbers", WebView.formatWinningNumbers(winningNumbers));
            put("purchasedLottos", WebView.formatLottos(lottos));
            put("result", WebView.formatResult(lottos.getResult(winningNumbers)));
        }};
        return render(model, "result.html");
    }

    private static List<Lotto> parseManualLottos(Request req) {
        return IntStream.range(0, req.queryParams().size() - 2).boxed()
                .map(i -> req.queryParams("manualNumbers[" + i + "]"))
                .filter(x -> x.trim().length() != 0)
                .map(x -> new Lotto(x))
                .collect(Collectors.toList());
    }
}