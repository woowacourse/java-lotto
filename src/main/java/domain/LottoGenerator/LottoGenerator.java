package domain.LottoGenerator;

import domain.Lotto.Lotto;

import java.util.List;

public interface LottoGenerator {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

    Lotto generateLotto();

    Lotto generateLotto(List<Integer> numbers);
=======
    Lotto generateLotto(List<Integer> lottoNumbers);
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
    Lotto generateLotto();

    Lotto generateWinningLotto(List<Integer> numbers);
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
=======

    Lotto generateLotto();

    Lotto generateLotto(List<Integer> numbers);
>>>>>>> 27b9569 (refactor : 인터페이스에서 원하는 추상 메서드만 몸체를 구현하고자 어댑터 클래스 추가)
}
