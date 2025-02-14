package lotto.domain;

import static lotto.util.Constant.LOTTO_MONEY_UNIT;
import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_SIZE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.util.LottoNumberGenerator;

public class LottoMachine {

    private final LottoNumberGenerator lottoNumberGenerator;
    private final List<Set<Integer>> lottoTickets;

    public LottoMachine(LottoNumberGenerator lottoNumberGenerator, LottoMoney lottoMoney) {
        this.lottoNumberGenerator = lottoNumberGenerator;
        this.lottoTickets = generateLottoTickets(lottoMoney);
    }

    private List<Set<Integer>> generateLottoTickets(LottoMoney lottoMoney) {
        List<Set<Integer>> tickets = new ArrayList<>();
        int ticketNumber = lottoMoney.getLottoMoney() / LOTTO_MONEY_UNIT;
        for (int i = 0; i < ticketNumber; i++) {
            Set<Integer> ticket = generateLottoTicket();
            tickets.add(ticket);
        }
        return tickets;
    }

    private Set<Integer> generateLottoTicket() {
        Set<Integer> lottoTicket = new HashSet<>();
        while (lottoTicket.size() < LOTTO_NUMBER_SIZE) {
            addLottoTicketNumber(lottoTicket);
        }
        return lottoTicket;
    }

    private void addLottoTicketNumber(Set<Integer> ticket) {
        int number = lottoNumberGenerator.generate(LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE);
        ticket.add(number);
    }

    public List<Set<Integer>> getLottoTickets() {
        return lottoTickets;
    }
}
