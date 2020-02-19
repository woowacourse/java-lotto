package domain;

import java.util.HashSet;
import java.util.Set;

public class WinningNumber {
    private Lotto winningNumbers;
    private LottoNumber bonusNumber;

    public WinningNumber(String[] numbers, String bonusNumber) {
        Set<LottoNumber> winningNumbers = new HashSet<>();
        for (String number : numbers) {
            winningNumbers.add(new LottoNumber(number));
        }
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }
}

