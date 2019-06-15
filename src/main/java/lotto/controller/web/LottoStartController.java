package lotto.controller.web;

import lotto.controller.web.dto.LottoStartControllerDTO;
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
    private static final String RESULT_MESSAGE = "%d개 일치 (%d원) - %d개\n";
    private static final String RESULT_SECOND_MESSAGE = "%d개 일치, 보너스볼 일치 (%d원) - %d개\n";

    private static LottoStartControllerDTO lottoStartDTO = new LottoStartControllerDTO();

    public static String start(Request req, Response res){
        Map<String, Object> model = new HashMap<>();
        return render(model, "view/lotto_buy.html");
    }

    public static String buyingLotto(Request req, Response res){
        Map<String, Object> model = new HashMap<>();
        try{
            Price price = new Price(req.queryParams("price"));
            lottoStartDTO.setPrice(price);

            model.put("amount", lottoStartDTO.getPrice().getNumberOfLotto());
            return render(model, "view/lotto_manual_amount.html");
        } catch (Exception e){
            model.put("error_price", e.getMessage());
            return render(model, "view/lotto_buy.html");
        }
    }

    public static String amount(Request req, Response res){
        Map<String, Object> model = new HashMap<>();
        try{
            NumberOfCustomLotto amount = new NumberOfCustomLotto(req.queryParams("customAmount"), lottoStartDTO.getPrice());
            lottoStartDTO.setCustomAmount(amount);

            model.put("customAmount", lottoStartDTO.getCustomAmount().getNumberOfCustomLotto());
            model.put("autoAmount", lottoStartDTO.getCustomAmount().getNumberOfAutoLotto());
            return render(model, "view/lotto_manual.html");
        } catch (Exception e){
            model.put("error_amount", e.getMessage());
            return render(model, "view/lotto_manual_amount.html");
        }
    }

    public static String getLottoTicket(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        try {
            List<String> customNumbers = new ArrayList<>(req.queryParams()
                    .stream()
                    .map(key -> req.queryParams(key))
                    .collect(toList()));

            LottoTicket lottoTicket = new LottoTicket(lottoStartDTO.getCustomAmount() ,customNumbers);
            lottoStartDTO.setLottoTicket(lottoTicket);
            model.put("lottoTicket", lottoStartDTO.getLottoTicket().getLottos());

            return render(model, "view/lotto_winning.html");
        } catch (Exception e){
            model.put("error_custom", e.getMessage());
            return render(model, "view/lotto_manual_error.html");
        }
    }

    public static String getWinningLotto(Request req, Response res){
        Map<String, Object> model = new HashMap<>();
        try {
            WinningLotto winningLotto = new WinningLotto(req.queryParams("winningNumbers"), req.queryParams("bonusNumber"));
            lottoStartDTO.setWinningLotto(winningLotto);

            model.put("winningLotto", lottoStartDTO.getWinningLotto());
            model.put("bonusBall", lottoStartDTO.getWinningLotto().getBonusBall());

            LottoResult lottoResult = new LottoResult(lottoStartDTO.getLottoTicket(), lottoStartDTO.getWinningLotto());
            LottoResult_VO lottoResult_vo = new LottoResult_VO(lottoResult.matchLotto(), lottoStartDTO.getPrice().getMoney());

            lottoStartDTO.setLottoResult(lottoResult_vo);

            model.put("result", resultForm(lottoResult_vo));
            model.put("resultIncomeRate", lottoStartDTO.getLottoResult().dividendRate());

            return render(model, "view/lotto_result.html");
        } catch (Exception e){
            model.put("error_custom", e.getMessage());
            return render(model, "view/lotto_winning.html");
        }
    }

    private static List<String> resultForm(LottoResult_VO lottoResult){
        return lottoResult.getResultKey()
                .stream()
                .map(rank -> printStatistics(rank, lottoResult))
                .collect(toList());
    }

    private static String printStatistics(Rank rank, LottoResult_VO lottoResult) {
        if (rank == Rank.MISS) {
            return "";
        }
        return String.format(rank != Rank.SECOND ? RESULT_MESSAGE : RESULT_SECOND_MESSAGE
                , rank.getCountOfMatch(), rank.getWinningMoney()
                , lottoResult.getResultValue(rank));
    }
}
