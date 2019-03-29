package meite.example.lanmbda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gavin
 * @date 2019/3/12 8:56
 */
public class TestStream {

    @Test
    public void test1() {
        List<String> strings = Arrays.asList("Hello", "", "gavin", "AHBEO");
        strings.stream().filter(string -> !string.isEmpty()).map(i -> i.toLowerCase()).limit(2).sorted().forEach(System.out::println);
        strings = strings.stream().filter(string -> !string.isEmpty()).map(i -> i.toLowerCase()).limit(2).sorted().collect(Collectors.toList());
        System.out.println(strings);
    }
}
