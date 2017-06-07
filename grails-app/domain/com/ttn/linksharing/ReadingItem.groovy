package com.ttn.linksharing

import java.sql.Date

class ReadingItem {

    User user
    Resource resource
    Boolean isRead
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user: User, resource: Resource]

    static constraints = {
        user nullable: false, blank: false
        resource nullable: false, blank: false, unique: 'user'
        isRead nullable: false, blank: false
    }

    static List getUnReadItems(User user, Map params) {
        List list = createCriteria().list(params) {
            projections {
                property('resource')
            }
            eq('user', user)
            eq('isRead', false)
            order("dateCreated", "desc")
        }
        return list
    }

    static int getUnReadItemCount(User user) {
        int count = countByUserAndIsRead(user, false)
        return count
    }


}
