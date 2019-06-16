package lotto;

import lotto.domain.LottoGame;
import lotto.domain.Money;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;

import java.util.List;

public class WebUILottoData {

    private boolean isGenerated;
    private int round;
    private int countOfAllLotto;
    private int countOfManualLotto;
    private int countOfAutoLotto;
    private Money money;
    private UserLottos autoLottos;
    private UserLottos manualLottos;
    private WinningLotto winningLotto;
    private LottoGame lottoGame;
    private List<List<String>> allResult;

    public WebUILottoData() {
    }

    public boolean getIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(boolean generated) {
        isGenerated = generated;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getCountOfAllLotto() {
        return countOfAllLotto;
    }

    public void setCountOfAllLotto(int countOfAllLotto) {
        this.countOfAllLotto = countOfAllLotto;
    }

    public int getCountOfManualLotto() {
        return countOfManualLotto;
    }

    public void setCountOfManualLotto(int countOfManualLotto) {
        this.countOfManualLotto = countOfManualLotto;
    }

    public int getCountOfAutoLotto() {
        return countOfAutoLotto;
    }

    public void setCountOfAutoLotto(int countOfAutoLotto) {
        this.countOfAutoLotto = countOfAutoLotto;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public UserLottos getAutoLottos() {
        return autoLottos;
    }

    public void setAutoLottos(UserLottos autoLottos) {
        this.autoLottos = autoLottos;
    }

    public UserLottos getManualLottos() {
        return manualLottos;
    }

    public void setManualLottos(UserLottos manualLottos) {
        this.manualLottos = manualLottos;
    }

    public WinningLotto getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public LottoGame getLottoGame() {
        return lottoGame;
    }

    public void setLottoGame(LottoGame lottoGame) {
        this.lottoGame = lottoGame;
    }

    public List<List<String>> getAllResult() {
        return allResult;
    }

    public void setAllResult(List<List<String>> allResult) {
        this.allResult = allResult;
    }
}
