package lotto.dto;

public class WinningLottoDTO {
    private String winningLotto;
    private int bonusNumber;

    public WinningLottoDTO() {

    }

    public WinningLottoDTO(String winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public String getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(String winningLotto) {
        this.winningLotto = winningLotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
}
