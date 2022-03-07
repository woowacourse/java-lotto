package lotterymachine.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomLotteryNumbersGenerator implements LotteryNumbersGenerator {
    @Override
    public List<LotteryNumber> generate() {
        ArrayList<Integer> keyOfNumbers = new ArrayList<>(LotteryNumber.NUMBERS.keySet());
        Collections.shuffle(keyOfNumbers);
        return keyOfNumbers.stream()
                .limit(LotteryTicket.TICKET_SIZE)
                .map(LotteryNumber::from)
                .collect(Collectors.toList());
    }
}