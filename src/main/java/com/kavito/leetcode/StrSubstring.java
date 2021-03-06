package com.kavito.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 实例：
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * @Author: kavito
 * @Date: 2019/9/29 3:53 下午
 */
public class StrSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

}
