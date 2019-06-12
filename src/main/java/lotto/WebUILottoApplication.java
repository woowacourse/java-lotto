package lotto;

import lotto.domain.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates/html");
        externalStaticFileLocation("src/main/resources/templates");

        WebUILottoData webUILottoData = new WebUILottoData();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("webUILottoData", webUILottoData);
            return render(model, "/html/LottoMain.html");
        });

        get("/buyLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("webUILottoData", webUILottoData);
            return render(model, "/html/InputMoney.html");
        });

        get("/manualLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            webUILottoData.setMoney(MoneyParser.parseMoney(req.queryParams("money")));
            webUILottoData.setCountOfAllLotto(webUILottoData.getMoney().getAllLottoSize());
            webUILottoData.setCountOfManualLotto(Integer.parseInt(req.queryParams("countOfManualLotto")));
            webUILottoData.setCountOfAutoLotto(webUILottoData.getCountOfAllLotto() - webUILottoData.getCountOfManualLotto());
            webUILottoData.setAutoLottos(AutoLottoGenerator.generateAutoLottos(webUILottoData.getCountOfAutoLotto()));
            model.put("webUILottoData", webUILottoData);
            return render(model, "/html/InputManualLotto.html");
        });

        get("/showLottos", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("webUILottoData", webUILottoData);
            model.put("autoLottos", webUILottoData.getAutoLottos().getUserLottos());

            return render(model, "/html/ShowLottos.html");
        });

        get("/winningLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("webUILottoData", webUILottoData);
            return render(model, "/html/InputWinningLotto.html");
        });

        get("/resultOfRound", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("webUILottoData", webUILottoData);
            return render(model, "/html/ResultOfRound.html");
        });

        get("/search", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("webUILottoData", webUILottoData);
            return render(model, "/html/ResultOfAllRound.html");
        });


    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
