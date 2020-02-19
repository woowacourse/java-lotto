package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestNumberGenerator implements NumberGenerator{
    private final List<Integer> numbers;

    public TestNumberGenerator(){
        numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(i);
        }
    }

    public TestNumberGenerator(List<Integer> values){
        if(Objects.isNull(values)){
            throw new IllegalArgumentException("null값은 입력할 수 없습니다.");
        }
        this.numbers = values;
    }

    @Override
    public List<Integer> create() {
        return numbers;
    }
}
