package lotto;

import lotto.service.LottoResultService;
import lotto.service.LottoService;
import lotto.service.WinningLottoService;
import lotto.utils.ViewUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        staticFiles.location("/static");

        get("/", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("message", req.queryParams("message"));
                    return ViewUtils.render(model, "home.html");
                }
        );

        post("/make/lotto", LottoService.makeSelfLottoPage);

        post("/lottos", LottoService.makeUserLottosPage);

        post("/make/winning", WinningLottoService.makeWinningLottoPage);

        post("/result", LottoResultService.makeLottoResultPage);

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