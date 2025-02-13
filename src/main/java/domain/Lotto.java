package domain;

import dto.LottoMatchResult;
import util.NumberPicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;
    private static final int PRICE = 1000;
    
    private final List<Integer> numbers;
    
    public Lotto(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberNotDuplicated(numbers);
        this.numbers = numbers;
    }
    
    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개가 되어야 합니다.");
        }
    }
    
    private void validateNumberNotDuplicated(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("로또 번호는 중복되면 안됩니다.");
        }
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
    
    public LottoMatchResult getMatchResult(List<Integer> matchNumbers, int bonusNumber) {
        validateMatchNumbersNotDuplicated(matchNumbers);
        validateBonusNumberNotDuplicated(matchNumbers, bonusNumber);
        int matchCount = 0;
        
        for (Integer matchNumber : matchNumbers) {
            if (numbers.contains(matchNumber)) {
                matchCount++;
            }
        }
        
        return new LottoMatchResult(matchCount, numbers.contains(bonusNumber));
    }
    
    private void validateBonusNumberNotDuplicated(List<Integer> matchNumbers, int bonusNumber) {
        if (matchNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 중복되면 안됩니다.");
        }
    }
    
    private void validateMatchNumbersNotDuplicated(List<Integer> matchNumbers) {
        if (matchNumbers.size() != new HashSet<>(matchNumbers).size()) {
            throw new IllegalArgumentException("로또 당첨 번호는 중복되면 안됩니다.");
        }
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
