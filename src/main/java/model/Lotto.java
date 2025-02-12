package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers = new ArrayList<>();

    public Lotto() {
        // TODO: 로직 별도 메서드(클래스)로 분리하기
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while(true) {
            set.add(random.nextInt(LottoConstants.MAX_NUMBER) + LottoConstants.MIN_NUMBER);
            if(set.size() == LottoConstants.NUMBER_COUNT) {
                break;
            }
        }
        for(int number : set) {
            numbers.add(number);
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
