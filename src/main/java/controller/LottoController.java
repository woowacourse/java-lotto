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
        final List<Lotto> issuedManualLotto = buyManualLotto(totalMoney);

        final Result result = issueLotto(totalMoney, issuedManualLotto);

        announceResult(totalMoney, result);
    }

    private Money getMoney() {
        try {
            return new Money(inputView.getMoney());
        } catch (Exception e) {
            outputView.printInputError(e);
            return getMoney();
        }
    }

    private List<Lotto> buyManualLotto(final Money totalMoney) {
        final Count manualCount = getManualCount(totalMoney);
        return getManualLottoWith(manualCount);
    }

    private List<LottoDto> toLottoDto(final List<Lotto> issuedLotto) {
        return issuedLotto.stream()
            .map(LottoDto::from)
            .collect(Collectors.toList());
    }

    private Count getManualCount(final Money totalMoney) {
        try {
            final int manualCount = inputView.getManualCount();
            return Count.createWithTotal(manualCount, totalMoney.getLottoCount());
        } catch (Exception e) {
            outputView.printInputError(e);
            return getManualCount(totalMoney);
        }
    }

    private List<Lotto> getManualLottoWith(final Count manualCount) {
        if (manualCount.isEnd()) {
            return Collections.emptyList();
        }
        try {
            return inputView.getManualLotto(manualCount.getCount());
        } catch (Exception e) {
            outputView.printInputError(e);
            return getManualLottoWith(manualCount);
        }
    }

    private Result issueLotto(final Money totalMoney, final List<Lotto> issuedManualLotto) {
        final List<Lotto> issuedLotto = lottoService.issueLotto(
            totalMoney,
            issuedManualLotto
        );
        outputView.printLotto(toLottoDto(issuedLotto), issuedManualLotto.size());

        final Lotto lastWinLotto = getWinLottoNumbers();
        final LottoNumber bonusNumber = getBonusNumber();
        final WinLotto winLotto = new WinLotto(lastWinLotto, bonusNumber);

        return lottoService.getResult(issuedLotto, winLotto);
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

    private void announceResult(final Money totalMoney, final Result result) {
        outputView.printWinStatistics(result);
        outputView.printWinProfit(result.getProfitOrNotMessage(totalMoney));
    }
}
