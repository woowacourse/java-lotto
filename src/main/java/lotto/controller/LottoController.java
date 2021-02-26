package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.PurchaseInfo;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumberFactory;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.WinningLottoTicket;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void start() {
        PurchaseInfo purchaseInfo = createPurchaseInfo();
        List<LottoTicket> manualLottoTickets = createManualLottoTickets(
            purchaseInfo.getPurchaseManualCount());
        LottoTickets lottoTickets = buyLottoTicket(purchaseInfo, manualLottoTickets);
        WinningLottoTicket winningLottoTicket = createWinningLottoTicket();
        LottoResult lottoResult = lottoTickets.calculateLottoResult(winningLottoTicket);

        outputView.printLottoResult(lottoResult, purchaseInfo.getPurchaseMoney());
    }

    private PurchaseInfo createPurchaseInfo() {
        Money lottoPurchaseMoney = new Money(inputView.inputForLottoMoney());
        int manualLottoCount = inputView.inputForManualLottoCount();

        return new PurchaseInfo(lottoPurchaseMoney, manualLottoCount);
    }

    private List<LottoTicket> createManualLottoTickets(int purchaseManualCount) {
        inputView.printForInputManualLottoNumbersMessage();
        return IntStream.range(0, purchaseManualCount)
            .mapToObj(num -> inputView.inputForLottoNumbers())
            .map(LottoTicket::new)
            .collect(Collectors.toList());
    }

    private LottoTickets buyLottoTicket(PurchaseInfo purchaseInfo,
        List<LottoTicket> manualLottoTickets) {
        LottoTickets lottoTickets = lottoService.buyTicket(purchaseInfo, manualLottoTickets);
        outputView.printAllLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private WinningLottoTicket createWinningLottoTicket() {
        LottoTicket winningTicket = new LottoTicket(inputView.inputForWinningNumbers());
        LottoNumber bonusNumber = LottoNumberFactory.getInstance(inputView.inputForBonusNumber());
        return new WinningLottoTicket(winningTicket, bonusNumber);
    }
}
