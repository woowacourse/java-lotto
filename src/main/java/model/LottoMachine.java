package model;

import dto.LottoDto;
import java.util.List;

import model.generator.LottoGenerator;
import model.lottonumber.Lotto;
import model.lottonumber.Lottos;
import model.lottonumber.WinningNumbers;
import model.LottoCount.TotalLottoCount;
import model.winningresult.WinningResult;

public class LottoMachine {

    private TotalLottoCount totalLottoCount;
    private Lottos lottos;

    public void makeTotalLottoCountToPurchase(final int purchaseMoney, final int manualLottoCount) {
        this.totalLottoCount = new TotalLottoCount(purchaseMoney, manualLottoCount);
    }

    public void purchaseLottos(final List<LottoDto> manualLottoNumberGroups) {
        int autoLottoCount = totalLottoCount.getAutoCount();

        lottos = new Lottos(manualLottoNumberGroups, autoLottoCount);
    }

    public int sendManualLottoCount() {
        return totalLottoCount.getManualCount();
    }

    public int sendAutoLottoCount() {
        return totalLottoCount.getAutoCount();
    }

    public int sendTotalPurchaseLottoCount() {
        return totalLottoCount.getTotalCount();
    }

    public List<Lotto> sendLottosInMachine() {
        return lottos.getLottos();
    }

    public WinningResult makeLottoWinningResult(final List<Integer> winningNumbers, final int bonusNumber) {
        return lottos.makeWinningResult(new WinningNumbers(winningNumbers, bonusNumber));
    }
}
