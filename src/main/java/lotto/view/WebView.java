package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.Ticket;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebView {

    public static Map<String, Object> userLottoJson(UserLottos userLottos) {
        List<List<Integer>> numbers = new ArrayList<>();
        for (Ticket ticket : userLottos.tickets()) {
            numbers.add(ticket.ticketNumbers());
        }
        Map<String, Object> json = new HashMap<>();
        json.put("userLotto", numbers);
        return json;
    }


    public static Map<String, Object> winningLottoJson(WinningLotto winningLotto) {
        Map<String, Object> json = new HashMap<>();
        json.put("numbers", winningLotto.ticketNumbers());
        json.put("bonus", winningLotto.bonusNumbers());
        return json;
    }

    public static Map<String, Object> lottoResultJson(LottoResult lottoResult) {
        Map<String, Object> json = new HashMap<>();
        json.put("result", lottoResult.results());
        json.put("summary", lottoResult.calculate().summary());
        return json;
    }

}
