package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.domain.ManualLottoCount;
import lotto.dto.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final String LOTTO_DELIMITER = ",";

    public List<Lotto> inputManualLottos(final int totalCount) {
        ManualLottoCount manualLottoCount = inputManualLottoCount(totalCount);
        InputView.printManualLottoGuideMesseage();
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualLottoCount.getCount(); i++) {
            manualLottos.add(inputManualLottoNumbers());
        }
        return manualLottos;
    }

    private ManualLottoCount inputManualLottoCount(int totalCount) {
        try {
            String count = InputView.inputManualLottoCount();
            return new ManualLottoCount(count, totalCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputManualLottoCount(totalCount);
        }
    }

    private Lotto inputManualLottoNumbers() {
        try {
            String numbers = removeBlank(InputView.inputManualLottoNumbers());
            return changeNumbersToLotto(numbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputManualLottoNumbers();
        }
    }

    public Lottos createLottos(final List<Lotto> manualLottos, final int totalCount) {
        Lottos lottos = new Lottos(manualLottos, totalCount);
        OutputView.printLottos(manualLottos.size(), lottos);
        return lottos;
    }

    public LottoWinningNumbers createLottoWinningNumbers() {
        try {
            final String numbers = inputLottoWinningNumbers();
            final Lotto lotto = changeNumbersToLotto(numbers);
            final LottoNumber bonusNumber = new LottoNumber(inputBonusNumber());
            return new LottoWinningNumbers(lotto, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLottoWinningNumbers();
        }
    }

    private String inputLottoWinningNumbers() {
        return removeBlank(InputView.inputLottoWinningNumbers());
    }

    private String inputBonusNumber() {
        return InputView.inputBonusNumber();
    }

    private Lotto changeNumbersToLotto(String numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers.split(LOTTO_DELIMITER))
                .map((s) -> removeBlank(s))
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    private String removeBlank(final String value) {
        return value.replace(" ", "");
    }

    public Result createResult(LottoWinningNumbers lottoWinningNumbers, Lottos lottos) {
        Result result = new Result(lottoWinningNumbers, lottos);
        OutputView.printWinningResult(result);
        return result;
    }
}
