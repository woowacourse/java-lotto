package lotto.dto;

import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;

public class LottosDto {

    private int countOfAllLotto;
    private int countOfManualLotto;
    private int countOfAutoLotto;
    private UserLottos autoLottos;
    private UserLottos manualLottos;
    private WinningLotto winningLotto;

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
}
