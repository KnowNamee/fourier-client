package com.fourier.application.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.regex.Pattern;

public class CredentialsValidator {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isEmailValid(String email) {
        return Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
        ).asPredicate().test(email) && email.length() <= 255;
    }

    /** Method {@code isPasswordStructureValid} validates {@code password} structure
     * using regular expression.
     *
     * Regular expression contains :
     * <p>(?=.*[0-9])  - password must contain at least 1 digit</p>
     * <p>(?=.*[_])    - password must contain at least 1 symbol '_'</p>
     * <p><(?=.*[a-z]) - password must contain at least 1 symbol of [a-z]</p>
     * <p>(?=.*[A-Z])  - password must contain at least 1 symbol of [A-Z]</p>
     * <p>[0-9a-zA-Z_] - password contains only symbols [0-9a-zA-Z_]</p>
     * <p>      {6,30} - password length from 6 to 30</p>
     *
     * @param password password to validate
     * @return {@code true} if the input argument is valid, otherwise {@code false}
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isPasswordValid(String password) {
        return Pattern.compile(
                "(?=.*[0-9])(?=.*[_])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z_]{6,30}"
        ).asPredicate().test(password);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isUsernameValid(String username) {
        return Pattern.compile(
                "[0-9a-zA-Z_]{3,20}"
        ).asPredicate().test(username);
    }

}
