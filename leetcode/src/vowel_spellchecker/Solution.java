package vowel_spellchecker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author xieziwei99
 * 2019-10-11
 */
public class Solution {
    public static void main(String[] args) {
        String[] wordlist = {"KiTe", "kite", "hare", "Hare"};
        String[] queries = {"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti",
                "keet", "keto"};
        String[] spellchecker = new Solution().spellchecker(wordlist, queries);
        assert Arrays.equals(spellchecker, new String[]{"kite", "KiTe", "KiTe", "Hare", "hare", "", "",
                "KiTe", "", "KiTe"});
    }

    /**
     * 将单词中的元音字母用同一个特殊字符如`*`表示，并全部转换为小写
     *
     * @param s 输入字符串
     * @return 用 ‘*’代替元音字母后的字符串
     */
    private String replaceVowel(String s) {
        String ret = s.toLowerCase();
        for (char c : Arrays.asList('a', 'e', 'i', 'o', 'u')) {
            ret = ret.replace(c, '*');
        }
        return ret;
    }

    private String[] spellchecker(String[] wordlist, String[] queries) {
        String[] ret = new String[queries.length];
        HashSet<String> check1 = new HashSet<>();   // 完全匹配
        HashMap<String, String> check2 = new HashMap<>();   // 大小写不一致
        HashMap<String, String> check3 = new HashMap<>();   // 将元音字母更换
        for (String s : wordlist) {
            check1.add(s);
            check2.putIfAbsent(s.toLowerCase(), s);
            check3.putIfAbsent(replaceVowel(s), s);
        }
        for (int i = 0; i < queries.length; i++) {
            if (check1.contains(queries[i])) {
                ret[i] = queries[i];
            } else if (check2.containsKey(queries[i].toLowerCase())) {
                ret[i] = check2.get(queries[i].toLowerCase());
            } else {
                ret[i] = check3.getOrDefault(replaceVowel(queries[i]), "");
            }
        }
        return ret;
    }
}
