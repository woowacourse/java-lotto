package domain.lottonumber.generator;

import domain.lottonumber.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserNumberGenerator implements NumberGenerator {
    List<LottoNumber> numbers = new ArrayList<>();

    public void inputNumbers(List<Integer> ints) {
        numbers = ints.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> create(){
        return numbers;
    }
}
