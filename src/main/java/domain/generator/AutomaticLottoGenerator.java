package domain.generator;

import java.util.*;

public class AutomaticLottoGenerator implements LottoGenerator {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    @Override
    public Set<Integer> generateNumbers() {
        final List<Integer> balls = new ArrayList<>();
        for (int i = MINIMUM_NUMBER; i <= MAXIMUM_NUMBER; i++) {
            balls.add(i);
        }
        Collections.shuffle(balls);

        return new HashSet<>(balls.subList(0, 6));
    }

}
