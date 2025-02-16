package service;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoRule;
import domain.Lottos;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoNumberGenerator {

    private final SecureRandom random;

    public LottoNumberGenerator(SecureRandom random) {
        this.random = random;
    }

    public Lottos generateLottos(int repeatCount) {
        validate(repeatCount);

        return Lottos.from(
                IntStream.range(0, repeatCount)
                        .mapToObj(repeat -> generateLotto())
                        .collect(Collectors.toList()));
    }

    private void validate(int repeatCount) {
        validateRepeat(repeatCount);
    }

    private void validateRepeat(int repeatCount) {
        int minRepeatCount = 1;

        if (repeatCount < minRepeatCount) {
            throw new IllegalArgumentException(minRepeatCount + "회 이상 반복해야 합니다.");
        }
    }

    private Lotto generateLotto() {
        return Lotto.from(
                Stream.generate(this::generateLottoNumber)
                        .distinct()
                        .limit(LottoRule.LOTTO_SELECTION_SIZE.getValue())
                        .collect(Collectors.toList()));
    }

    private LottoNumber generateLottoNumber() {
        int lottoNumber = random.nextInt(LottoRule.MAX_LOTTO_NUMBER.getValue() - LottoRule.MIN_LOTTO_NUMBER.getValue() + 1)
                + LottoRule.MIN_LOTTO_NUMBER.getValue();

        return LottoNumber.from(lottoNumber);
    }
}
