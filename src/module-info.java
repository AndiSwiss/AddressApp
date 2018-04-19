/**
 * This file is needed for the app to run in Java 9, since in Java 9, the concept of modules was introduced, see:
 * https://www.jetbrains.com/help/idea/getting-started-with-java-9-module-system.html
 * https://blog.jetbrains.com/idea/2017/03/support-for-java-9-modules-in-intellij-idea-2017-1/
 * (there are similar posts for other IDE's)
 * <p>
 * The app works fine for the first four parts of the tutorial.
 * But part 5 of the tutorial: http://code.makery.ch/library/javafx-8-tutorial/part5/
 * doesn't work, since it uses JAXB with the following imports:
 * <p>
 * import javax.xml.bind.annotation.XmlElement;
 * import javax.xml.bind.annotation.XmlRootElement;
 * <p>
 * Both import statements work perfectly in Java 8, but in Java 9, they are not anymore accessible,
 * because of the new module-system in Java 9.
 * By adding this file 'module-info.java' to the /src-folder, with the 'requires' and  the 'opens'
 * statements, this app works again in Java 9.
 */
module CodeMakery.AdressApp {

    // java.xml.bind is deprecated in Java 9. To dismiss the error-warning, you
    // just need to add the following comment just before 'requires java.xml.bind':

    //noinspection removal
    requires java.xml.bind;

    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;

    requires java.prefs;


    // All the following lines are necessary in order for the code to work.
    // Otherwise, there will be many errors, such as:
    // Caused by: java.lang.IllegalAccessException: class javafx.fxml.FXMLLoader$ValueElement (in module javafx.fxml) cannot access class ch.makery.address.view.PersonOverviewController (in module CodeMakery.AdressApp) because module CodeMakery.AdressApp does not export ch.makery.address.view to module javafx.fxml
    // -> I first tried with 'exports' instead of 'opens', but that just solved some of the errors
    // By using 'opens', all errors are gone and the app behaves perfectly in java 9.0.4!

    opens ch.makery.address;
    opens ch.makery.address.view;
    opens ch.makery.address.model;
    opens ch.makery.address.util;

}