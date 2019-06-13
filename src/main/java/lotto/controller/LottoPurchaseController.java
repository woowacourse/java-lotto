package lotto.controller;

import lotto.service.LottoTicketsService;
import lottogame.domain.InvalidLottoNumberException;
import lottogame.domain.LottoTickets;
import lottogame.domain.Money;
import spark.Request;
import spark.Response;

import java.util.*;

import static lotto.WebUILottoApplication.render;
import static spark.Spark.*;

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
        String numberOfManualInput = req.queryParams("numberofmanual");
        int numberOfManual;

        Money money = req.session().attribute("money");
        money.checkInValidNumber(Integer.parseInt(numberOfManualInput));
        numberOfManual = Integer.parseInt(numberOfManualInput);

        req.session().attribute("numberOfManualTicket", numberOfManual);
        model.put("numberOfManualTicket", numberOfManual);
        if (numberOfManual == 0) {
            res.redirect("/lottogame/showpurchaseresult");
        }
        return render(model, "manuallottoinput.html");
    }

    public static Object showPurchaseResultPost(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        LottoTickets lottoTickets = new LottoTickets();
        Money money = req.session().attribute("money");
        int numberOfManualTicket = req.session().attribute("numberOfManualTicket");
        int round = req.session().attribute("round");
        if (req.queryParams().size() != numberOfManualTicket) {
            throw new InvalidLottoNumberException("개수가 일치하지 않습니다.");
        }
        for (String lotto : req.queryParams()) {
            System.out.println(lotto);
            lottoTickets.addManualLotto(req.queryParams(lotto));
        }
        while (lottoTickets.isPossibleCreateLottoNumberOf(money.getNumberOfTicket())) {
            lottoTickets.createAutoLottos();
        }
        LottoTicketsService.insertPurchaseResult(round, lottoTickets);
        return renderResult(model, lottoTickets, numberOfManualTicket);
    }

    public static Object showPurchaseResultGet(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        LottoTickets lottoTickets = new LottoTickets();
        Money money = req.session().attribute("money");
        int round = req.session().attribute("round");
        int numberOfManualTicket = 0;

        while (lottoTickets.isPossibleCreateLottoNumberOf(money.getNumberOfTicket())) {
            lottoTickets.createAutoLottos();
        }
        LottoTicketsService.insertPurchaseResult(round, lottoTickets);
        return renderResult(model, lottoTickets, numberOfManualTicket);
    }

    private static Object renderResult(Map<String, Object> model, LottoTickets lottoTickets, int numberOfManualTicket) {
        model.put("lottoTickets", lottoTickets);
        model.put("numberOfManual", numberOfManualTicket);
        model.put("numberOfAuto", getNumberOfAutoTicket(lottoTickets, numberOfManualTicket));
        return render(model, "purchaseresult.html");
    }

    private static int getNumberOfAutoTicket(LottoTickets lottoTickets, int numberOfManualTicket) {
        return lottoTickets.numberOfLottos() - numberOfManualTicket;
    }

}
