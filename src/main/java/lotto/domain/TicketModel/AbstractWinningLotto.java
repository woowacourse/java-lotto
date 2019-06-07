package lotto.domain.TicketModel;

import java.util.List;

public class AbstractWinningLotto implements WinningTicket {
    private Ticket winningTicket;
    private int bonus;

    public AbstractWinningLotto(List<Integer> numbers, int bonus) {
        TicketCreator generator = new LottoGenerator();
        this.winningTicket = generator.create(numbers);
        this.bonus = bonus;
    }

    @Override
    public int match(Ticket lotto) {
        return match(lotto.numbers());
    }

    private int match(List<Integer> lottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(number -> winningTicket.contains(number))
                .count();
    }

    @Override
    public boolean bonus(Ticket lotto) {
        return bonus(lotto.numbers());
    }

    private boolean bonus(List<Integer> numbers) {
        return numbers.contains(bonus);
    }
}
