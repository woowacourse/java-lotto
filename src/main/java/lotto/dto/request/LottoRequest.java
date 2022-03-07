package lotto.dto.request;

import java.util.List;

public class LottoRequest {

    private final int inputMoney;
    private final int manualAmount;
    private final List<List<Integer>> manualLottoNumbers;

    public LottoRequest(int inputMoney, int manualAmount, List<List<Integer>> manualLottoNumbers) {
        this.inputMoney = inputMoney;
        this.manualAmount = manualAmount;
        this.manualLottoNumbers = manualLottoNumbers;
    }

    public int getInputMoney() {
        return inputMoney;
    }

    public int getManualAmount() {
        return manualAmount;
    }

    public List<List<Integer>> getManualLottoNumbers() {
        return manualLottoNumbers;
    }
}
