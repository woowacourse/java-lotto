package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class WinningNumber {

    private final List<Integer> winningNumbers;

    public WinningNumber(String input) {
        String[] inputs = input.split(", ");
        validateNumber(inputs);
        duplicateCheck(inputs);
        this.winningNumbers = makeInteger(inputs);
    }

    private void duplicateCheck(String[] inputs) {
        Set<String> set = new HashSet<>();
        Arrays.stream(inputs)
                .forEach(s -> set.add(s));
        if (set.size() != 6) {
            throw new IllegalArgumentException("중복된 당첨번호가 있어서는 안됩니다.");
        }
    }

    private void validateNumber(String[] inputs) {
        if (!Arrays.stream(inputs)
                .allMatch(s -> s.chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야만 합니다.");
        }

        if (!Arrays.stream(inputs)
                .allMatch(s -> (Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 45))) {
            throw new IllegalArgumentException("당첨 번호는 1~45사이의 숫자여야 합니다.");
        }
    }

    private List<Integer> makeInteger(String[] inputs) {
        return Arrays.stream(inputs)
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }

    public List<Integer> getWinningNumber() {
        return winningNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WinningNumber)) return false;

        WinningNumber that = (WinningNumber) o;

        return Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return winningNumbers != null ? winningNumbers.hashCode() : 0;
    }
}
