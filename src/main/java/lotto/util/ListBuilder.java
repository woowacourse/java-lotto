package lotto.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListBuilder {
	public static <T> List<T> merge(final List<T> first, final List<T> second) {
		return Stream.concat(first.stream(), second.stream())
				.collect(Collectors.toUnmodifiableList());
	}
}
