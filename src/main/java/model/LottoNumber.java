package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN = 1;
    public static final int MAX = 45;

    private static final Map<Integer, LottoNumber> numberPool = new HashMap<>();
    private final int val;

    public static LottoNumber of(int i) {
        if (i < MIN || i > MAX) {
            throw new IllegalArgumentException();
        }
        if (!numberPool.containsKey(i)) {
            numberPool.put(i, new LottoNumber(i));
        }
        return numberPool.get(i);
    }

    public static LottoNumber of(String input) {
        return of(Integer.parseInt(input.trim()));
    }

    private LottoNumber(int i) {
        this.val = i;
    }

    @Override
    public int compareTo(LottoNumber rhs) {
        return val - rhs.val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber number = (LottoNumber) o;
        return val == number.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
