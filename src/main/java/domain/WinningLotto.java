package domain;

import java.util.List;

public class WinningLotto extends Lotto {

    private int bonus;

    public WinningLotto(String lotto, String bonus) {
        super(lotto);
        this.bonus = validateBonus(bonus);
    }

    public boolean matchBonus(List<Integer> numbers) {
        return numbers.contains(bonus);
    }
}
