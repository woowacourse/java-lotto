package lotto.cotroller;

import lotto.WebUILottoApplication;
import lotto.domain.dao.RoundDAO;
import lotto.domain.lottoTicket.Lotto;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebManualLottoController implements WebController {
    public String page(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        List<Integer> lottoNumbers = WebUILottoApplication.convertLottoNumber(req);
        List<Lotto> manualLottos = req.session().attribute("manual");
        manualLottos.add(new Lotto(lottoNumbers));
        req.session().attribute("manual", manualLottos);
        model.put("searchCount", RoundDAO.searchMaxCount());
        return WebUILottoApplication.render(model, "lotto.html");
    }
}
