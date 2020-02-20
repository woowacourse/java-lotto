package domain.lottonumber.generator;

import domain.lottonumber.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class UserNumberGenerator implements NumberGenerator {
    List<LottoNumber> numbers = new ArrayList<>();

    public void init(List<Integer> inputNumbers) {
        numbers.clear();
        for(int number : inputNumbers){
            numbers.add(LottoNumber.of(number));
        }
    }

    @Override
    public List<LottoNumber> create() {
        return numbers;
    }
}
