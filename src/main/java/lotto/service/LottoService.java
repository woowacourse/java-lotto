package lotto.service;

import lotto.domain.*;
import lotto.domain.generator.ResultGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;

public class LottoService {

    public static Money generateMoney(String inputMoney) {
        return new Money(Integer.parseInt(inputMoney));
    }

    public static BoughtLottos generateBoughtLottos(Money money, String input) {
        List<String> inputManualLottos = processedInputManualLottos(input);
        return BoughtLottos.buyLottos(money, inputManualLottos);
    }

    private static List<String> processedInputManualLottos(String inputManualLottos) {
        if (inputManualLottos.equals("")) {
            return Collections.EMPTY_LIST;
        }
        return Arrays.stream(inputManualLottos.split("\n"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static Result generateResult(BoughtLottos boughtLottos, String winningNumbers, String bonus) {
        WinningNumber winningNumber = generateWinningNumber(winningNumbers, bonus);
        return ResultGenerator.generateResult(boughtLottos, winningNumber);
    }

    private static WinningNumber generateWinningNumber(String winningNumbers, String bonus) {
        List<LottoNumber> lottoNumbers = generateLottoNumbers(winningNumbers);
        return new WinningNumber(new Lotto(lottoNumbers), Integer.parseInt(bonus));
    }
}
