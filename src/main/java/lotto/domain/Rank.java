package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public enum Rank {
	FIRST(new ArrayList<Integer>(Collections.singletonList(6)), false, 2000000000),
	SECOND(new ArrayList<Integer>(Collections.singletonList(5)), true, 30000000),
	THIRD(new ArrayList<Integer>(Collections.singletonList(5)), false, 1500000),
	FOURTH(new ArrayList<Integer>(Collections.singletonList(4)), false, 50000),
	FIFTH(new ArrayList<Integer>(Collections.singletonList(3)), false, 5000),
	SIXTH(new ArrayList<Integer>(Arrays.asList(0, 1, 2)), false, 0);

	private List<Integer> winningNumbersCount;
	private boolean hasBonusLottoNumber; // TODO: 마지막까지도 안쓰이면 지우
	private int reward;

	Rank(List<Integer> winningNumbersCount, boolean hasBonusLottoNumber, final int reward) {
		this.winningNumbersCount = winningNumbersCount;
		this.hasBonusLottoNumber = hasBonusLottoNumber;
		this.reward = reward;
	}

	public static Rank getRank(int numberOfContain, boolean hasBonusLottoNumber) {
		if (numberOfContain != 5) {  // TODO MAgic number 없에기 -> 로또 넘버로 만ㄷ르어라 intfmf
 			return getRankNotRelatedToBonusLottoNumber(numberOfContain);
		}

		if (hasBonusLottoNumber) {
			return Rank.SECOND;
		}
		return Rank.THIRD;
	}

	private static Rank getRankNotRelatedToBonusLottoNumber(int numberOfContain) {
		return Arrays.stream(Rank.values())
				.filter(value -> value.winningNumbersCount.contains(numberOfContain))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("로또의 등수를 구할 수 없습니다."));
	}

	public int getReward() {
		return this.reward;
	}

	public List<Integer> getWinningNumbersCount() {
		return this.winningNumbersCount;
	}
}
