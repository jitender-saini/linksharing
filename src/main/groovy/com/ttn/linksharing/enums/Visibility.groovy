package com.ttn.linksharing.enums

enum Visibility {

    PUBLIC,
    PRIVATE

    static Visibility toEnum(String visibility) {
        if (visibility.equalsIgnoreCase("PUBLIC")) {
            PUBLIC
        } else {
            PRIVATE
        }

    }

}