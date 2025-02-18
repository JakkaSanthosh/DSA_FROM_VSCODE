public class MyClass {
    static void staticMethod() {
        System.out.println("This is a static method.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Call static method directly
        MyClass.staticMethod(); // No need to create an object
    }
}
