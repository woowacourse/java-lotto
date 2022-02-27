package study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Rank;

public class EnumTest {

	@DisplayName("동등성 테스트(내부 값이 같은지?)")
	@Test
	void equals_test() {
		Rank rank = Rank.FIFTH;
		Rank other = Rank.FIFTH;

		Assertions.assertTrue(rank.equals(other));
	}

	@DisplayName("동알성 테스트(객체 자체가 같은지?)")
	@Test
	void same_object_test() {
		Rank rank = Rank.FIFTH;
		Rank other = Rank.FIFTH;

		Assertions.assertTrue(rank == other);
	}
}
