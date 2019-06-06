package lotto;

import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.game.Count;
import lotto.domain.game.ManualCount;
import lotto.domain.game.TotalLottoGames;
import lotto.domain.lotto.Lotto;

public class WebUILottoData {
    private PurchaseAmount purchaseAmount;
    private Count totalCount;
    private ManualCount manualCount;
    private TotalLottoGames totalLottoGames;
    private Lotto winningNumber;
    private Number bonusNumber;
    private WinningLotto winningLotto;

    public PurchaseAmount getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(PurchaseAmount purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Count getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Count totalCount) {
        this.totalCount = totalCount;
    }

    public ManualCount getManualCount() {
        return manualCount;
    }

    public void setManualCount(ManualCount manualCount) {
        this.manualCount = manualCount;
    }

    public TotalLottoGames getTotalLottoGames() {
        return totalLottoGames;
    }

    public void setTotalLottoGames(TotalLottoGames totalLottoGames) {
        this.totalLottoGames = totalLottoGames;
    }

    public Lotto getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(Lotto winningNumber) {
        this.winningNumber = winningNumber;
    }

    public Number getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(Number bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public WinningLotto getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }
}
