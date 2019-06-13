package lotto.data;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

public class GameDTO {
     private Lotto winningNumbers;
     private Number bonusNumber;
     private String result;
     private String returnAmount;
     private String returnRate;

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
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(String returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(String returnRate) {
        this.returnRate = returnRate;
    }
}
