package com.sakanal.LeetCode;

public class 句子中的有效单词数 {

    public static int countValidWords(String sentence) {
        String regex = "([a-z]*|([a-z]+-[a-z]+))[!.,]?";
        int ans = 0;
        for (String s : sentence.split(" ")) {
            if (s.length() > 0 && s.matches(regex)) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String sentence="cat and dog";
        int ans=countValidWords(sentence);
        System.out.println("ans = " + ans);
    }
}
