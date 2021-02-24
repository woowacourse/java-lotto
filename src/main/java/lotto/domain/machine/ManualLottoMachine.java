package lotto.domain.machine;

import lotto.domain.Money;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTickets;

import java.util.List;

public class ManualLottoMachine {
    private static final String TICKETS_SIZE_ERROR_MSG_FORMAT = "수동 로또의 개수와 입력한 수동 로또 번호의 숫자가 같지 않습니다. 개수 : %d, 입력한 수동 번호의 수 : %d";

    private final LottoMachine lottoMachine;

    public ManualLottoMachine(Money lottoPrice) {
        this.lottoMachine = new LottoMachine(lottoPrice);
    }

    public LottoTickets createTickets(int numberOfTickets, List<LottoNumbers> lottoNumbersBundle) {
        validateTicketsSize(numberOfTickets, lottoNumbersBundle.size());
        return lottoMachine.createTickets(numberOfTickets, lottoNumbersBundle);
    }

    private void validateTicketsSize(int numberOfTickets, int size) {
        if (numberOfTickets != size) {
            throw new IllegalArgumentException(String.format(TICKETS_SIZE_ERROR_MSG_FORMAT, numberOfTickets, size));
        }
    }
}
