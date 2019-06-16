package lotto.controller.web;

import lotto.domain.*;
import lotto.domain.vo.LottoResult_VO;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static lotto.WebUILottoApplication.render;

public class LottoStartController {

    public static String start(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "view/lotto_buy.html");
    }

    public static String buyingLotto(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        try {
            Price price = new Price(req.queryParams("price"));
            req.session().attribute("price", price);
            model.put("totalAmount", price.getNumberOfLotto());
            return render(model, "view/lotto_manual_amount.html");
        } catch (Exception e) {
            model.put("error_price", e.getMessage());
            return render(model, "view/lotto_buy.html");
        }
    }

    public static String amount(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        try {
            NumberOfCustomLotto amount = new NumberOfCustomLotto(req.queryParams("customAmount"), req.session().attribute("price"));
            req.session().attribute("customAmount", amount);
            model.put("customAmount", amount.getNumberOfCustomLotto());
            model.put("autoAmount", amount.getNumberOfAutoLotto());
            return render(model, "view/lotto_manual.html");
        } catch (Exception e) {
            model.put("error_amount", e.getMessage());
            return render(model, "view/lotto_manual_amount.html");
        }
    }

    public static String getLottoTicket(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        try {
            List<String> customNumbers = req.queryParams().stream()
                    .map(key -> req.queryParams(key))
                    .collect(toList());

            LottoTicket lottoTicket = new LottoTicket(req.session().attribute("customAmount"), customNumbers);
            req.session().attribute("lottoTicket", lottoTicket);
            model.put("lottoTicket", lottoTicket.getLottos());
            return render(model, "view/lotto_winning.html");
        } catch (Exception e) {
            model.put("error_custom", e.getMessage());
            return render(model, "view/lotto_manual_error.html");
        }
    }

    public static String getWinningLotto(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        try {
            WinningLotto winningLotto = new WinningLotto(req.queryParams("winningNumbers"), req.queryParams("bonusNumber"));
            LottoResult lottoResult = new LottoResult(req.session().attribute("lottoTicket"), winningLotto);
            LottoResult_VO lottoResult_vo = new LottoResult_VO(lottoResult.matchLotto(), req.session().attribute("price"));

            model.put("winningLotto", winningLotto);
            model.put("bonusBall", winningLotto.getBonusBall());
            model.put("result", lottoResult_vo.getRank());
            model.put("resultPrize", lottoResult_vo.getPrize());
            model.put("resultIncomeRate", lottoResult_vo.dividendRate());
            return render(model, "view/lotto_result.html");
        } catch (Exception e) {
            model.put("error_custom", e.getMessage());
            return render(model, "view/lotto_winning.html");
        }
    }

}
