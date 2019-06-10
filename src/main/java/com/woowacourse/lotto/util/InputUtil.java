package com.woowacourse.lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputUtil {

    public static List<Integer> splitByComma(String str) {
        return Arrays.stream(str.split(","))
            .map(String::trim)
            .map(Integer::valueOf)
            .collect(Collectors.toList());
    }
}
