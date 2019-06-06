package lotto.domain;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.IntStream;

public class LottoAutoGenerator implements LottoGenerator {
    private Stack<LottoNo> lottoNos;

    public LottoAutoGenerator() {
        lottoNos = IntStream.rangeClosed(LottoNo.MIN_NO, LottoNo.MAX_NO)
                .mapToObj(LottoNo::of)
                .collect(Stack::new, Stack::add, Stack::addAll);
    }

    @Override
    public LottoNo generate() {
        Collections.shuffle(lottoNos);
        return lottoNos.pop();
    }

    @Override
    public int size() {
        return Lotto.LOTTO_SIZE;
    }
}
