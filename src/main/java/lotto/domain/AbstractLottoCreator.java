package lotto.domain;

import lotto.domain.Exceptions.LottoNumberDuplicateException;
import lotto.domain.Exceptions.LottoNumberException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class AbstractLottoCreator implements LottoCreator {
    protected static final int NUMBER_COUNT = 6;
    protected static final int MIN_NUMBER = 1;
    protected static final int MAX_NUMBER = 45;
    private static final List<Integer> lottoNumbers;

    static {
        lottoNumbers = new ArrayList<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            lottoNumbers.add(i);
        }
    }

    protected void validate(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new LottoNumberException();
        }
        if (new HashSet<Integer>(numbers).size() != numbers.size()) {
            throw new LottoNumberDuplicateException();
        }
        if (Collections.min(numbers) < MIN_NUMBER || Collections.max(numbers) > MAX_NUMBER) {
            throw new LottoNumberException();
        }
    }

    @Override
    public LottoTicket create() {
        return null;
    }

    @Override
    public LottoTicket create(List<Integer> numbers) {
        return null;
    }
}
