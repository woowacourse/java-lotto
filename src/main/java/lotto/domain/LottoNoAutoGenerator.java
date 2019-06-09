package lotto.domain;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.IntStream;

public class LottoNoAutoGenerator implements LottoNoGenerator {
    private Stack<Integer> lottoNumbers;

    public LottoNoAutoGenerator() {
        lottoNumbers = IntStream.rangeClosed(LottoNo.MIN_NO, LottoNo.MAX_NO)
                .collect(Stack::new, Stack::add, Stack::addAll);
    }

    @Override
    public int generate() {
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.pop();
    }

    @Override
    public int size() {
        return Lotto.LOTTO_SIZE;
    }
}
