package lotto;

import lotto.domain.dao.LottoDao;
import lotto.domain.dto.ResultDTO;
import lotto.domain.lotto.Result;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {

        // TODO: 2019-06-12 Transaction? -> auto commit X -> last logic
        // TODO: 2019-06-12 apply DTO? (or VO?) -> setter or builder pattern
        // TODO: 2019-06-13 Modify URI path!!
        staticFileLocation("/");
        port(80);

        // connect -> index page
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<ResultDTO> lottoGames = LottoDao.getInstance().selectAllLottoGame();

            lottoGames.forEach(result -> {
                result.setTotalWinningMoney(new Result(result.getLottoScore()).calculateTotalWinningMoney());
                result.setEarningRate(result.getTotalWinningMoney() / result.getPayment());
            });

            model.put("lottoGames", lottoGames);
            return render(model, "index.html");
        });

        // index -> payment page
        post("/inputPayment", (req, res) -> {
            String userName = req.queryParams("user_name");

            // TODO: 2019-06-13 How to produce insert result?
            LottoDao.getInstance().insertUser(userName);


            Map<String, Object> sessionMap = new HashMap<>();
            sessionMap.put("name", userName);
            req.session().attribute("user", sessionMap); // session 에 등록

            return render(null, "../static/lottoPaymentInfo.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
