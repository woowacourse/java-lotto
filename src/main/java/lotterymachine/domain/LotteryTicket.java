package lotterymachine.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lotterymachine.view.ErrorMessage.INVALID_SIZE;

public class LotteryTicket {
    private static final int TICKET_SIZE = 6;
    private final List<LotteryNumber> numbers;

    public LotteryTicket(final List<LotteryNumber> numbers) {
        validateSize(numbers);
        this.numbers = numbers;
    }

    public int countMatchingNumbers(List<LotteryNumber> numbers) {
        return (int) numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public boolean containsNumber(LotteryNumber number) {
        return this.numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        Collections.sort(this.numbers);
        return numbers.stream()
                .map(LotteryNumber::getNumber)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateSize(List<LotteryNumber> numbers) {
        if (!isLotteryTicketSize(numbers.size())) {
            throw new IllegalArgumentException(INVALID_SIZE.getMessage());
        }
    }

    private static boolean isLotteryTicketSize(int size) {
        return size == TICKET_SIZE;
    }
}
