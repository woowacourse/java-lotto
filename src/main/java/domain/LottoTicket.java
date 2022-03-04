package domain;

import java.util.*;

import static constant.LottoConstant.*;

public class LottoTicket {
	private static final int NUMBER_FOR_BONUS_CHECK = 5;

	private final static List<Integer> candidates = initCandidates();
	private final LottoNumbers numbers;

	private LottoTicket(LottoNumbers numbers) {
		this.numbers = numbers;
	}

	public static LottoTicket byRandom() {
		Collections.shuffle(candidates);
		return new LottoTicket(new LottoNumbers(candidates.subList(0, NUMBER_OF_NUMBERS)));
	}

	public static LottoTicket byManual(LottoNumbers lottoNumbers) {
		return new LottoTicket(lottoNumbers);
	}

	public ResultStatics calculate(AnswerLotto answerLotto) {
		int count = numbers.calculateDuplicatedCount(answerLotto.getNumbers());
		boolean bonus = false;

		if (count == NUMBER_FOR_BONUS_CHECK) {
			bonus = numbers.isExist(answerLotto.getBonusNumber());
		}

		return ResultStatics.of(count, bonus);
	}

	public Set<LottoNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers.getNumbers());
	}

	private static List<Integer> initCandidates() {
		List<Integer> candidates = new ArrayList<>();
		for (int eachNumber = MIN_NUMBER; eachNumber <= MAX_NUMBER; eachNumber++) {
			candidates.add(eachNumber);
		}

		return candidates;
	}
}
