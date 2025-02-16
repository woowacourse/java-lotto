package service;

import domain.Lotto;
import domain.vo.LottoNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLottoNumbersGenerator implements RandomNumbersGenerator {
    @Override
    public List<Integer> generate() {
        validate(LottoNumber.MIN, LottoNumber.MAX, Lotto.SIZE);

        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();
        while(numbers.size() < Lotto.SIZE) {
            int number = random.nextInt(LottoNumber.MAX - LottoNumber.MIN + 1) + LottoNumber.MIN;
            numbers.add(number);
        }
        return numbers.stream().toList();
    }

    private void validate(int start, int end, int count) {
        if (end - start + 1 < count) {
            throw new IllegalArgumentException("난수 생성 범위가 생성 가능 개수보다 적습니다.");
        }
    }
}
