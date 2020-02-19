package lotto.domain;

import java.util.List;

public class Lotto {
	protected List<Integer> lottoNumbers;

	public Lotto(List<Integer> lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	@Override
	public String toString() {
		return lottoNumbers.toString();
	}

	public int compare(WinLotto winLotto) {
		int count = 0;
		for (int num : lottoNumbers) {
			winLotto.isContain(num);
			count++;
		}
		return count;

	}

	protected boolean isContain(int num) {
		return lottoNumbers.contains(num);
	}
}
