package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import exception.LackOfMoneyException;

public class LottoFactory {
	private static final List<LottoNumber> numbers = new ArrayList<>();
	private static final int LOTTO_LENGTH_FRONT = 0;
	private static final int LOTTO_LENGTH = 6;
	private static final int LOTTO_NUMBER_FRONT = 1;
	private static final int LOTTO_NUMBER_RANGE = 45;

	static {
		for (int i = LOTTO_NUMBER_FRONT; i <= LOTTO_NUMBER_RANGE; i++) {
			numbers.add(LottoNumber.createNumber(i));
		}
	}

	public static List<Lotto> createAutoLottos(int amount) {
		validateAmonut(amount);
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			lottos.add(drawAutoLotto());
		}
		return lottos;
	}

	// todo : 예외 추가필요
	public static List<Lotto> createSelfLottos(List<String> inputSelfNumbers) {
		List<Lotto> lottos = new ArrayList<>();
		for (String numbers : inputSelfNumbers) {
			lottos.add(numbersToLotto(numbers));
		}
		return lottos;
	}

	private static Lotto numbersToLotto(String numbers) {
		return new Lotto(Arrays.stream(numbers.split(", "))
			.map(LottoNumber::createNumber)
			.collect(Collectors.toList()));
	}

	//todo : exception 변경필요
	private static void validateAmonut(int amount) {
		if (amount < 0) {
			throw new LackOfMoneyException();
		}
	}

	private static Lotto drawAutoLotto() {
		Collections.shuffle(numbers);
		ArrayList<LottoNumber> subNumbers = new ArrayList<>(numbers.subList(LOTTO_LENGTH_FRONT, LOTTO_LENGTH));
		return new Lotto(subNumbers);
	}

}