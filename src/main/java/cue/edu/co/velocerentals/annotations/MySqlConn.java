package cue.edu.co.velocerentals.annotations;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

// Indicates that this is a qualifier annotation for dependency injection.
@Qualifier
// Specifies that this annotation will be available at runtime.
@Retention(RetentionPolicy.RUNTIME)
// Specifies the targets where this annotation can be applied.
@Target({ METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR})
// Declares the annotation named @MySqlConn.
public @interface MySqlConn {
}
