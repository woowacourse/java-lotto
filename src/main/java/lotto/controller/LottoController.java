package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.lotto.util.LottoMoney;
import lotto.domain.lotto.util.PurchaseCount;
import lotto.domain.rank.Ranks;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        LottoTicket lottoTicket = createLottoTicket();
        OutputView.printLottoTicket(lottoTicket);

        WinningLotto winningLotto = creatWinningLotto();
        Ranks lottoWinningResult = createLottoWinningResult(lottoTicket, winningLotto);
        OutputView.printLottoResult(lottoWinningResult);
    }

    private LottoTicket createLottoTicket() {
        try {
            LottoMoney lottoMoney = createLottoMoney();
            PurchaseCount manualPurchaseCount = createPurchaseCount();

            return new LottoTicket(lottoMoney.spendLottoLine(manualPurchaseCount),
                createLottoLines(manualPurchaseCount));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createLottoTicket();
        }
    }

    private List<LottoLine> createLottoLines(PurchaseCount purchaseCount) {
        List<LottoLine> lottoLines = new ArrayList<>();
        for (int i = 0; i < purchaseCount.getValue(); i++) {
            lottoLines.add(createLottoLine());
        }
        return lottoLines;
    }

    private LottoLine createLottoLine() {
        try {
            List<Integer> lottoNumbersUserInput = InputView.getLottoNumbersUserInput();
            List<LottoNumber> lottoNumbers = lottoNumbersUserInput.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
            return new LottoLine(lottoNumbers, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createLottoLine();
        }
    }

    private LottoNumber createBonusLottoNumber() {
        try {
            return new LottoNumber(InputView.getBonusLottoNumberUserInput());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createBonusLottoNumber();
        }
    }

    private Ranks createLottoWinningResult(LottoTicket lottoTicket,
        WinningLotto winningLotto) {
        return new Ranks(lottoTicket.checkLottoLines(winningLotto));
    }

    private LottoMoney createLottoMoney() {
        return new LottoMoney(InputView.getMoneyUserInput());
    }

    private PurchaseCount createPurchaseCount() {
        PurchaseCount purchaseCount =
            new PurchaseCount(InputView.getManualPurchaseCountUserInput());
        if (purchaseCount.getValue() > 0) {
            InputView.printManualLottoLineNumbersInputRequestMessage();
        }
        return purchaseCount;
    }

    private WinningLotto creatWinningLotto() {
        try {
            InputView.printWinningLottoLineInputRequestMessage();
            return new WinningLotto(createLottoLine(), createBonusLottoNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return creatWinningLotto();
        }
    }

}
