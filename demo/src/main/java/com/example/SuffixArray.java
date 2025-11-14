package com.example;

import java.util.Arrays;

public class SuffixArray {

    public static int[] buildSuffixArray(String text) {
        int n = text.length();

        // Integer вместо int, чтобы можно было использовать Comparator
        Integer[] saObj = new Integer[n];
        for (int i = 0; i < n; i++) {
            saObj[i] = i;
        }

        // Сортируем с компаратором
        Arrays.sort(saObj, (a, b) -> compareSuffix(text, a, b));

        // Переносим в обычный int[]
        int[] sa = new int[n];
        for (int i = 0; i < n; i++) {
            sa[i] = saObj[i];
        }

        return sa;
    }

    // Сравнение суффиксов напрямую через индексы
    private static int compareSuffix(String text, int i, int j) {
        int n = text.length();
        while (i < n && j < n) {
            char c1 = text.charAt(i);
            char c2 = text.charAt(j);

            if (c1 != c2)
                return c1 - c2;

            i++;
            j++;
        }
        return (n - i) - (n - j);
    }

    // Сравнение pattern со строкой, начинающейся в text[pos]
    private static int comparePattern(String text, int pos, String pattern) {
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i < m; i++) {
            if (pos + i >= n)
                return -1;
            char c1 = text.charAt(pos + i);
            char c2 = pattern.charAt(i);
            if (c1 != c2)
                return c1 - c2;
        }
        return 0;
    }

    // Бинарный поиск подстроки
    public static int search(String text, String pattern, int[] sa) {
        int left = 0;
        int right = sa.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int pos = sa[mid];

            int cmp = comparePattern(text, pos, pattern);

            if (cmp == 0)
                return pos;
            if (cmp < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }
}
