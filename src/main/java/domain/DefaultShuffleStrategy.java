package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultShuffleStrategy implements ShuffleStrategy {

    @Override
    public List<Integer> shuffle(List<Integer> lottoNumbers) {
        List<Integer> numbers = new ArrayList<>(lottoNumbers);
        Collections.shuffle(numbers);
        return numbers;
    }
}