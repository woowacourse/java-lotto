package domain;

import java.util.List;

public class WinningLotto extends Lotto {

    private final int bonus;

    public WinningLotto(List<Integer> inputWinningLotto, String bonus) {
        super(inputWinningLotto);
        this.bonus = validateBonus(bonus);
    }

    public boolean matchBonus(List<Integer> numbers) {
        return numbers.contains(bonus);
    }

    private int validateBonus(String inputBonus) {
        int bonus = validateIsInteger(inputBonus);
        validateRange(bonus);
        validateBonusDuplicate(bonus);

        return bonus;
    }
}
