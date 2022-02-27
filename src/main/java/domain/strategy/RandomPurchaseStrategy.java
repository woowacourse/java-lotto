package domain.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomPurchaseStrategy implements PurchaseStrategy {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    @Override
    public List<Integer> generateNumbers() {
        final List<Integer> balls = new ArrayList<>();
        for (int i = MINIMUM_NUMBER; i <= MAXIMUM_NUMBER; i++) {
            balls.add(i);
        }
        Collections.shuffle(balls);

        return balls.subList(0, 6).stream()
                .sorted()
                .collect(Collectors.toList());
    }

}
