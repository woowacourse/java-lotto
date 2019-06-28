package lotto.dto;

import lotto.domain.WinningLotto;

import java.util.List;

public class WinningLottoDto {
    private final List<Integer> numbers;
    private final int bonus;

    public WinningLottoDto(List<Integer> numbers, int bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    public WinningLottoDto(WinningLotto winningLotto) {
        this.numbers = winningLotto.ticketNumbers();
        this.bonus = winningLotto.bonusNumbers();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonus() {
        return bonus;
    }
}
