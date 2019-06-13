package lotto.controller;

import com.google.gson.Gson;
import lotto.StandardResponse;
import lotto.StatusResponse;
import lotto.domain.lotto.LottoNo;
import lotto.utils.LottoNoParser;
import spark.Request;
import spark.Response;
import spark.Route;

public class WinningLottoInputController {
    private static LottoService service;
    public static Route inputWinningLotto = (Request request, Response response) -> {
        response.type("application/json");
        try {
            service.makeWinningLotto(LottoNoParser.parseToLottoNos(request.queryMap("winningLottoNo").value()),
                    new LottoNo(request.queryMap("bonusNo").integerValue()));
            service.registerWinningLotto();
            service.registerResult();
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    };

    public static Route serveWinningLottoInputPage = (Request request, Response response) -> {
        response.redirect("/winninglotto.html");
        return null;
    };

    static {
        service = new LottoService();
    }
}