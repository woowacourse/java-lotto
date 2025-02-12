package lotto.domain;

import java.util.*;

public class Lotto {
    public static final int PRICE = 1000;
    public Set<Integer> numbers = new TreeSet<>();

    public Lotto() {
        while (numbers.size() < 6) {
            numbers.add((int)(Math.random() * 45) + 1);
        }
    }
}
