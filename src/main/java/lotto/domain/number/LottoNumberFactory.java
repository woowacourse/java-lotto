package lotto.domain.number;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberFactory {
    private static final String WRONG_RANGE_MSG_FORMAT = "로또 번호가 잘 못 되었습니다. 최소값 : %d, 최대값 : %d, 입력값 : %d";

    private static final List<LottoNumber> lottoNumbers = IntStream
            .rangeClosed(LottoNumber.MIN_RANGE, LottoNumber.MAX_RANGE).mapToObj(LottoNumber::createLottoNumber)
            .collect(Collectors.toList());

    private LottoNumberFactory() {
    }

    public static LottoNumber of(int lottoNumber) {
        return lottoNumbers.stream()
                .filter(ball -> ball.equals(LottoNumber.createLottoNumber(lottoNumber)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(WRONG_RANGE_MSG_FORMAT, LottoNumber.MIN_RANGE, LottoNumber.MAX_RANGE, lottoNumber))
                );
    }
}
