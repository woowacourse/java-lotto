package service;

import domain.IntegerGenerator;
import domain.LottoMachine;
import domain.LottoTickets;
import domain.Payment;

public class IssuingService {
    public LottoTickets issueLottoTickets(Payment payment, IntegerGenerator generator) {
        LottoMachine lottoMachine = new LottoMachine(generator);
        return lottoMachine.generateLottoTickets(payment);
    }
}
