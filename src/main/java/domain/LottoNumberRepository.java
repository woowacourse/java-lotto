package domain;

/**
 * LottoNumberRepository.java
 * 로또 넘버들을 한 데에 모아 생성해놓은 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon스
 * @author Daeun Lee, github.com/da-nyee
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumberRepository {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final int MINIMUM_RANGE = 0;
    private static final int MAXIMUM_RANGE = 6;

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    static {
        IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
                .mapToObj(Integer::toString)
                .map(LottoNumber::new)
                .forEach(lottoNumbers::add);
    }

    public static List<LottoNumber> shuffleLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> splitLottoNumbers = new ArrayList<>(lottoNumbers.subList(MINIMUM_RANGE, MAXIMUM_RANGE));
        return Collections.unmodifiableList(splitLottoNumbers);
    }
}
