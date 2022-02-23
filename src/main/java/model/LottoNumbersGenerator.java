package model;

import java.util.ArrayList;
import java.util.List;

public abstract class LottoNumbersGenerator {

    public List<LottoNumbers> generate(int quantity) {
        List<LottoNumbers> result = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            result.add(createLottoNumbers());
        }
        return result;
    }

    protected abstract LottoNumbers createLottoNumbers();
}
