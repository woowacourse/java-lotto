package lotto.domain;


import lotto.domain.exceptions.LottoTicketException;

import java.util.ArrayList;
import java.util.List;

public class LottoCreator {
    public static final int LOTTO_PRICE = 1000;
    private static LottoCreator lottoCreator;

    private LottoCreator() {

    }

    public static LottoCreator getLottoCreator() {
        if (lottoCreator == null) {
            lottoCreator = new LottoCreator();
            return lottoCreator;
        }
        return lottoCreator;
    }

    public UserLottos createUserLotto(int lottoMoney, int manualCount, List<List<Integer>> numbers) {
        validate(lottoMoney, manualCount, numbers);
        List<Ticket> tickets = manualLotto(numbers);
        autoLotto(lottoMoney, tickets);
        return new UserLottos(tickets);
    }

    public UserLottos createUserLotto(List<List<Integer>> numbers) {
        List<Ticket> tickets = manualLotto(numbers);
        return new UserLottos(tickets);
    }

    public WinningLotto createWinningLotto(List<Integer> numbers, int bonus) {
        return new WinningLotto(create(numbers), bonus(bonus));
    }

    private void autoLotto(int lottoMoney, List<Ticket> tickets) {
        int iterate = lottoMoney / LOTTO_PRICE;
        for (int i = tickets.size(); i < iterate; i++) {
            tickets.add(create());
        }
    }

    private List<Ticket> manualLotto(List<List<Integer>> numbers) {
        List<Ticket> tickets = new ArrayList<>();
        for (List<Integer> number : numbers) {
            tickets.add(this.create(number));
        }
        return tickets;
    }

    private void validate(int lottoMoney, int manualCount, List<List<Integer>> numbers) {
        if (lottoMoney < LOTTO_PRICE) {
            throw new LottoTicketException();
        }
        if (manualCount != numbers.size()) {
            throw new LottoTicketException();
        }
    }

    private LottoNumber bonus(int bonus) {
        return new LottoNumber(bonus);
    }

    public Ticket create(List<Integer> numbers) {
        return new Lotto(LottoNumberPool.manual(numbers));
    }

    private Ticket create() {
        return new Lotto(new LottoNumbers(LottoNumberPool.random()));
    }
}
