package lotto.domain;

import java.util.List;
import lotto.domain.generator.AutoLottoTicketGenerator;
import lotto.domain.vo.LottoPurchaseMoney;

public class LottoMachine {

    public LottoTickets purchase(LottoPurchaseMoney lottoPurchaseMoney, List<LottoTicket> manualLottoNumbers) {

        int manualCount = manualLottoNumbers.size();

        int autoCount = lottoPurchaseMoney.calculate(manualCount);

        return new LottoTickets(autoCount, manualLottoNumbers, new AutoLottoTicketGenerator());
    }
}
