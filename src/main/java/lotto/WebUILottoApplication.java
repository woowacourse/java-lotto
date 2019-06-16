package lotto;

import lotto.dao.LottoDAO;
import lotto.dao.RoundDAO;
import lotto.dao.WinningLottoDAO;
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
        LottoDAO lottoDAO = new LottoDAO();
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO();
        RoundDAO roundDAO = new RoundDAO();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            roundDAO.addRound();
            int round = roundDAO.findlast();
            webUILottoData.setRound(round);
            webUILottoData.setIsGenerated(false);
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
            UserLottos autoLottos = AutoLottoGenerator.generateAutoLottos(webUILottoData.getCountOfAutoLotto());
            webUILottoData.setAutoLottos(autoLottos);

            List<String> scannedManualLottos = new ArrayList<>();
            for (int i = 1; i <= webUILottoData.getCountOfManualLotto(); i++) {
                scannedManualLottos.add(req.queryParams("manual" + i));
            }
            UserLottos manualLottos = ManualLottoParser.parseManualLottoNumbers(scannedManualLottos);
            webUILottoData.setManualLottos(manualLottos);

            List<Lotto> allUserLottos = new ArrayList<>(autoLottos.getUserLottos());
            allUserLottos.addAll(manualLottos.getUserLottos());
            lottoDAO.addLottos(new UserLottos(allUserLottos), webUILottoData.getRound());

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
            webUILottoData.setLottoGame(new LottoGame(lottoDAO.findAllByRound(webUILottoData.getRound()), webUILottoData.getWinningLotto()));
            winningLottoDAO.addWinningLotto(webUILottoData.getWinningLotto(), webUILottoData.getRound());

            model.put("winningLotto", webUILottoData.getWinningLotto());
            model.put("webUILottoData", webUILottoData);
            model.put("lottoGameResult", webUILottoData.getLottoGame().gameResult());
            return render(model, "/html/ResultOfRound.html");
        });

        get("/search", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int round = Integer.parseInt(req.queryParams("round"));
            UserLottos userLottos = lottoDAO.findAllByRound(round);
            WinningLotto winningLotto = winningLottoDAO.findAllByRound(round);
            model.put("userLottos", userLottos.getUserLottos());
            model.put("winningLotto", winningLotto);
            model.put("result", new LottoGame(userLottos, winningLotto).gameResult());

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
        }
    }

}
