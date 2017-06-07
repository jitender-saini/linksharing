package linksharing

import com.ttn.linksharing.UserService

class MailTriggerJob {
    UserService userService
    static triggers = {
//      simple name: 'mySimpleTrigger', startDelay: 60000,repeatInterval: 5000l // execute job once in 5 seconds

        // at 8:00 AM ONLY ON Monday
        cron name: 'mySimpleTrigger', startDelay: 0, cronExpression: '0 0 8 ? * 2'
    }

    def group = "MyGroup"
    def description = "Example job with Simple Trigger"

    def execute() {
        userService.sendUnreadItemsEmail()
    }
}
