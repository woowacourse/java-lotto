package lotto.domain.random;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoRound;

import java.util.*;

public class RandomNumberGenerator {
    private final List<LottoNumber> lottoNumberPool = new LinkedList<>();

    public RandomNumberGenerator() {
        Map<Integer, LottoNumber> lottoNumberMapper = LottoNumber.getLottoNumberMapper();
        lottoNumberPool.addAll(lottoNumberMapper.values());
        Collections.shuffle(lottoNumberPool);
    }

    LottoNumber generate() {
        return lottoNumberPool.remove(0);
    }

    public List<LottoNumber> generateNumbers() {
        List<LottoNumber> randomNumbers = new ArrayList<>();
        for (int i = 0; i < LottoRound.LOTTO_NUMBER_AMOUNT; i++) {
            randomNumbers.add(generate());
        }
        return randomNumbers;
    }
}
