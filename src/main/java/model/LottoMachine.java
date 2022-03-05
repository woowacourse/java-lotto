package model;

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

    public void makeTotalLottoCountForPurchase(final int purchaseMoney, final int manualLottoCount) {
        this.totalLottoCount = new TotalLottoCount(purchaseMoney, manualLottoCount);
    }

    public void purchaseLottos(final List<List<Integer>> manualLottoNumberGroups) {
        LottoGenerator lottoGenerator = LottoGenerator.getInstance();
        List<Lotto> manualLottoGroup = lottoGenerator.makeManualLotto(manualLottoNumberGroups);
        List<Lotto> autoLottoGroup = lottoGenerator.makeAutoLotto(totalLottoCount.getAutoCount());

        lottos = new Lottos(manualLottoGroup, autoLottoGroup);
    }

    public int bringManualLottoCountForPurchase() {
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
