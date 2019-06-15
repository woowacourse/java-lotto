package lotto;

import lotto.dao.RoundDao;
import lotto.db.DatabaseConnection;
import lotto.controller.LottoResultController;
import lotto.controller.LottoController;
import lotto.controller.WinningLottoController;
import lotto.utils.ViewUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        staticFiles.location("/static");

        get("/", (req, res) -> {
                    Connection conn = new DatabaseConnection().getConnection();
                    RoundDao roundDao = new RoundDao(conn);
                    Map<String, Object> model = new HashMap<>();
                    int presentRound = roundDao.findLatestRound() + 1;
                    List<Integer> rounds = roundDao.findAllRound();
                    model.put("present", presentRound);
                    model.put("rounds", rounds);
                    model.put("message", req.queryParams("message"));
                    req.session().attribute("round", presentRound);
                    return ViewUtils.render(model, "home.html");
                }
        );

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