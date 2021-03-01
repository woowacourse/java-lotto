package lotto.utils;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.domain.LottoNumber.*;
import static lotto.domain.LottoNumbers.LOTTO_NUMBER_SIZE;

public class AutoLottosGenerator implements LottosGenerator {

    public static final int FROM_INDEX = 0;

    private final Quantity quantity;

    public AutoLottosGenerator(Quantity autoQuantity) {
        quantity = autoQuantity;
    }

    @Override
    public Lottos generate() throws IllegalArgumentException {
        List<LottoNumber> numbers = new ArrayList<>(lottoNumbers.values());
        Lottos autoLottos = new Lottos();
        for (int i = 0; i < quantity.quantity(); i++) {
            Collections.shuffle(numbers);
            List<LottoNumber> lottoNumbers =
                    new ArrayList<>(numbers.subList(FROM_INDEX, LOTTO_NUMBER_SIZE));
            autoLottos.add(new Lotto(new LottoNumbers(lottoNumbers)));
        }
        return autoLottos;
    }
}
