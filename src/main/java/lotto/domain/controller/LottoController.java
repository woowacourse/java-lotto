package lotto.domain.controller;

import lotto.domain.LottoMatchKind;
import lotto.domain.LottoNumbers;
import lotto.domain.TargetLottoNumbers;
import lotto.domain.generator.Generator;
import lotto.domain.vo.LottoNumber;
import lotto.dto.LottoMatchKindDto;
import lotto.dto.LottoNumbersDto;
import lotto.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final Generator generator, final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        lottoService = initializeLottoService(generator);
    }

    public void run() {
        outputView.printPurchaseCount(lottoService.getCountOfLottoNumbers());
        final List<LottoNumbersDto> lottoNumbersGroup =
                convertLottoNumbersGroupToDtos(lottoService.getLottoNumbersGroup());
        outputView.printLottoNumbersGroup(lottoNumbersGroup);
        final TargetLottoNumbers targetLottoNumbers = generateTargetLottoNumbers();
        final List<LottoMatchKindDto> results =
                convertWinningResultsToDtos(lottoService.getMatchResult(targetLottoNumbers));
        outputView.printCountOfWinningByMatchKind(results);
        outputView.printProfitRate(lottoService.getProfitRate());
    }

    private LottoService initializeLottoService(final Generator generator) {
        try {
            final String purchaseAmountInput = inputView.inputPurchaseAmount();
            return new LottoService(generator, purchaseAmountInput);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return initializeLottoService(generator);
        }
    }

    private List<LottoNumbersDto> convertLottoNumbersGroupToDtos(
            final List<LottoNumbers> lottoNumbersGroup) {
        return lottoNumbersGroup.stream()
                .map(LottoNumbers::getValues)
                .map(LottoNumbersDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private TargetLottoNumbers generateTargetLottoNumbers() {
        try {
            final LottoNumbers targetLottoNumbers = new LottoNumbers(inputView.inputLastWeekWinningNumbers());
            final LottoNumber bonusLottoNumber = LottoNumber.from(inputView.inputBonusNumber());
            return new TargetLottoNumbers(targetLottoNumbers, bonusLottoNumber);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return generateTargetLottoNumbers();
        }
    }

    private List<LottoMatchKindDto> convertWinningResultsToDtos(final Map<LottoMatchKind, Integer> results) {
        return results.keySet().stream()
                .map(lottoMatchKind -> new LottoMatchKindDto(lottoMatchKind, results.get(lottoMatchKind)))
                .collect(Collectors.toUnmodifiableList());
    }
}
