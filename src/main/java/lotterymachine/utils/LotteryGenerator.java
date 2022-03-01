package lotterymachine.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotterymachine.model.LotteryTicket;
import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;

public class LotteryGenerator {
    private static final List<Integer> preparedNumbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
    private static final int FIRST_NUMBER_INDEX = 0;
    private static final int LAST_NUMBER_INDEX = 6;

    public static List<LotteryTicket> generate(Count targetAmount) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (int i = 0; i < targetAmount.getNumber(); i++) {
            lotteryTickets.add(generateTicket());
        }
        return lotteryTickets;
    }

    private static LotteryTicket generateTicket() {
        Collections.shuffle(preparedNumbers);
        List<Ball> selectedBalls = Ball.createBalls(preparedNumbers.subList(FIRST_NUMBER_INDEX, LAST_NUMBER_INDEX));
        return new LotteryTicket(selectedBalls);
    }
}
