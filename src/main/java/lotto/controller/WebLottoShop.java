package lotto.controller;

import lotto.domain.dao.LottoGameDao;
import lotto.domain.dao.LottoTicketDao;
import lotto.domain.dao.WinningLottoDao;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.WinningLotto;
import lotto.domain.result.Rank;
import lotto.domain.result.WinningResult;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTickets;
import lotto.view.inputview.InputParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebLottoShop {
    private static final String NEXT_LINE_DELIMITER = "\\r?\\n";

    private UserTickets userTickets;

    public Map<String, Object> getUserTicketState(String purchasePrice, String manualAmount, String manualLotto) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(InputParser.getPurchasePrice(purchasePrice));

        LottoGameDao lottoGameDao = LottoGameDao.getInstance();
        lottoGameDao.addLottoAmount(purchaseAmount);

        userTickets = new UserTickets(getManualLotto(manualLotto), purchaseAmount);

        LottoTicketDao lottoTicketDao = LottoTicketDao.getInstance();
        for (LottoTicket lottoTicket : userTickets.getUserLottoTickets()) {
            lottoTicketDao.addLottoTicket(lottoTicket);
        }

        return getUserTicketModel(manualAmount, purchaseAmount);
    }

    private static List<String> getManualLotto(String manualLotto) {
        return Arrays.asList(manualLotto.split(NEXT_LINE_DELIMITER));
    }

    private Map<String, Object> getUserTicketModel(String manualAmount, PurchaseAmount purchaseAmount) {
        Map<String, Object> model = new HashMap<>();
        model.put("manualAmount", manualAmount);
        model.put("autoAmount", purchaseAmount.getAutoAmount(Integer.parseInt(manualAmount)));
        model.put("userTickets", userTickets.getUserLottoTickets());

        return model;
    }

    public Map<String, Object> getWinningResultState(String lotto, String bonusNum) {
        WinningLotto winningLotto = new WinningLotto(InputParser.getLottoNum(lotto)
                , Integer.parseInt(bonusNum));
        WinningLottoDao winningLottoDao = WinningLottoDao.getInstance();
        winningLottoDao.addWinningLotto(winningLotto);

        WinningResult winningResult = new WinningResult(userTickets, winningLotto);

        return  getResultModel(winningResult);
    }

    private Map<String, Object> getResultModel(WinningResult winningResult) {
        Map<String, Object> model = new HashMap<>();
        model.put("FIFTH", winningResult.getMatchedRankCountValue(Rank.FIFTH));
        model.put("FOURTH", winningResult.getMatchedRankCountValue(Rank.FOURTH));
        model.put("THIRD", winningResult.getMatchedRankCountValue(Rank.THIRD));
        model.put("SECOND", winningResult.getMatchedRankCountValue(Rank.SECOND));
        model.put("FIRST", winningResult.getMatchedRankCountValue(Rank.FIRST));

        model.put("totalYield", winningResult.getTotalYield());
        return model;
    }
}