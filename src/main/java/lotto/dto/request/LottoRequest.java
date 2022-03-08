package lotto.dto.request;

import java.util.List;

public class LottoRequest {

    private final int inputMoney;
    private final List<List<Integer>> manualLottoNumbers;

    public LottoRequest(int inputMoney, List<List<Integer>> manualLottoNumbers) {
        this.inputMoney = inputMoney;
        this.manualLottoNumbers = manualLottoNumbers;
    }

    public int getInputMoney() {
        return inputMoney;
    }

    public List<List<Integer>> getManualLottoNumbers() {
        return manualLottoNumbers;
    }
}
