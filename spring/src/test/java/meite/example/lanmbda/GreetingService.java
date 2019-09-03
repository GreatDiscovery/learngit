package meite.example.lanmbda;

/**
 * @author gavin
 * @date 2019/1/23 13:11
 */
@FunctionalInterface
public interface GreetingService {
    void saySomething(String message);
}

class TestLanmbda {
    public static void main(String[] args) {
        GreetingService greetingService = message -> System.out.println("hello" + message);
        greetingService.saySomething("gavin");
    }
}