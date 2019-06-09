package lotto.domain.ticket.generator;

import lotto.domain.machine.ManualNumbers;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;

import java.util.ArrayList;
import java.util.List;

public class ManualGenerator implements Generator {
    private List<ManualNumbers> numberList;
    private int quantity;

    public ManualGenerator(int quantity, List<ManualNumbers> numberList) {
        this.quantity = quantity;
        this.numberList = numberList;
    }

    public List<LottoTicket> generateTickets() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            lottoTickets.add(generateTicket(numberList.get(i).getManualNumbers()));
        }
        return lottoTickets;
    }

    private LottoTicket generateTicket(List<LottoNumber> numbers) {
        return new LottoTicket(numbers);
    }
}
