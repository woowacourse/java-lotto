package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Counter<T> {
    private final Map<T, Integer> counter;

    private Counter() {
        this(new HashMap<>());
    }

    private Counter(Map<T, Integer> counter) {
        this.counter = counter;
    }

    public static Counter create() {
        return new Counter();
    }

    public static <E> Counter<E> newInstance(Counter<E> instance) {
        // Todo: 좀더 좋은 방법이 없을까? 자바에서 좋은 복사 방식은 무엇이려나
        Counter<E> newInstance = new Counter<E>(new HashMap(instance.counter));

        return newInstance;
    }

    public void put(T key) {
        counter.put(key, count(key) + 1);
    }

    public int count(T key) {
        if (counter.containsKey(key)) {
            return counter.get(key);
        }
        return 0;
    }

    public Set<T> keySet() {
        return counter.keySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counter<?> counter1 = (Counter<?>) o;
        return Objects.equals(counter, counter1.counter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter);
    }
}
