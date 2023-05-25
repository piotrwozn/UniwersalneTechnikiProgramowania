/**
 *
 *  @author Woźnicki Piotr SO0139
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
  public static void main(String[] args) throws IOException {
    URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    List<String> words = reader.lines().collect(Collectors.toList());

    // Tworzenie mapy z anagramami
    Map<String, List<String>> anagrams = words.stream()
            .collect(Collectors.groupingBy(Main::sortChars));

    // Znajdowanie maksymalnej liczby anagramów
    int maxAnagrams = anagrams.values().stream()
            .mapToInt(List::size)
            .max()
            .getAsInt();

    // Wypisywanie słów z maksymalną liczbą anagramów
    anagrams.values().stream()
            .filter(list -> list.size() == maxAnagrams)
            .forEach(list -> {
              System.out.print(list.get(0) + " ");
              for (int i = 1; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
              }
              System.out.println();
            });
  }

  private static String sortChars(String word) {
    char[] chars = word.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }
}
