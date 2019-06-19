package lotto.cotroller;

import lotto.WebUILottoApplication;
import lotto.domain.Money;
import lotto.domain.dao.LottoDAO;
import lotto.domain.dao.ResultDAO;
import lotto.domain.dao.RoundDAO;
import lotto.domain.dao.WinningDAO;
import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.rank.RankResult;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebWinningLottoController implements WebController{
    public String page(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        List<Integer> winningLottoNumber = WebUILottoApplication.convertLottoNumber(req);
        int bonus = WebUILottoApplication.convertNumber(req, "bonus");
        WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonus);
        Money money = req.session().attribute("money");
        Lottos lottos = getLottos(req, money);
        RankResult rank = lottos.matchLottoRank(winningLotto);

        RoundDAO.registerCount();
        LottoDAO.addTotalLottos(lottos);
        WinningDAO.addWinningLotto(winningLottoNumber, bonus);
        ResultDAO.addResult(rank, money);
        model.put("searchCount", RoundDAO.searchMaxCount());
        return WebUILottoApplication.render(model, "lotto.html");
    }

    private static Lottos getLottos(Request req, Money money) {
        List<Lotto> manualLottos = req.session().attribute("manual");
        return new Lottos(manualLottos,
                money.getAutoLottoNumber(manualLottos.size()));
    }
}
