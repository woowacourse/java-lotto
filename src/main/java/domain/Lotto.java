package domain;

import util.RandomNumberPicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;
    private static final int PRICE = 1000;
    
    private final List<Integer> numbers;
    
    public Lotto(List<Integer> numbers) {
        // TODO: 6, 중복 검증 필요
        this.numbers = numbers;
    }
    
    public static List<Lotto> purchase(int money, RandomNumberPicker randomNumberPicker) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = money / PRICE;
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = randomNumberPicker.pickUnique(START_NUMBER, END_NUMBER, NUMBER_COUNT);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
        return Collections.unmodifiableList(lottos);
    }
}
