package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningNumbersManual implements WinningNumbers {
    private final List<LottoNumber> mainNumbers;
    private final LottoNumber bonusNumber;

    protected WinningNumbersManual(Set<LottoNumber> mainNumbers, LottoNumber bonusNumber) {
        validation(mainNumbers, bonusNumber);
        this.mainNumbers = Collections.unmodifiableList(new ArrayList<>(mainNumbers));
        this.bonusNumber = bonusNumber;
    }

    protected WinningNumbersManual(String input) {
        List<LottoNumber> numbers = Stream.of(input.split(","))
                                    .map(LottoNumber::of)
                                    .collect(Collectors.toList());
        Set<LottoNumber> mainNumbers = new HashSet<>(numbers.subList(0, Lotto.NUMBER_OF_PICKS));
        LottoNumber bonusNumber = numbers.get(Lotto.NUMBER_OF_PICKS);
        validation(mainNumbers, bonusNumber);
        this.mainNumbers = Collections.unmodifiableList(new ArrayList<>(mainNumbers));
        this.bonusNumber = bonusNumber;

    }

    private void validation(Set<LottoNumber> mainNumbers, LottoNumber bonusNumber) {
        if ((mainNumbers.size() != Lotto.NUMBER_OF_PICKS) || mainNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<LottoNumber> mainNumbers() {
        return this.mainNumbers;
    }

    @Override
    public LottoNumber bonusNumber() {
        return this.bonusNumber;
    }
}