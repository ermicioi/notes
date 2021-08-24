package ermicioi.java9.modules.p041;

import ermicioi.java9.modules.p040.P040Service;

import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {
        ServiceLoader.load(P040Service.class)
                .findFirst()
                .orElseThrow()
                .sayHi();
    }

}
