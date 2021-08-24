package ermicioi.java9.modules.p11;

import ermicioi.java9.modules.p010.P010Util;

public class P011Lib {

    public static void doRoutine() {
        System.out.println("Executing routine ...");

        try {
            P010Util obj = (P010Util) Class.forName("ermicioi.java9.modules.p010.P010Util")
                    .getDeclaredConstructor()
                    .newInstance();
            obj.sayHi();
        } catch (Exception e) {
            System.err.println("Util not available");
        }

        System.out.println("Done.");
    }

}
