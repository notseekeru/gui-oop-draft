
// NEEDED FOR JPACKAGE TO WORK PROPERLY
// This class serves as the entry point for the application when launched via jpackage.
// It simply delegates to the main method of the App class, which contains the actual application logic.
// By having this separate Launcher class, we can ensure that jpackage can find a clear entry point to start the application, while keeping the App class focused on the core functionality of the application.
// This is a common pattern when using jpackage, as it allows for better separation of concerns and can help avoid issues with class loading or other complexities that may arise when jpackage tries to launch the application directly from the main class.
// Note: Ensure that the main class specified in the jpackage command points to this Launcher class (com.mycompany.mavenproject1.Launcher) so that jpackage can correctly start the application.
package main;

public class Launcher {

    public static void main(String[] args) {
        App.main(args);
    }
}
