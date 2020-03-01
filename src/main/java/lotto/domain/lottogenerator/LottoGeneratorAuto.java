package lotto.domain.lottogenerator;

import lotto.domain.Customer;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.domain.lottogenerator.LottoNo.*;


public class LottoGeneratorAuto implements LottoGenerator {
	private static final int LOTTO_NO_PICK_FROM_INDEX = 0;
	private static final String ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY = "입력값이 비었습니다.";

	private static List<LottoNo> pickSixRandomNo() {
		List<LottoNo> lotto = new ArrayList<>(lottoNoBox.values());
		Collections.shuffle(lotto);
		lotto = lotto.subList(LOTTO_NO_PICK_FROM_INDEX, Lotto.LOTTO_SIZE);
		return lotto;
	}

	private static void validate(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY);
		}
	}

	private static Lotto createLottoAuto() {
		List<LottoNo> lotto = new ArrayList<>(pickSixRandomNo());
		return new Lotto(lotto);
	}

	@Override
	public List<Lotto> generator(Customer customer) {
		validate(customer);
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < customer.calculatorAutoLottoCount(); i++) {
			lottos.add(createLottoAuto());
		}
		return lottos;
	}
}
