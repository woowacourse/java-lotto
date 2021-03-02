package lotto.controller;

import java.util.ArrayList;
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
        LottoTickets lottoTickets = buyLottoTicket(purchaseInfo);
        WinningLottoTicket winningLottoTicket = createWinningLottoTicket();
        LottoResult lottoResult = lottoTickets.calculateLottoResult(winningLottoTicket);
        outputView.printLottoResult(lottoResult, purchaseInfo.getPurchaseMoney());
    }

    private PurchaseInfo createPurchaseInfo() {
        Money lottoPurchaseMoney = new Money(inputView.inputForLottoMoney());
        int manualLottoCount = inputView.inputForManualLottoCount();
        List<LottoTicket> manualLottoTickets = createManualLottoTickets(manualLottoCount);
        return new PurchaseInfo(lottoPurchaseMoney, manualLottoCount, manualLottoTickets);
    }

    private List<LottoTicket> createManualLottoTickets(int purchaseManualCount) {
        inputView.printForInputManualLottoNumbersMessage();
        if (PurchaseInfo.MANUAL_COUNT_NO_TICKET_LIMIT > purchaseManualCount) {
            return new ArrayList<>();
        }
        return IntStream.range(0, purchaseManualCount)
            .mapToObj(num -> inputView.inputForLottoNumbers())
            .map(LottoTicket::new)
            .collect(Collectors.toList());
    }

    private LottoTickets buyLottoTicket(PurchaseInfo purchaseInfo) {
        LottoTickets lottoTickets = lottoService.buyTicket(purchaseInfo);
        outputView.printAllLottoTickets(purchaseInfo, lottoTickets);
        return lottoTickets;
    }

    private WinningLottoTicket createWinningLottoTicket() {
        LottoTicket winningTicket = new LottoTicket(inputView.inputForWinningNumbers());
        LottoNumber bonusNumber = LottoNumberFactory.getInstance(inputView.inputForBonusNumber());
        return new WinningLottoTicket(winningTicket, bonusNumber);
    }
}
