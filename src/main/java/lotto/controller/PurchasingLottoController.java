package lotto.controller;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.factory.LottoTicketsFactory;
import lotto.service.LottoGameService;
import spark.Route;

import java.util.*;

import static lotto.utils.WebUIRenderer.render;

public class PurchasingLottoController {
    private static final int MONEY_OFFSET = 1000;
    private static final String LOTTO_DELIMITER = "-";

    public static Route purchasingTickets = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        return render(model, "purchasing_lotto.html");
    };

    public static Route purchasedTickets = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        Money money = new Money(Integer.parseInt(req.queryParams("money")));
        List<String> inputCustoms = getInputCustoms(req.queryParams("lottos"));
        LottoTickets lottoTickets = LottoTicketsFactory.getInstance().create(money, inputCustoms);

        model.put("money", money.getMoney());
        model.put("lottos", lottoTickets.getLottoTickets());
        model.put("amountOfCustom", inputCustoms.size());
        model.put("amountOfAuto", (money.getMoney() / MONEY_OFFSET) - inputCustoms.size());

        LottoGameService.addLottoTicket(lottoTickets);

        return render(model, "purchased_tickets.html");
    };

    private static List<String> getInputCustoms(String lottos) {
        List<String> inputCustoms = Arrays.asList(lottos.split(LOTTO_DELIMITER));

        if (lottos.isEmpty()) {
            inputCustoms = new ArrayList<>();
        }
        return inputCustoms;
    }
}
