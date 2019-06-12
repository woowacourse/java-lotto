package controller;

import model.*;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.WebView;

import java.sql.SQLException;
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

        get("/history", (req, res) -> {
            try {
                return historyPage(req);
            } catch (IllegalArgumentException | SQLException e) {
                return WebView.error();
            }
        });

        get("*", (req, res) -> {
            res.status(404);
            res.redirect("404.html");
            return 404;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String indexPage() {
        Map<String, Object> model = new HashMap<String, Object>() {{
            put("price", Lotto.PRICE);
            put("priceFormatted", NumberFormat.getInstance().format(Lotto.PRICE));
            put("history", WebView.historySelect(PurchaseHistory.retrieveDatesFromLog()));
            put("recentRoundMenu", WebView.roundSelect());
        }};
        return render(model, "index.html");
    }

    private static String resultPage(Request req) {
        final int round = Integer.parseInt(req.queryParams("round").trim());
        final WinningNumbers winningNumbers = WinningNumbersFactory.of(round);
        final List<Lotto> manualLottos = parseManualLottos(req);
        final Lottos lottos = new Lottos(
                manualLottos,
                new LottoPurchaseAmount(new Money(req.queryParams("investment")), manualLottos.size())
        );
        final LottoResult result = lottos.getResult(winningNumbers);
        PurchaseHistory.writeLog(lottos, round, result);
        Map<String, Object> model = new HashMap<String, Object>() {{
            put("round", round);
            put("winningNumbers", WebView.formatWinningNumbers(winningNumbers));
            put("purchasedLottos", WebView.formatLottos(lottos));
            put("result", WebView.formatResult(result));
        }};
        return render(model, "result.html");
    }

    private static String historyPage(Request req) throws SQLException {
        final PurchaseHistory history = new PurchaseHistory(req.queryParams("date").trim());
        if (history.round() == 0) {
            throw new IllegalArgumentException();
        }
        final WinningNumbers winningNumbers = WinningNumbersFactory.of(history.round());
        final LottoResult result = history.lottos().getResult(winningNumbers);
        Map<String, Object> model = new HashMap<String, Object>() {{
            put("round", history.round());
            put("winningNumbers", WebView.formatWinningNumbers(winningNumbers));
            put("purchasedLottos", WebView.formatLottos(history.lottos()));
            put("result", WebView.formatResult(result));
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