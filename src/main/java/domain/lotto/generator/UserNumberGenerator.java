package domain.lotto.generator;

import domain.lotto.LottoNumber;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class UserNumberGenerator implements NumberGenerator {
    @Override
    public SortedSet<LottoNumber> create() {
        List<Integer> userInput = InputView.inputNumbers(OutputView::printUserNumbersFormat);
        return userInput.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
