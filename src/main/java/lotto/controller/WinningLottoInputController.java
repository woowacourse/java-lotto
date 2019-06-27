package lotto.controller;

import lotto.StandardResponse;
import lotto.StatusResponse;
import lotto.service.WinningLottoService;
import spark.Request;
import spark.Response;
import spark.Route;

public class WinningLottoInputController {
    private static WinningLottoService service;
    private static int INTERNAL_SERVER_ERROR_CODE = 500;

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
            response.status(INTERNAL_SERVER_ERROR_CODE);
            return new StandardResponse(StatusResponse.ERROR, e.getMessage()).toJson();
        }
        return new StandardResponse(StatusResponse.SUCCESS).toJson();
    };
}