package lotto;

import lotto.domain.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

            generateWebUILottoData(webUILottoData, req.queryParams("money"), req.queryParams("countOfManualLotto"));
            model.put("webUILottoData", webUILottoData);
            return render(model, "/html/InputManualLotto.html");
        });

        post("/showLottos", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            generateWebUILottoData(webUILottoData, req.queryParams("money"), req.queryParams("countOfManualLotto"));

            List<String> scannedManualLottos = new ArrayList<>();
            for (int i = 1; i <= webUILottoData.getCountOfManualLotto(); i++) {
                scannedManualLottos.add(req.queryParams("manual" + i));
            }
            webUILottoData.setManualLottos(ManualLottoParser.parseManualLottoNumbers(scannedManualLottos));

            model.put("webUILottoData", webUILottoData);
            model.put("manualLottos", webUILottoData.getManualLottos().getUserLottos());
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
            webUILottoData.setWinningLotto(WinningLottoParser.parseWinningLotto(req.queryParams("winningLotto"), req.queryParams("bonusBall")));
            webUILottoData.setLottoGame(new LottoGame(webUILottoData.getManualLottos(), webUILottoData.getAutoLottos(), webUILottoData.getWinningLotto()));

            model.put("winningLotto", webUILottoData.getWinningLotto());
            model.put("webUILottoData", webUILottoData);
            model.put("lottoGameResult", webUILottoData.getLottoGame().gameResult());
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

    private static void generateWebUILottoData(WebUILottoData webUILottoData, String money, String countOfManualLotto) {
        if (!webUILottoData.getIsGenerated()) {
            webUILottoData.setIsGenerated(true);
            webUILottoData.setMoney(MoneyParser.parseMoney(money));
            webUILottoData.setCountOfAllLotto(webUILottoData.getMoney().getAllLottoSize());
            webUILottoData.setCountOfManualLotto(Integer.parseInt(countOfManualLotto));
            webUILottoData.setCountOfAutoLotto(webUILottoData.getCountOfAllLotto() - webUILottoData.getCountOfManualLotto());
            webUILottoData.setAutoLottos(AutoLottoGenerator.generateAutoLottos(webUILottoData.getCountOfAutoLotto()));
        }
    }

}
