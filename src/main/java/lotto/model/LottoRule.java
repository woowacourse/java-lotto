package lotto.model;

import java.util.List;
import java.util.stream.Collectors;

public abstract class LottoRule {
    private final int numberStart;
    private final int numberEnd;
    private final int numberCount;
    private final int price;
    private final String numberFormatMessage;

    LottoRule(final int start, final int end, final int count, final int price) {
        this.numberStart = start;
        this.numberEnd = end;
        this.numberCount = count;
        this.price = price;
        this.numberFormatMessage
                = "로또 번호는 "
                + numberStart
                + "부터 "
                + numberEnd
                + " 사이의 중복되지 않은 숫자 "
                + numberCount
                + "개로 이루어져 있어야 합니다.";
    }

    //    public int getNumberStart() {
    //        return numberStart;
    //    }

    public int getNumberEnd() {
        return numberEnd;
    }

    //    public int getNumberCount() {
    //        return numberCount;
    //    }

    public int getPrice() {
        return price;
    }

    public String getNumberFormatMessage() {
        return numberFormatMessage;
    }

    private boolean isValidListCount(final List<Integer> numbers) {
        return numbers.size() == numberCount;
    }

    public boolean isValidNumberRange(final int number) {
        return (numberStart <= number) && (number <= numberEnd);
    }

    public List<Integer> numbersArrange(final List<Integer> numbers) {
        return numbers.stream()
                .distinct() // 중복 제거
                .sorted() // 오름차순 정렬
                .collect(Collectors.toList());
    }

    public boolean isValidList(List<Integer> numbers) {
        numbers = numbersArrange(numbers);
        if (!isValidListCount(numbers)) {
            return false;
        }
        for (int i : numbers) {
            if (!isValidNumberRange(i)) {
                return false;
            }
        }
        return true;
    }
}
