# Java 9 Modules

When we create a module, we include a descriptor file that defines several aspects of our new module:

* Name – the name of our module
* Dependencies – a list of other modules that this module depends on
* Public Packages – a list of all packages we want accessible from outside the module
* Services Offered – we can provide service implementations that can be consumed by other modules
* Services Consumed – allows the current module to be a consumer of a service
* Reflection Permissions – explicitly allows other classes to use reflection to access the private members of a package


There are four types of modules in the new module system:

* System Modules – These are the modules listed when we run the list-modules command above. They include the Java SE and 
JDK modules.
* Application Modules – These modules are what we usually want to build when we decide to use Modules. They are named and 
defined in the compiled module-info.class file included in the assembled JAR.
* Automatic Modules – We can include unofficial modules by adding existing JAR files to the module path. The name of the 
module will be derived from the name of the JAR. Automatic modules will have full read access to every other module 
loaded by the path.
* Unnamed Module – When a class or JAR is loaded onto the classpath, but not the module path, it's automatically added to 
the unnamed module. It's a catch-all module to maintain backward compatibility with previously-written Java code.

## Basic
Module definition is done in file called `module-info.java` which should be placed into root folder of packages.

* Created Maven module `java9-modules-001-basic`.
  
## Requires
Example of declaring compile and runtime dependency
```java
module my.module {
    requires module.name;
}
```

* The module `java9-modules-002-requires` depend on `java9-modules-001-basic` and can invoke methods from exported classes.
However, the access to not exported classes is not present.

## Requires Static
Mainly when writing some libraries which may be depended on compile time, but not mandatory on runtime, or is optional for 
user of our library.

```java
module my.module {
    requires static module.name;
}
```

* `java9-modules-010-util` - Utility module
* `java9-modules-011-lib` - The library which requires static the `util`.
* `java9-modules-012-app` - The application which imports lib, but do not want to rely on util.

Findings:

Let's assume that dependency to `util` from `lib` is `provided`.

1. If requires is not static and Maven dependency to `util` from `app` is not set, the application compilation is 
failing with something like: `java.lang.module.FindException: Module java9Modules010Util not found, required by java9Modules011Lib`

2. If requires is not static and Maven dependency to `util` from `app` is provided, then applicaiton runs, as it can 
find the class on which it depends.

3. If requires is static and dependency to `util` from `app` is not set, then ClassNotFoundException may occur.

4. If requires is static, dependency to `util` from `app` is set and requires of `util` in app is not defined, then still
ClassNotFoundException may occur
5. If requires is static, dependency to `util` from `app` is set and requires of `util` in app is defined the all works well.

## Requires Transitive
```java
module my.module {
    requires transitive module.name;
}
```

* `java9-modules-020-util` - Defines an util method
* `java9-modules-021-lib` - Requires transitive the `util` which allow `app` module to not define requires to `util`.
* `java9-modules-022-app` - Requires `lib`, but because the `util` is transitive it also have access to classes in `util`.

## Export to module
Some packages can be specifically exported to a module

```java
module java9Modules030Util {
    exports ermicioi.java9.modules.p030 to java9Modules031MyApp;
}
```

as result, the packages is not available in module `java9Modules032HisApp`.

## Provide service
A module can define usage of some service by:
```java
import ermicioi.java9.modules.p040.P040Service;

module java9Modules041Consumer {
    requires java9Modules040Base;
    uses P040Service;
}
```

but the implementation to be provided on runtime. The provider would have something like:
```java
import ermicioi.java9.modules.p040.P040Service;
import ermicioi.java9.modules.p042.P042ServiceImpl;

module java9Modules042Provider {
    requires java9Modules040Base;
    provides P040Service with P042ServiceImpl;
}
```

## Open to reflection
### Open everything (050)
If module is not open for reflection of private members, it seems that to public members the access is allowed, then
an exception like `Exception in thread "main" java.lang.reflect.InaccessibleObjectException` would be thrown.

Example of closed module:
```java
module java9Modules050X {
//open module java9Modules050X {
    exports ermicioi.java9.modules._050;
}
```

Example of open module:
```java
open module java9Modules050X {
    exports ermicioi.java9.modules._050;
}
```

### Open package (060)
Open a concrete package for reflection:
```java
module java9Modules060X {
    exports ermicioi.java9.modules._060.open;
    exports ermicioi.java9.modules._060.close;

    opens ermicioi.java9.modules._060.open;
}
```

### Open package to (x)
Opens a package to a module
```java
module java9Modules060X {
    exports ermicioi.java9.modules._060.open;
    exports ermicioi.java9.modules._060.close;

    opens ermicioi.java9.modules._060.open to java9Modules061X;
}
```

## Resources
* https://www.oracle.com/corporate/features/understanding-java-9-modules.html
* https://www.baeldung.com/java-9-modularity