package domain;

import java.util.HashSet;
import java.util.Set;

public class WinningNumber {
    private Lotto winningNumbers;

    public WinningNumber(String[] numbers) {
        Set<LottoNumber> winningNumbers = new HashSet<>();
        for (String number : numbers) {
            winningNumbers.add(new LottoNumber(number));
        }
        this.winningNumbers = new Lotto(winningNumbers);
    }
}

