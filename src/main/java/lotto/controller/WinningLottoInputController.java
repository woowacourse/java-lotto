package lotto.controller;

import com.google.gson.Gson;
import lotto.StandardResponse;
import lotto.StatusResponse;
import lotto.service.WinningLottoService;
import spark.Request;
import spark.Response;
import spark.Route;

public class WinningLottoInputController {
    private static WinningLottoService service;

    static {
        service = new WinningLottoService();
    }

    public static Route serveWinningLottoInputPage = (Request request, Response response) -> {
        response.redirect("/winninglotto.html");
        return null;
    };

    public static Route inputWinningLotto = (Request request, Response response) -> {
        response.type("application/json");
        try {
            service.makeWinningLotto(request.queryParams("winningLottoNo"),
                    Integer.parseInt(request.queryParams("bonusNo")));
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    };
}