package lotto.domain;

import java.util.List;

public class UserLottoDto {
    private final String inputLottoMoney;
    private final List<String> manualLottoNumber;

    public UserLottoDto(String inputLottoMoney, List<String> manualLottoNumber) {
        this.inputLottoMoney = inputLottoMoney;
        this.manualLottoNumber = manualLottoNumber;
    }

    public String getInputLottoMoney() {
        return inputLottoMoney;
    }

    public List<String> getManualLottoNumber() {
        return manualLottoNumber;
    }
}
