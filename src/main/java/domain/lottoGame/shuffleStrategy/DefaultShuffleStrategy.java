package domain.lottoGame.shuffleStrategy;

import domain.lottoGame.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultShuffleStrategy implements ShuffleStrategy {

    @Override
    public List<LottoNumber> shuffle(List<LottoNumber> lottoNumbers) {
        List<LottoNumber> numbers = new ArrayList<>(lottoNumbers);
        Collections.shuffle(numbers);
        return numbers;
    }
}