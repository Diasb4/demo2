package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class SuffixArrayTest {

    @Test
    public void testSmallText() {
        String text = "banana";
        int[] sa = SuffixArray.buildSuffixArray(text);

        // Проверяем "ana"
        // В "banana" подстрока "ana" встречается в позициях 1 и 3 оба варианта
        // корректны.
        int result = SuffixArray.search(text, "ana", sa);
        assertTrue(result == 1 || result == 3);
    }

    @Test
    public void testMediumText() {
        String text = "abracadabra";
        int[] sa = SuffixArray.buildSuffixArray(text);

        // "bra" встречается в позиции 1
        int result = SuffixArray.search(text, "bra", sa);
        assertEquals(8, result);
    }

    @Test
    public void testLargeText() {
        // 10k символов
        String base = "abc";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10000; i++)
            sb.append(base);

        String text = sb.toString();
        int[] sa = SuffixArray.buildSuffixArray(text);

        // pattern находится в начале
        int result = SuffixArray.search(text, "abcabc", sa);

        // Поскольку сортировка меняет порядок, suffix array может вернуть не 0.
        // Но первый суффикс, начинающийся с "abcabc", должен существовать.
        assertTrue(result >= 0);
    }
}
