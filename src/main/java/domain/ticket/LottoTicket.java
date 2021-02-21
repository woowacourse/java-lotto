package domain.ticket;

import domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.LottoNumber.MAX_NUMBER_VALUE;
import static domain.LottoNumber.MIN_NUMBER_VALUE;

public final class LottoTicket extends Ticket {
    private static final List<Integer> TOTAL_NUMBERS = new ArrayList<>();

    static {
        IntStream.rangeClosed(MIN_NUMBER_VALUE, MAX_NUMBER_VALUE)
                .forEach(i -> TOTAL_NUMBERS.add(i));
    }

    public LottoTicket(List<Integer> selectedNumbers) {
        List<Integer> numbers = generateNumbers(selectedNumbers);
        validate(numbers);

        this.lottoNumbers.addAll(
                numbers.stream()
                        .sorted()
                        .map(LottoNumber::valueOf)
                        .collect(Collectors.toList())
        );
    }

    @Override
    protected List<Integer> generateNumbers(final List<Integer> selectedNumbers) {
        if (hasDuplication(selectedNumbers)) {
            throw new IllegalArgumentException("선택한 번호 중에 중복 숫자가 존재합니다.");
        }
        return makeFullNumbers(selectedNumbers);
    }

    private boolean hasDuplication(final List<Integer> selectedNumbers) {
        return selectedNumbers.size() != new HashSet<>(selectedNumbers).size();
    }

    private List<Integer> makeFullNumbers(final List<Integer> selectedNumbers) {
        final List<Integer> numbers = new ArrayList<>(selectedNumbers);
        addRandomNumbers(numbers);
        return numbers;
    }

    private List<Integer> addRandomNumbers(final List<Integer> numbers) {
        Collections.shuffle(TOTAL_NUMBERS);

        TOTAL_NUMBERS.stream()
                .filter(randomNumber -> numbers.size() < LOTTO_TICKET_SIZE)
                .filter(randomNumber -> !numbers.contains(randomNumber))
                .forEach(randomNumber -> numbers.add(randomNumber));

        return numbers;
    }
}
