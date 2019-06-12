package lotto;

import lotto.domain.Factory.LottoTicketsFactory;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.view.InputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        path("/purchase", () -> {
            get("", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return render(model, "purchasing_lotto.html");
            });

            post("/ticket", (req, res) -> {
                Map<String, Object> model = new HashMap<>();

                Money money = new Money(Integer.parseInt(req.queryParams("money")));
                model.put("money", money.getMoney());

                List<String> inputCustoms = Arrays.asList(req.queryParams("lottos").split("-"));
                LottoTickets lottoTickets = LottoTicketsFactory.getInstance().create(money, inputCustoms);

                List<String> customs = new ArrayList<>();
                for (String custom : customs) {

                }
                model.put("customs", lottoTickets.getLottoTickets());

                model.put("amountOfCustom", inputCustoms.size());
                model.put("amountOfAuto", (money.getMoney() / 1000) - inputCustoms.size());

                return render(model, "purchased_tickets.html");
            });
        });

        path("/statistics", () -> {
            get("/win", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return render(model, "purchased_tickets.html");
            });

            get("/ticket", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return render(model, "lotto_tickets.html");
            });
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
