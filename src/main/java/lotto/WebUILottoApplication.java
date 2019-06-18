package lotto;

import lotto.controller.RoundController;
import lotto.controller.LottoResultController;
import lotto.controller.LottoController;
import lotto.controller.WinningLottoController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static spark.Spark.*;

public class WebUILottoApplication {

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        staticFiles.location("/static");

        get("/", RoundController.makeRoundPage);

        get("/result", LottoResultController.makeLottoResultByRoundPage);

        post("/make/lotto", LottoController.makeSelfLottoPage);

        post("/lottos", LottoController.makeUserLottosPage);

        post("/make/winning", WinningLottoController.makeWinningLottoPage);

        post("/result", LottoResultController.makeLottoResultPage);

        exception(Exception.class, (exception, req, res) -> {
            String message = null;
            try {
                message = encodeUTF8(exception.getMessage());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            res.redirect("/?message=" + message);

        });

    }

    private static String encodeUTF8(final String message) throws UnsupportedEncodingException {
        return URLEncoder.encode(message, "UTF-8");
    }

}