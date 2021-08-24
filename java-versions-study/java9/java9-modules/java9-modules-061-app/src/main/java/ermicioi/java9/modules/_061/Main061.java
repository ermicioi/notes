package ermicioi.java9.modules._061;

import ermicioi.java9.modules._060.close.ClosedService;
import ermicioi.java9.modules._060.open.OpenService;

import java.util.stream.Stream;

public class Main061 {

    public static void main(String[] args) {
        doo(OpenService.class, new OpenService());
        doo(ClosedService.class, new ClosedService());
    }

    private static void doo(final Class<?> c, final Object instance) {
        Stream.of(c.getDeclaredMethods())
                .filter(m -> "sayHi".equals(m.getName()))
                .findFirst()
                .ifPresent(m -> {
                    m.setAccessible(true);
                    try {
                        m.invoke(instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

}
