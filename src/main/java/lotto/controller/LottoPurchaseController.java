package lotto.controller;

import lotto.service.LottoTicketsService;
import lottogame.domain.InvalidLottoNumberException;
import lottogame.domain.LottoTickets;
import lottogame.domain.Money;
import spark.Request;
import spark.Response;

import java.util.*;

import static lotto.WebUILottoApplication.render;

public class LottoPurchaseController {
    public static Object showMoneyInputPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "money.html");
    }

    public static Object showNumberOfManualInputPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        String price = req.queryParams("money");

        Money money = Money.generate(Integer.parseInt(price));

        req.session().attribute("money", money);
        model.put("numberofticket", money.getNumberOfTicket());
        return render(model, "numberofmanualinput.html");
    }

    public static Object showManualLottoInputPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        Money money = req.session().attribute("money");
        int numberOfManual = getNumberOfManulTicket(money, req.queryParams("numberofmanual"));

        req.session().attribute("numberOfManualTicket", numberOfManual);
        model.put("numberOfManualTicket", numberOfManual);
        if (numberOfManual == 0) {
            res.redirect("/lottogame/purchaseresult");
        }
        return render(model, "manuallottoinput.html");
    }

    private static int getNumberOfManulTicket(Money money, String numberOfManual) {
        money.checkInValidNumber(Integer.parseInt(numberOfManual));
        return Integer.parseInt(numberOfManual);
    }

    public static Object showPurchaseResultPost(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        LottoTickets lottoTickets = new LottoTickets();
        int numberOfManualTicket = req.session().attribute("numberOfManualTicket");
        int round = req.session().attribute("round");

        checkValidCount(req, numberOfManualTicket);
        addManualLotto(req, lottoTickets);
        createAutoLotto(lottoTickets, req.session().attribute("money"));
        LottoTicketsService.insertPurchaseResult(round, lottoTickets);
        return renderPurchaseResult(model, lottoTickets, numberOfManualTicket);
    }

    private static void checkValidCount(Request req, int numberOfManualTicket) {
        if (req.queryParams().size() != numberOfManualTicket) {
            throw new InvalidLottoNumberException("개수가 일치하지 않습니다.");
        }
    }

    private static void addManualLotto(Request req, LottoTickets lottoTickets) {
        for (String lotto : req.queryParams()) {
            lottoTickets.addManualLotto(req.queryParams(lotto));
        }
    }

    public static Object showPurchaseResultGet(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        LottoTickets lottoTickets = new LottoTickets();
        Money money = req.session().attribute("money");
        int round = req.session().attribute("round");
        int numberOfManualTicket = 0;

        createAutoLotto(lottoTickets, money);
        LottoTicketsService.insertPurchaseResult(round, lottoTickets);
        return renderPurchaseResult(model, lottoTickets, numberOfManualTicket);
    }

    private static void createAutoLotto(LottoTickets lottoTickets, Money money) {
        while (lottoTickets.isPossibleCreateLottoNumberOf(money.getNumberOfTicket())) {
            lottoTickets.createAutoLottos();
        }
    }

    private static Object renderPurchaseResult(Map<String, Object> model, LottoTickets lottoTickets, int numberOfManualTicket) {
        model.put("lottoTickets", lottoTickets.getLottos());
        model.put("numberOfManual", numberOfManualTicket);
        model.put("numberOfAuto", getNumberOfAutoTicket(lottoTickets, numberOfManualTicket));
        return render(model, "purchaseresult.html");
    }

    private static int getNumberOfAutoTicket(LottoTickets lottoTickets, int numberOfManualTicket) {
        return lottoTickets.numberOfLottos() - numberOfManualTicket;
    }

}
