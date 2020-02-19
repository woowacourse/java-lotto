package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestNumberGenerator implements NumberGenerator{
    private static final List<Integer> numbers = new ArrayList<>();

    static {
        for (int i = 1; i <= 6; i++) {
            numbers.add(i);
        }
    }

    @Override
    public List<Integer> create() {
        return numbers;
    }
}
