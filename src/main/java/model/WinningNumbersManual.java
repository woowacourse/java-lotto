package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningNumbersManual implements WinningNumbers {
    private final List<LottoNumber> mainNumbers;
    private final LottoNumber bonusNumber;

    protected WinningNumbersManual(Set<LottoNumber> mainNumbers, LottoNumber bonusNumber) {
        if ((mainNumbers.size() != Lotto.NUMBER_OF_PICKS) || mainNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
        this.mainNumbers = Collections.unmodifiableList(new ArrayList<>(mainNumbers));
        this.bonusNumber = bonusNumber;
    }

    protected WinningNumbersManual(List<LottoNumber> winningNumbers) {
        this(
                new HashSet<>(winningNumbers.subList(0, Lotto.NUMBER_OF_PICKS)),
                winningNumbers.get(Lotto.NUMBER_OF_PICKS)
        );
    }

    protected WinningNumbersManual(String input) {
        this(Stream.of(input.split(","))
                    .map(LottoNumber::of)
                    .collect(Collectors.toList())
                    .subList(0, Lotto.NUMBER_OF_PICKS + 1)
        );

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