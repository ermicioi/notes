import ermicioi.java9.modules.p040.P040Service;
import ermicioi.java9.modules.p042.P042ServiceImpl;

module java9Modules042Provider {
    requires java9Modules040Base;
    provides P040Service with P042ServiceImpl;
}