package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomPurchaseStrategy implements PurchaseStrategy {

    @Override
    public List<Integer> getNumbers() {
        List<Integer> balls = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            balls.add(i);
        }
        Collections.shuffle(balls);

        return balls.subList(0, 6).stream()
                .sorted()
                .collect(Collectors.toList());
    }

}
