package lotto.domain;

import static lotto.util.Constant.DEFAULT_VALUE_ZERO;
import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_SIZE;

import java.util.ArrayList;
import java.util.List;
import lotto.util.RandomGenerator;

public class LottoMachine {

    private List<Lotto> lottoTickets = new ArrayList<>();

    public LottoMachine(LottoMoney lottoMoney) {
        int ticketNumber = lottoMoney.getTicketBuyAmount();
        this.lottoTickets = generateLottoTickets(ticketNumber);
    }

    private List<Lotto> generateLottoTickets(int ticketNumber) {
        List<Lotto> lottoTickets = new ArrayList<>();

        for (int i = DEFAULT_VALUE_ZERO; i < ticketNumber; i++) {
            List<Integer> lottoTicket = generateLottoTicket();
            lottoTickets.add(new Lotto(lottoTicket));
        }
        return lottoTickets;
    }

    private List<Integer> generateLottoTicket() {
        List<Integer> lottoTicket = new ArrayList<>();

        while (lottoTicket.size() < LOTTO_NUMBER_SIZE) {
            int number = RandomGenerator.generateRandomNumber(LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE);
            checkDuplicate(lottoTicket, number);
        }
        return lottoTicket;
    }

    private void checkDuplicate(List<Integer> lottoTicket, int number) {
        if (!lottoTicket.contains(number)) {
            lottoTicket.add(number);
        }
    }

    public List<Lotto> getLottoTickets() {
        return lottoTickets;
    }
}
