package lotto.data;

import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.game.Count;
import lotto.domain.game.ManualCount;
import lotto.domain.game.TotalLottoGames;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

public class WebUILottoData {
    private PurchaseAmount purchaseAmount;
    private Count totalCount;
    private ManualCount manualCount;
    private TotalLottoGames totalLottoGames;
    private Lotto winningNumbers;
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

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(Lotto winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public Number getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(Number bonusNumber) {
        this.bonusNumber = bonusNumber;
        this.setWinningLotto(new WinningLotto(this.winningNumbers, this.bonusNumber));
    }

    public WinningLotto getWinningLotto() {
        return winningLotto;
    }

    private void setWinningLotto(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }
}
