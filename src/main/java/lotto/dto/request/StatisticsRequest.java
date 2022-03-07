package lotto.dto.request;

import java.util.List;

public class StatisticsRequest {

    private final List<List<Integer>> lottos;
    private final List<Integer> winnerNumbers;
    private final int bonusNumber;
    private final int inputMoney;

    public StatisticsRequest(List<List<Integer>> lottos, List<Integer> winnerNumbers, int bonusNumber, int inputMoney) {
        this.lottos = lottos;
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
        this.inputMoney = inputMoney;
    }

    public List<List<Integer>> getLottos() {
        return lottos;
    }

    public List<Integer> getWinnerNumbers() {
        return winnerNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public int getInputMoney() {
        return inputMoney;
    }
}
