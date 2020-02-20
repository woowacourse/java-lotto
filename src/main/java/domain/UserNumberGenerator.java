package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserNumberGenerator implements NumberGenerator{
    List<LottoNumber> numbers = new ArrayList<>();

    public void input(List<Integer> ints) {
        numbers = ints.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> create(){
        return numbers;
    }
}
