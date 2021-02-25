package domain.ticket;

import domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.LottoNumber.MAX_NUMBER_VALUE;
import static domain.LottoNumber.MIN_NUMBER_VALUE;

public final class LottoTicket extends Ticket {

    private final static class TotalNumbersCache {
        private static final List<Integer> NUMBERS = new ArrayList<>();

        static {
            IntStream.rangeClosed(MIN_NUMBER_VALUE, MAX_NUMBER_VALUE)
                    .forEach(i -> NUMBERS.add(i));
        }

        private TotalNumbersCache() {
        }

        private static int get(int idx) {
            if (idx >= NUMBERS.size()) {
                throw new IndexOutOfBoundsException("캐시의 크기를 넘어간 인덱스를 참조하려고 했습니다.");
            }
            return NUMBERS.get(idx);
        }
    }

    public LottoTicket() {
        this(Collections.emptyList());
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

    private List<Integer> makeFullNumbers(final List<Integer> selectedNumbers) {
        final List<Integer> numbers = new ArrayList<>(selectedNumbers);
        addRandomNumbers(numbers);
        return numbers;
    }

    private List<Integer> addRandomNumbers(final List<Integer> numbers) {
        Collections.shuffle(TotalNumbersCache.NUMBERS);

        int idx = 0;
        while (numbers.size() < LOTTO_TICKET_SIZE) {
            final int randomNumber = TotalNumbersCache.get(idx++);
            addIfNotDuplicated(numbers, randomNumber);
        }

        return numbers;
    }

    private void addIfNotDuplicated(final List<Integer> numbers, final int randomNumber) {
        if (numbers.contains(randomNumber)) {
            return;
        }
        numbers.add(randomNumber);
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return this.lottoNumbers
                .contains(lottoNumber);
    }

    public List<Integer> toIntegerList() {
        return lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList());
    }
}
