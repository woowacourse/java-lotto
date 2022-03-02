package lotto.view.input;

import lotto.dto.InputLottoDto;

import java.util.List;

public interface InputView {
    String inputPurchaseAmount();

    String inputManualPurchaseAmount();

    List<InputLottoDto> inputManualLottoNumbers(int manualPurchaseAmount);

    List<String> inputLastWeekWinningNumbers();

    String inputBonusNumber();

    void printErrorMessage(final String errorMessage);
}
