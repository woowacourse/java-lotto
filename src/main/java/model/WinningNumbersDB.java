package model;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbersDB implements WinningNumbers {
    private final List<LottoNumber> numbers;

    protected WinningNumbersDB(int round) throws SQLException {
        this.numbers = WinningNumbersDAO.fetchWinningNumbers(round).stream()
                                                            .map(LottoNumber::of)
                                                            .collect(Collectors.collectingAndThen(
                                                                    Collectors.toList(),
                                                                    Collections::unmodifiableList
                                                            ));
        if (this.numbers.size() != Lotto.NUMBER_OF_PICKS + 1) {
            throw new NoWinningNumbersInDBException();
        }
    }

    @Override
    public List<LottoNumber> mainNumbers() {
        return this.numbers.subList(0, Lotto.NUMBER_OF_PICKS);
    }

    @Override
    public LottoNumber bonusNumber() {
        return this.numbers.get(Lotto.NUMBER_OF_PICKS);
    }
}