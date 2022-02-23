package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LottoNumbersGenerator {
    private final NumberQueue numberQueue;

    public LottoNumbersGenerator(NumberQueue numberQueue) {
        this.numberQueue = numberQueue;
    }

    public List<LottoNumbers> generate(int quantity) {
        List<LottoNumbers> result = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            result.add(createLottoNumbers());
        }
        return result;
    }

    private LottoNumbers createLottoNumbers() {
        Set<Integer> lottoNumbers = nextSixNumbers();
        Iterator<Integer> iterator = lottoNumbers.iterator();
        return LottoNumbers.withSixNumbers(iterator.next(), iterator.next(), iterator.next(),
                iterator.next(), iterator.next(), iterator.next());
    }

    private Set<Integer> nextSixNumbers() {
        return numberQueue.get(6);
    }
}
