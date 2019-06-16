package lotto;

import lotto.domain.*;
import lotto.dto.GameDTO;
import lotto.view.WebInputParser;
import lotto.view.WebOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        GameDTO gameDTO = new GameDTO();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/number", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Money money = WebInputParser.getMoney(req.queryParams("money"));
                gameDTO.setMoney(money);
                model.put("money", money);
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "number.html");
        });

        post("/manual", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                int numberOfManualLotto = WebInputParser.getNumberOfManualLotto(gameDTO.getMoney(), req.queryParams("number"));
                gameDTO.setNumberOfManualLotto(numberOfManualLotto);
                if (numberOfManualLotto == 0) {
                    LottoTickets lottoTickets = WebInputParser.getLottoTickets(gameDTO.getMoney(), 0, null);
                    gameDTO.setLottoTickets(lottoTickets);
                    model.put("lottoTickets", WebOutputView.printLottoTicketsAsBall(lottoTickets));

                    return render(model, "winning.html");
                }

                model.put("number", numberOfManualLotto);
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "manual.html");
        });

        post("/winning", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                LottoTickets lottoTickets = WebInputParser.getLottoTickets(gameDTO.getMoney(), gameDTO.getNumberOfManualLotto(), req.queryParams("manual"));
                gameDTO.setLottoTickets(lottoTickets);
                model.put("lottoTickets", WebOutputView.printLottoTicketsAsBall(lottoTickets));
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "winning.html");
        });

        post("/check", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                WinningLotto winningLotto = WebInputParser.getWinningLotto(req.queryParams("winning"), req.queryParams("bonus"));
                gameDTO.setWinningLotto(winningLotto);
                model.put("winningLotto", WebOutputView.printWinningLottoAsBall(winningLotto));
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "check.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                LottoResult lottoResult = gameDTO.getLottoTickets().getLottoResult(gameDTO.getWinningLotto());
                gameDTO.setLottoResult(lottoResult);
                model.put("lottoResult", WebOutputView.printLottoResult(lottoResult));
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
