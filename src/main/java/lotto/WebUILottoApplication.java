package lotto;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static WebUILottoData webUILottoData;

    public static void main(String[] args) {
        get("/", (req, res) ->
                BuyLottoAPI.index()
        );

        get("/buy", (req, res) -> {
            webUILottoData = new WebUILottoData();
            return BuyLottoAPI.buy();
        });

        get("/purchase", (req, res) ->
                BuyLottoAPI.purchase(req, webUILottoData)
        );

        get("/manual", (req, res) ->
                BuyLottoAPI.manual(req, webUILottoData)
        );

        post("/numbers", (req, res) ->
                BuyLottoAPI.numbers(req, webUILottoData)
        );

        get("/winning", (req, res) ->
                BuyLottoAPI.winning(req, webUILottoData)
        );

        get("/result", (req, res) ->
                BuyLottoAPI.result(req, webUILottoData)
        );

        get("/enroll", (req, res) ->
                EnrollLottoAPI.enroll(webUILottoData)
        );

        get("/lookup", (req, res) ->
                InquireLottoAPI.lookUp()
        );

        get("/look", (req, res) ->
                InquireLottoAPI.look(req)
        );

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(404);
            res.body("<h1>에러 발생</h1>" +
                    "<form action=\"/\">\n" +
                    "  <input type=\"submit\" value=\"홈으로\"/>\n" +
                    "</form>");
        });
    }
}
