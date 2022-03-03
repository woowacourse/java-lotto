package model;

import java.util.List;

import model.lottonumber.Lotto;
import model.lottonumber.Lottos;
import model.lottonumber.WinningNumbers;
import model.totalmoney.TotalPurchaseMoney;
import model.winningresult.WinningResult;

public class LottoMachine {

    private Lottos lottos;
    private TotalPurchaseMoney totalPurchaseMoney;

    public void insertTotalPurchaseMoney(final int totalPurchaseMoney, final int manualLottoCount) {
        this.totalPurchaseMoney = new TotalPurchaseMoney(totalPurchaseMoney, manualLottoCount);
    }

    public void purchaseLottos(final List<Lotto> manualLottos) {
        int autoLottoCount = totalPurchaseMoney.getAutoLottoCount();

        lottos = new Lottos(manualLottos, autoLottoCount);
    }

    public int sendManualLottoCount() {
        return totalPurchaseMoney.getManualLottoCount();
    }

    public int sendAutoLottoCount() {
        return totalPurchaseMoney.getAutoLottoCount();
    }

    public int sendTotalPurchaseLottoCount() {
        return totalPurchaseMoney.getTotalPurchaseLottoCount();
    }

    public List<Lotto> sendLottosInMachine() {
        return lottos.getLottos();
    }

    public WinningResult makeLottoWinningResult(final List<Integer> winningNumbers, final int bonusNumber) {
        return lottos.makeWinningResult(new WinningNumbers(winningNumbers, bonusNumber));
    }
}
