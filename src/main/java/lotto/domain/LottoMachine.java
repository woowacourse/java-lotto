package lotto.domain;

import static lotto.util.Constant.LOTTO_MONEY_UNIT;
import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_SIZE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoMachine {
    private List<List<Integer>> lottoTickets = new ArrayList<>();

    public LottoMachine(LottoMoney lottoMoney) {
        int ticketNumber = lottoMoney.getLottoMoney() / LOTTO_MONEY_UNIT;
        this.lottoTickets = generateLottoTickets(ticketNumber);
    }

    private List<List<Integer>> generateLottoTickets(int ticketNumber) {
        List<List<Integer>> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketNumber; i++) {
            List<Integer> lottoTicket = generateLottoTicket();
            lottoTickets.add(lottoTicket);
        }
        return lottoTickets;
    }

    private List<Integer> generateLottoTicket() {
        Random random = new Random();
        List<Integer> lottoTicket = new ArrayList<>();

        while (lottoTicket.size() < LOTTO_NUMBER_SIZE) {
            int number = random.nextInt(LOTTO_NUMBER_MAX_RANGE) + 1;
            if (!lottoTicket.contains(number)) {
                lottoTicket.add(number);
            }
        }
        return lottoTicket;
    }

    public List<List<Integer>> getLottoTickets() {
        return lottoTickets;
    }
}
