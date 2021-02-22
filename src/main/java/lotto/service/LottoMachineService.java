package lotto.service;

import lotto.domain.Money;
import lotto.domain.PurchaseInfo;
import lotto.domain.machine.AutoLottoMachine;
import lotto.domain.machine.LottoMachine;
import lotto.domain.machine.ManualLottoMachine;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTickets;

import java.util.List;

public class LottoMachineService {
    private final LottoMachine autoLottoMachine;
    private final LottoMachine manualLottoMachine;

    public LottoMachineService(Money lottoPrice) {
        this.autoLottoMachine = new AutoLottoMachine(lottoPrice);
        this.manualLottoMachine = new ManualLottoMachine(lottoPrice);
    }

    public LottoTickets createAllLottoTickets(PurchaseInfo purchaseInfo, List<LottoNumbers> lottoNumbersBundle) {
        LottoTickets autoLottoTickets = buyAutoLottoTicket(purchaseInfo.calculateLeftMoney());
        LottoTickets manualLottoTickets = buyManualLottoTicket(purchaseInfo.numberOfManualLotto(), lottoNumbersBundle);
        return manualLottoTickets.concat(autoLottoTickets);
    }

    private LottoTickets buyAutoLottoTicket(Money lottoPurchaseMoney) {
        int numberOfTickets = autoLottoMachine.calculateNumberOfTickets(lottoPurchaseMoney);
        return autoLottoMachine.createTicketsByMoney(numberOfTickets);
    }

    private LottoTickets buyManualLottoTicket(int sizeOfManualLotto, List<LottoNumbers> lottoNumbers) {
        return manualLottoMachine.createTickets(sizeOfManualLotto, lottoNumbers);
    }

    public Money getLottoPrice() {
        return autoLottoMachine.getLottoPrice();
    }

}
