package ermicioi.java9.modules.p002;

import ermicioi.java9.modules.p001.pub.P001PubSimpleService;

public class Main {

    public static void main(String[] args) {
        P001PubSimpleService.sayHi();
//        P001PrvSimpleService.sayHi(); The service from not exported package is not available.
    }

}
