package lotto.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumbersSplitterTest {
    @Test
    void 입력한_번호가_숫자가_아닌_경우() {
        assertThrows(NumberFormatException.class, () ->
                NumbersSplitter.split("a,1,2,3,4,5"));
    }

}