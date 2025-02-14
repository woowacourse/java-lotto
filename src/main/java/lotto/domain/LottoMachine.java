package lotto.domain;

import static lotto.util.Constant.LOTTO_MONEY_UNIT;
import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_SIZE;

import java.util.ArrayList;
import java.util.List;
import lotto.util.RandomUtil;

public class LottoMachine {
    private final RandomUtil randomUtil;
    private final List<List<Integer>> lottoTickets;

    public LottoMachine(RandomUtil randomUtil, LottoMoney lottoMoney) {
        this.randomUtil = randomUtil;
        this.lottoTickets = generateLottoTickets(lottoMoney);
    }

    private List<List<Integer>> generateLottoTickets(LottoMoney lottoMoney) {
        List<List<Integer>> tickets = new ArrayList<>();
        int ticketNumber = lottoMoney.getLottoMoney() / LOTTO_MONEY_UNIT;
        for (int i = 0; i < ticketNumber; i++) {
            List<Integer> ticket = generateLottoTicket();
            tickets.add(ticket);
        }
        return tickets;
    }

    private List<Integer> generateLottoTicket() {
        List<Integer> lottoTicket = new ArrayList<>();
        while (lottoTicket.size() < LOTTO_NUMBER_SIZE) {
            addLottoTicketNumber(lottoTicket);
        }
        return lottoTicket;
    }

    private void addLottoTicketNumber(List<Integer> ticket) {
        int number = randomUtil.generate(LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE);
        if (!ticket.contains(number)) {
            ticket.add(number);
        }
    }

    public List<List<Integer>> getLottoTickets() {
        return lottoTickets;
    }
}
