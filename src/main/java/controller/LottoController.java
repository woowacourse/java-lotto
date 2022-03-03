package controller;

import domain.Count;
import domain.Lotto;
import domain.LottoNumber;
import domain.Money;
import domain.Result;
import domain.WinLotto;
import dto.LottoDto;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = new LottoService();
    }

    public void start() {
        final Money totalMoney = getMoney();
        final List<Lotto> issuedLotto = issueLotto(totalMoney);

        final Result result = getResult(issuedLotto);

        printResult(totalMoney, result);
    }

    private Result getResult(final List<Lotto> issuedLotto) {
        final WinLotto winLotto = generateWinLotto(getWinLottoNumbers());
        return new Result(issuedLotto, winLotto);
    }

    private WinLotto generateWinLotto(final Lotto lastWinLotto) {
        try {
            final LottoNumber bonusNumber = getBonusNumber();
            return new WinLotto(lastWinLotto, bonusNumber);
        } catch (Exception e) {
            outputView.printInputError(e);
            return generateWinLotto(lastWinLotto);
        }
    }

    private Money getMoney() {
        try {
            return new Money(inputView.getMoney());
        } catch (Exception e) {
            outputView.printInputError(e);
            return getMoney();
        }
    }

    private List<Lotto> issueLotto(final Money totalMoney) {
        final Count manualCount = getManualCount();
        final List<Lotto> issuedManualLotto = getManualLottoWith(manualCount);
        final List<Lotto> issuedLotto = lottoService.issueLotto(totalMoney, issuedManualLotto);
        outputView.printLotto(getIssuedLottoDto(issuedLotto), manualCount.getCount());
        return issuedLotto;
    }

    private List<LottoDto> getIssuedLottoDto(final List<Lotto> issuedLotto) {
        return issuedLotto.stream()
            .map(LottoDto::from)
            .collect(Collectors.toList());
    }

    private Count getManualCount() {
        try {
            return new Count(inputView.getManualCount());
        } catch (Exception e) {
            outputView.printInputError(e);
            return getManualCount();
        }
    }

    private List<Lotto> getManualLottoWith(final Count manualCount) {
        if (manualCount.isEnd()) {
            return Collections.emptyList();
        }
        try {
            final List<List<String>> manualLottoInput = inputView.getManualLotto(manualCount.getCount());
            return lottoService.issueManualLottoGroup(manualLottoInput);
        } catch (Exception e) {
            outputView.printInputError(e);
            return getManualLottoWith(getManualCount());
        }
    }

    private Lotto getWinLottoNumbers() {
        try {
            final List<String> winLotto = inputView.getWinLotto();
            return Lotto.fromInput(winLotto);
        } catch (Exception e) {
            outputView.printInputError(e);
            return getWinLottoNumbers();
        }
    }

    private LottoNumber getBonusNumber() {
        try {
            final int bonusNumber = inputView.getBonusNumber();
            return new LottoNumber(bonusNumber);
        } catch (Exception e) {
            outputView.printInputError(e);
            return getBonusNumber();
        }
    }

    private void printResult(final Money money, final Result result) {
        outputView.printWinStatistics(result.getStatistics());
        outputView.printWinProfit(result.getProfitOrNotMessage(money));
    }
}
