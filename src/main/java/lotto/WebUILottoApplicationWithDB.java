package lotto;

import lotto.dao.*;
import lotto.database.DBConnector;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.dto.GameDTO;
import lotto.view.WebInputParser;
import lotto.view.WebOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplicationWithDB {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        GameDTO gameDto = new GameDTO();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/number", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Money money = WebInputParser.getMoney(req.queryParams("money"));
                gameDto.setMoney(money);
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
                int numberOfManualLotto = WebInputParser.getNumberOfManualLotto(gameDto.getMoney(), req.queryParams("number"));
                gameDto.setNumberOfManualLotto(numberOfManualLotto);
                if (numberOfManualLotto == 0) {
                    LottoTickets lottoTickets = WebInputParser.getLottoTickets(gameDto.getMoney(), 0, null);
                    gameDto.setLottoTickets(lottoTickets);
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
                LottoTickets lottoTickets = WebInputParser.getLottoTickets(gameDto.getMoney(), gameDto.getNumberOfManualLotto(), req.queryParams("manual"));
                gameDto.setLottoTickets(lottoTickets);
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
                gameDto.setWinningLotto(winningLotto);
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
                LottoResult lottoResult = gameDto.getLottoTickets().getLottoResult(gameDto.getWinningLotto());
                gameDto.setLottoResult(lottoResult);
                model.put("result", lottoResult);

                Connection conn = DBConnector.getConnection();
                GameDAO gameDao = new GameDAO(conn);
                gameDto.setRound(new RoundDAO(conn).getRound());
                gameDao.addAll(gameDto);
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
                Connection conn = DBConnector.getConnection();
                model.put("select", WebOutputView.printResultSelectBox(round, new RoundDAO(conn).getRound() - 1));
                model.put("winningLotto", WebOutputView.printWinningLottoAsBall(new WinningLottoDAO(conn).findByRound(round)));
                model.put("lottos", WebOutputView.printLottosAsBall(new LottoDAO(conn).findByRound(round)));
                model.put("result", new LottoResultDAO(conn).findByRound(round));
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
