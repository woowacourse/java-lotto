package domain.LottoGenerator;

import domain.Lotto.Lotto;

import java.util.List;

public interface LottoGenerator {
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
}
