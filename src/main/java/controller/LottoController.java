package controller;

import domain.Count;
import domain.Lotto;
import domain.LottoNumber;
import domain.Money;
import domain.Result;
import dto.LottoDto;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private static final String ERROR_MESSAGE = "[ERROR] ";

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

        final Lotto winLotto = getWinLotto();
        final LottoNumber bonusNumber = getBonusNumber();
        final Result result = lottoService.calculateResult(issuedLotto, winLotto, bonusNumber);

        printResult(totalMoney, result);
    }

    private Money getMoney() {
        try {
            return new Money(inputView.getMoney());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
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
            System.out.println(ERROR_MESSAGE + e.getMessage());
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
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getManualLottoWith(getManualCount());
        }
    }

    private Lotto getWinLotto() {
        try {
            final List<String> winLotto = inputView.getWinLotto();
            return Lotto.fromInput(winLotto);
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getWinLotto();
        }
    }

    private LottoNumber getBonusNumber() {
        try {
            final int bonusNumber = inputView.getBonusNumber();
            return new LottoNumber(bonusNumber);
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getBonusNumber();
        }
    }

    private void printResult(final Money money, final Result result) {
        outputView.printWinStatistics(result.getStatistics());
        outputView.printWinProfit(result.getProfitOrNotMessage(money));
    }
}
