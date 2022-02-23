package model;

import java.util.ArrayList;
import java.util.List;

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
        return LottoNumbers.withSixNumbers(nextNumber(), nextNumber(), nextNumber(),
                nextNumber(), nextNumber(), nextNumber());
    }

    private int nextNumber() {
        return numberQueue.get();
    }
}
