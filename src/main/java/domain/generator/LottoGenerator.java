package domain.generator;

import java.util.List;

import domain.Lotto;
import domain.LottoNumber;

public interface LottoGenerator {
	List<LottoNumber> LOTTO_BUCKET = LottoNumber.ofList();

	List<Lotto> creatLottos();
}
