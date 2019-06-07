package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.persistence.dto.LottoDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Lotto {
    public static final int UNIT_PRICE = 1000;

    private LottoNumberGroup nums;

    Lotto(LottoNumberGroup nums) {
        this.nums = nums;
    }

    public int countMatch(LottoNumberGroup otherNumber) {
        return nums.countMatch(otherNumber);
    }

    public boolean contains(LottoNumber n) {
        return nums.contains(n);
    }

    public void forEachNums(Consumer<LottoNumber> consumer) {
        nums.forEachNumbers(consumer);
    }

    public LottoDto toDto() {
        LottoDto dto = new LottoDto();
        List<LottoNumber> numList = new ArrayList<>();
        nums.forEachNumbers(numList::add);
        dto.setNumber0(numList.get(0).toInt());
        dto.setNumber1(numList.get(1).toInt());
        dto.setNumber2(numList.get(2).toInt());
        dto.setNumber3(numList.get(3).toInt());
        dto.setNumber4(numList.get(4).toInt());
        dto.setNumber5(numList.get(5).toInt());
        dto.setPrice(Lotto.UNIT_PRICE);
        return dto;
    }

    public static Lotto from(LottoDto dto) {
        return new Lotto(LottoNumberGroup.of(Arrays.asList(
            dto.getNumber0(),
            dto.getNumber1(),
            dto.getNumber2(),
            dto.getNumber3(),
            dto.getNumber4(),
            dto.getNumber5()
        )));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(nums, lotto.nums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nums);
    }

    @Override
    public String toString() {
        return new StringBuilder("Lotto { nums: ")
            .append(nums)
            .append(" }")
            .toString();
    }
}
