package domain;

import util.NumberPicker;

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
    
    public static List<Lotto> purchase(int money, NumberPicker numberPicker) {
        validateMinPrice(money);
        
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = money / PRICE;
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = numberPicker.pickUnique(START_NUMBER, END_NUMBER, NUMBER_COUNT);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
        return Collections.unmodifiableList(lottos);
    }
    
    private static void validateMinPrice(int money) {
        if (money < PRICE) {
            throw new IllegalStateException("금액은 1000원 이상이여아 합니다.");
        }
    }
    
    public int getMatchCount(List<Integer> matchNumbers) {
        int matchCount = 0;
        
        for (Integer matchNumber : matchNumbers) {
            if (numbers.contains(matchNumber)) {
                matchCount++;
            }
        }
        return matchCount;
    }
    
    public boolean isBonusMatch(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }
    
    private List<Integer> getSorted() {
        return numbers.stream()
                .sorted()
                .toList();
    }
    
    public List<Integer> getNumbers() {
        return numbers;
    }
    
    @Override
    public String toString() {
        return getSorted().toString();
    }
}
