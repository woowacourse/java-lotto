package lotto;

import lotto.dao.*;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.view.WebInputParser;
import lotto.view.WebOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplicationWithDB {
    public static void main(String[] args) {
        staticFiles.location("/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/number", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Money money = WebInputParser.getMoney(req.queryParams("money"));
                req.session().attribute("money", money);
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
                int numberOfManualLotto = WebInputParser.getNumberOfManualLotto(req.session().attribute("money"),
                        req.queryParams("number"));
                req.session().attribute("numberOfManualLotto", numberOfManualLotto);
                if (numberOfManualLotto == 0) {
                    LottoTickets lottoTickets = WebInputParser.getLottoTickets(req.session().attribute("money"), 0, null);
                    req.session().attribute("lottoTickets", lottoTickets);
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
                LottoTickets lottoTickets = WebInputParser.getLottoTickets(req.session().attribute("money"),
                        req.session().attribute("numberOfManualLotto"), req.queryParams("manual"));
                req.session().attribute("lottoTickets", lottoTickets);
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
                req.session().attribute("winningLotto", winningLotto);
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
                LottoResult lottoResult = ((LottoTickets) req.session().attribute("lottoTickets"))
                        .getLottoResult(req.session().attribute("winningLotto"));
                req.session().attribute("lottoResult", lottoResult);
                model.put("result", lottoResult);

                GameDAO.addAll(req.session());
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "result_db.html");
        });

        get("/all", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                int round = Integer.parseInt(req.queryParams("round"));
                model.put("select", WebOutputView.printResultSelectBox(round, RoundDAO.getInstance().getRound() - 1));
                model.put("winningLotto", WebOutputView.printWinningLottoAsBall(WinningLottoDAO.getInstance().findByRound(round)));
                model.put("lottos", WebOutputView.printLottosAsBall(LottoDAO.getInstance().findByRound(round)));
                model.put("result", LottoResultDAO.getInstance().findByRound(round));
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "all.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
