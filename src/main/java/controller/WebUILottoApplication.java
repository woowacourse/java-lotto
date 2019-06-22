package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.*;
import service.LottoService;
import spark.Request;
import view.WebView;

import java.sql.SQLException;
import java.util.stream.IntStream;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        port(4567);
        staticFiles.location("/static");

        get("/", (req, res) -> WebView.printIndex(
                LottoHistoryDAO.retrieveDatesFromHistory(),
                WinningNumbers.recentRound()
            )
        );

        post("/purchase", "application/json", (req, res) -> Error.orThrow(() -> purchase(req), res));

        post("/history", (req, res) -> Error.orThrow(() -> history(req), res));

        get("*", (req, res) -> {
            res.status(404);
            res.redirect("404.html");
            return 404;
        });
    }

    private static String purchase(Request req) {
        final JsonElement el = new JsonParser().parse(req.body());
        return WebView.printPurchase(
                LottoService.purchase(
                    el.getAsJsonObject().get("round").getAsInt(),
                    el.getAsJsonObject().get("investment").getAsInt(),
                    IntStream.range(0, req.queryParams().size() - 2)
                            .mapToObj(i -> req.queryParams("manualNumbers[" + i + "]"))
                            .filter(x -> x.trim().length() != 0)
            )
        );
    }

    private static String history(Request req) throws SQLException {
        final JsonElement el = new JsonParser().parse(req.body());
        return WebView.printHistory(
                LottoService.history(el.getAsJsonObject().get("date").getAsString())

        );
    }
}