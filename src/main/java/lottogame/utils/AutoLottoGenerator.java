package lottogame.utils;

import lottogame.domain.stats.Quantity;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoGenerator {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static List<LottoNumber> numbers;

    static {
        numbers = IntStream.range(LOTTO_MIN, LOTTO_MAX)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    private final Quantity quantity;

    public AutoLottoGenerator(Quantity quantity) {
        this.quantity = quantity;
    }

    @Override
    public List<Lotto> generateLottos() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < quantity.value(); i++) {
            lottos.add(new Lotto(makeNumbers()));
        }
        return lottos;
    }

    public List<LottoNumber> makeNumbers() {
        Collections.shuffle(numbers);
        List<LottoNumber> selectNumber = numbers.subList(0, 6);
        Collections.sort(selectNumber);
        return new ArrayList<>(selectNumber);
    }
}
