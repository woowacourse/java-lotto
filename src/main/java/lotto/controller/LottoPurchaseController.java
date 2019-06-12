package lotto.controller;

import lottogame.domain.InvalidLottoNumberException;
import lottogame.domain.LottoTickets;
import lottogame.domain.Money;

import java.util.*;

import static lotto.WebUILottoApplication.render;
import static spark.Spark.*;

public class LottoPurchaseController {
    public static void controller() {
        gameMain();
        getMoney();
        getNumberOfManualLotto();
        showPurchaseResult();
    }

    public static void gameMain() {
        path("/lottogame/money", () -> {
            get("", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return render(model, "money.html");
            });
            post("", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return render(model, "money.html");
            });
        });
    }

    private static void getMoney() {
        post("/lottogame/numberofmanual", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String price = req.queryParams("money");

            Money money = Money.generate(Integer.parseInt(price));

            req.session().attribute("money", money);
            model.put("numberofticket", money.getNumberOfTicket());
            return render(model, "numberofmanualinput.html");
        });
    }

    private static void getNumberOfManualLotto() {
        post("/lottogame/createmanuallotto", (req, res) -> {
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
        });
    }

    private static void showPurchaseResult() {
        path("/lottogame/showpurchaseresult", () -> {
            get("", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                LottoTickets lottoTickets = new LottoTickets();
                Money money = req.session().attribute("money");
                int numberOfManualTicket = 0;

                while (lottoTickets.isPossibleCreateLottoNumberOf(money.getNumberOfTicket())) {
                    lottoTickets.createAutoLottos();
                }
                model.put("lottoTickets",lottoTickets);
                model.put("numberOfManual",numberOfManualTicket);
                model.put("numberOfAuto", getNumberOfAutoTicket(lottoTickets,numberOfManualTicket));
                return render(model, "purchaseresult.html");
            });
            post("", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                LottoTickets lottoTickets = new LottoTickets();
                Money money = req.session().attribute("money");
                int numberOfManualTicket = req.session().attribute("numberOfManualTicket");

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
                model.put("lottoTickets",lottoTickets);
                model.put("numberOfManual",numberOfManualTicket);
                model.put("numberOfAuto", getNumberOfAutoTicket(lottoTickets,numberOfManualTicket));
                return render(model, "purchaseresult.html");
            });
        });
    }

    private static int getNumberOfAutoTicket(LottoTickets lottoTickets, int numberOfManualTicket) {
        return lottoTickets.numberOfLottos() - numberOfManualTicket;
    }

}
