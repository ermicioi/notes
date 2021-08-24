package ermicioi.java9.modules._051;

import ermicioi.java9.modules._050.SomeService;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class Main051 {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        final SomeService service = new SomeService();

        Stream.of(service.getClass().getDeclaredMethods())
                .filter(m -> "sayHi".equals(m.getName()))
                .findFirst()
                .ifPresent(m -> {
                    m.setAccessible(true);
                    try {
                        m.invoke(service);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

}
