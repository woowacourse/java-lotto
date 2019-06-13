package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
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

        get("/", (req, res) -> main());

        post("/purchase", "application/json", (req, res) -> orThrowError(() -> purchase(req), res));

        post("/history", (req, res) -> orThrowError(() -> history(req), res));

        get("*", (req, res) -> {
            res.status(404);
            res.redirect("404.html");
            return 404;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    @FunctionalInterface
    public interface SupplierWithExceptions<T, E extends Exception> {
        T get() throws E;
    }

    private static String orThrowError(SupplierWithExceptions<String, Exception> f, Response res) {
        try {
            return f.get();
        } catch (Exception e) {
            res.status(500);
            return WebView.error();
        }
    }

    private static String main() {
        Map<String, Object> model = new HashMap<String, Object>() {{
            put("price", Lotto.PRICE);
            put("priceFormatted", NumberFormat.getInstance().format(Lotto.PRICE));
            put("history", WebView.historySelect(PurchaseHistory.retrieveDatesFromLog()));
            put("recentRoundMenu", WebView.roundSelect());
        }};
        return render(model, "app.html");
    }

    private static String purchase(Request req) {
        JsonElement el = new JsonParser().parse(req.body());
        final int round = el.getAsJsonObject().get("round").getAsInt();
        final WinningNumbers winningNumbers = WinningNumbersFactory.of(round);
        final List<Lotto> manualLottos = parseManualLottos(req);
        final Lottos lottos = new Lottos(
                manualLottos,
                new LottoPurchaseAmount(
                        new Money(el.getAsJsonObject().get("investment").getAsInt()),
                        manualLottos.size()
                )
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

    private static String history(Request req) throws SQLException {
        JsonElement el = new JsonParser().parse(req.body());
        final PurchaseHistory history = new PurchaseHistory(el.getAsJsonObject().get("date").getAsString());
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