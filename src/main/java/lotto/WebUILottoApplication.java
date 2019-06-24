package lotto;

import lotto.domain.dao.LottoGameDao;
import lotto.domain.dao.LottoTicketDao;
import lotto.domain.dao.WinningLottoDao;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.WinningLotto;
import lotto.domain.result.Rank;
import lotto.domain.result.WinningResult;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTickets;
import lotto.view.inputview.InputParser;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final String NEXT_LINE_DELIMITER = "\\r?\\n";
    private static UserTickets userTickets;

    public static void main(String[] args) {
        port(8080);

        staticFiles.location("/static");

        get("/winningLotto", (req, res) -> {
            PurchaseAmount purchaseAmount = new PurchaseAmount(InputParser.getPurchasePrice(req.queryParams("purchasePrice")));
            int manualAmount = Integer.parseInt(req.queryParams("manualAmount"));
            LottoGameDao lottoGameDao = LottoGameDao.getInstance();
            lottoGameDao.addLottoAmount(purchaseAmount);

            userTickets = new UserTickets(getManualLotto(req), purchaseAmount);
            LottoTicketDao lottoTicketDao = LottoTicketDao.getInstance();
            for (LottoTicket lottoTicket : userTickets.getUserLottoTickets()) {
                lottoTicketDao.addLottoTicket(lottoTicket);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("manualAmount", manualAmount);
            model.put("autoAmount", purchaseAmount.getAutoAmount(manualAmount));
            model.put("userTickets", userTickets.getUserLottoTickets());
            return render(model, "winningLotto.html");
        });

        get("/winningResult", (req, res) -> {
            WinningLotto winningLotto = new WinningLotto(InputParser.getLottoNum(req.queryParams("winningLotto"))
                    , Integer.parseInt(req.queryParams("bonusNum")));
            WinningLottoDao winningLottoDao = WinningLottoDao.getInstance();
            winningLottoDao.addWinningLotto(winningLotto);

            WinningResult winningResult = new WinningResult(userTickets, winningLotto);

            Map<String, Object> model = new HashMap<>();
            model.put("FIFTH", winningResult.getMatchedRankCountValue(Rank.FIFTH));
            model.put("FOURTH", winningResult.getMatchedRankCountValue(Rank.FOURTH));
            model.put("THIRD", winningResult.getMatchedRankCountValue(Rank.THIRD));
            model.put("SECOND", winningResult.getMatchedRankCountValue(Rank.SECOND));
            model.put("FIRST", winningResult.getMatchedRankCountValue(Rank.FIRST));

            model.put("totalYield", winningResult.getTotalYield());

            return render(model, "winningResult.html");
        });
    }

    private static List<String> getManualLotto(Request req) {
        return Arrays.asList(req.queryParams("manualLotto")
                .split(NEXT_LINE_DELIMITER));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
