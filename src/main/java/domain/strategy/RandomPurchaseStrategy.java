package domain.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomPurchaseStrategy implements PurchaseStrategy {

    private static final int MINIMUN_NUMBER = 1;
    private static final int MAXIMUN_NUMBER = 45;


    @Override
    public List<Integer> getNumbers() {
        List<Integer> balls = new ArrayList<>();
        for (int i = MINIMUN_NUMBER; i <= MAXIMUN_NUMBER; i++) {
            balls.add(i);
        }
        Collections.shuffle(balls);

        return balls.subList(0, 6).stream()
                .sorted()
                .collect(Collectors.toList());
    }

}
