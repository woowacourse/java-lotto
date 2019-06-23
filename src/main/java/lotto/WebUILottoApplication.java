package lotto;

import lotto.dao.LottoDao;
import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.dto.LottoGameDto;
import lotto.dto.LottosDto;
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

        LottosDto lottosDto = new LottosDto();
        LottoGameDto lottoGameDto = new LottoGameDto();

        LottoDao lottoDAO = LottoDao.getInstance();
        WinningLottoDao winningLottoDAO = WinningLottoDao.getInstance();
        RoundDao roundDAO = RoundDao.getInstance();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            roundDAO.addRound();
            int round = roundDAO.findlast();
            lottoGameDto.setRound(round);
            lottoGameDto.setIsGenerated(false);

            model.put("lottoGame", lottoGameDto);
            return render(model, "/html/LottoMain.html");
        });

        get("/buyLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoGame", lottoGameDto);
            return render(model, "/html/InputMoney.html");
        });

        get("/manualLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            generateWebUILottoData(lottoGameDto, lottosDto, req.queryParams("money"), req.queryParams("countOfManualLotto"));

            model.put("lottoGame", lottoGameDto);
            model.put("lottos", lottosDto);
            return render(model, "/html/InputManualLotto.html");
        });

        post("/showLottos", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            generateWebUILottoData(lottoGameDto, lottosDto, req.queryParams("money"), req.queryParams("countOfManualLotto"));
            UserLottos autoLottos = AutoLottoGenerator.generateAutoLottos(lottosDto.getCountOfAutoLotto());
            lottosDto.setAutoLottos(autoLottos);

            List<String> scannedManualLottos = new ArrayList<>();
            for (int i = 1; i <= lottosDto.getCountOfManualLotto(); i++) {
                scannedManualLottos.add(req.queryParams("manual" + i));
            }
            UserLottos manualLottos = ManualLottoParser.parseManualLottoNumbers(scannedManualLottos);
            lottosDto.setManualLottos(manualLottos);

            List<Lotto> allUserLottos = new ArrayList<>(autoLottos.getUserLottos());
            allUserLottos.addAll(manualLottos.getUserLottos());
            lottoDAO.addLottos(new UserLottos(allUserLottos), lottoGameDto.getRound());

            model.put("lottoGame", lottoGameDto);
            model.put("lottos", lottosDto);
            model.put("manualLottos", lottosDto.getManualLottos().getUserLottos());
            model.put("autoLottos", lottosDto.getAutoLottos().getUserLottos());
            return render(model, "/html/ShowLottos.html");
        });

        get("/winningLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("lottoGame", lottoGameDto);
            model.put("lottos", lottosDto);
            return render(model, "/html/InputWinningLotto.html");
        });

        get("/resultOfRound", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            lottosDto.setWinningLotto(WinningLottoParser.parseWinningLotto(req.queryParams("winningLotto"), req.queryParams("bonusBall")));
            lottoGameDto.setLottoGame(
                    new LottoGame(lottoDAO.findAllByRound(lottoGameDto.getRound()), lottosDto.getWinningLotto()));

            winningLottoDAO.addWinningLotto(lottosDto.getWinningLotto(), lottoGameDto.getRound());

            model.put("lottoGame", lottoGameDto);
            model.put("lottos", lottosDto);
            model.put("winningLotto", lottosDto.getWinningLotto());
            model.put("lottoGameResult", lottoGameDto.getLottoGame().gameResult());
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

            model.put("lottoGame", lottoGameDto);
            model.put("lottos", lottosDto);
            return render(model, "/html/ResultOfAllRound.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void generateWebUILottoData(LottoGameDto lottoGameDto, LottosDto lottosDto, String money, String countOfManualLotto) {
        if (!lottoGameDto.getIsGenerated()) {
            lottoGameDto.setIsGenerated(true);
            lottoGameDto.setMoney(MoneyParser.parseMoney(money));
            lottosDto.setCountOfAllLotto(lottoGameDto.getMoney().getAllLottoSize());
            lottosDto.setCountOfManualLotto(Integer.parseInt(countOfManualLotto));
            lottosDto.setCountOfAutoLotto(lottosDto.getCountOfAllLotto() - lottosDto.getCountOfManualLotto());
        }
    }

}
