package algo;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {


  private static int lengthOfLongestSubstring(String s) {

    Deque<Character> window = new ArrayDeque<>();

      int maxSize =  0;

      final int leftPointer = 0;


      for ( Character c : s.toCharArray()){

        if (!window.contains(c)) window.add(c);
        else {

            Iterator<Character> iteratorVals = window.iterator();

            while (iteratorVals.hasNext()) if (iteratorVals.next() != c) window.remove();



        }
        maxSize = Math.max(window.size(), maxSize);
    }


    return maxSize;
  }

  public static void main(String[] args) {

    final String s = "dvdf";

    System.out.println(lengthOfLongestSubstring(s));
  }
}
