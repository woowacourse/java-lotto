package com.woowacourse.lotto.domain;

import java.util.Arrays;

public enum LottoRank {
	ZERO(0, 0),
	FOURTH(3, 5000),
	THIRD(4, 50000),
	SECOND(5, 1500000),
	FIRST(6, 2000000000);

	private int count;
	private int price;

	LottoRank(int count, int price) {
		this.count = count;
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}

	public int getCount() {
		return this.count;
	}

	public static LottoRank valueOf(int count) {
		return Arrays.stream(values()).filter(rank -> rank.count == count).findAny().orElse(ZERO);
	}
}
