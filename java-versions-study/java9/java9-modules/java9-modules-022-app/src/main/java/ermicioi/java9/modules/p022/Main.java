package ermicioi.java9.modules.p022;

import ermicioi.java9.modules.p020.P020Util;
import ermicioi.java9.modules.p021.P021Routine;

public class Main {

    public static void main(String[] args) {
        P021Routine.doRoutine();
        P020Util.sayHi(); // This call is possible only because of transitive require
    }

}
