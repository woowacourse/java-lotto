package lotto.domain.lottogenerator;

import lotto.domain.Customer;
import lotto.domain.Lotto;

import java.util.List;

public interface LottoGenerator {
	List<Lotto> generator(Customer customer);
}
