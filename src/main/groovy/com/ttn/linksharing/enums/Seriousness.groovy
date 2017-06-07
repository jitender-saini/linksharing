package com.ttn.linksharing.enums

enum Seriousness {

    SERIOUS('Serious'),
    VERY_SERIOUS('Very Serious'),
    CASUAL('Casual')

    String displayName

    Seriousness(String displayName) {
        this.displayName = displayName
    }

    static Seriousness toEnum(String seriousness) {
        if (seriousness.equalsIgnoreCase("SERIOUS")) {
            SERIOUS
        } else if (seriousness.equalsIgnoreCase("VERY_SERIOUS")) {
            VERY_SERIOUS
        } else {
            CASUAL
        }
    }
}