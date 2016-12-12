package com.hado.indicatorlibrary;

/**
 * Created by Hado on 11-Dec-16.
 */

public class PagesLessException extends Exception {
    @Override
    public String getMessage() {
        return "Pages must equal or larger than 2";
    }
}
