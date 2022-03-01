package domain.LottoGenerator;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
import java.util.ArrayList;
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
<<<<<<< HEAD
public class AutoLottoGenerator extends LottoGeneratorAdpater {
=======
public class AutoLottoGenerator implements LottoGenerator {
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
public class AutoLottoGenerator extends LottoGeneratorAdpater {
>>>>>>> 27b9569 (refactor : 인터페이스에서 원하는 추상 메서드만 몸체를 구현하고자 어댑터 클래스 추가)

    private static final int LOTTO_SIZE = 6;

    @Override
<<<<<<< HEAD
<<<<<<< HEAD
    public Lotto generateLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(lottoNumbers);

        List<LottoNumber> collect = lottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted(Comparator.comparing(LottoNumber::getNumber))
                .collect(Collectors.toList());

        return new Lotto(collect);
=======
    public Lotto generateLotto(List<Integer> numbers) {
        Collections.shuffle(numbers);
=======
    public Lotto generateLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(lottoNumbers);
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)

        List<LottoNumber> collect = lottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted(Comparator.comparing(LottoNumber::getNumber))
                .collect(Collectors.toList());

<<<<<<< HEAD
        return new Lotto(lottoNumbers);
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
        return new Lotto(collect);
    }
<<<<<<< HEAD

    @Override
    public Lotto generateWinningLotto(List<Integer> numbers) {
        return null;
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
    }
=======
>>>>>>> 27b9569 (refactor : 인터페이스에서 원하는 추상 메서드만 몸체를 구현하고자 어댑터 클래스 추가)
}
