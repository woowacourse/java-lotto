package lotto.cotroller;

import lotto.WebUILottoApplication;
import lotto.domain.Money;
import lotto.domain.dao.RoundDAO;
import lotto.domain.lottoTicket.Lotto;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebMoneyController implements WebController{
    public String page(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        Money money = new Money(WebUILottoApplication.convertNumber(req, "money"));
        req.session().attribute("money", money);
        req.session().attribute("manual", new ArrayList<Lotto>());
        model.put("searchCount", RoundDAO.searchMaxCount());
        return WebUILottoApplication.render(model, "lotto.html");
    }
}
