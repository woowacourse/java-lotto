package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.WinningNumber;

import java.util.List;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;

public class WinningNumberDto {
    private List<Integer> numbers;
    private int bonusNumber;

    public WinningNumberDto() {
    }

    public WinningNumberDto(final List<Integer> numbers, final int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(final int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public WinningNumber toEntity() {
        Lotto winningLotto = new Lotto(generateLottoNumbers(numbers));
        return new WinningNumber(winningLotto, bonusNumber);
    }
}
