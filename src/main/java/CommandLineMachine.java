import command.CommandRouter;
import component.CommandRouterFactory;
import component.DaggerCommandRouterFactory;
import scheduler.EndDayScheduler;
import utils.CommandConstant;

import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;

/**
 * @author : Hau Nguyen
 * @created : 6/1/22
 **/

public class CommandLineMachine {
    public static void main(String[] args) {
        System.out.println("<<<< Please insert money >>>>");
        Scanner scanner = new Scanner(System.in);
        Integer counter = 0;

        CommandRouterFactory commandRouterFactory = DaggerCommandRouterFactory.create();
        CommandRouter commandRouter = commandRouterFactory.router();
        while (scanner.hasNextLine()) {
            if (counter == 0) {
                Double money = scanner.nextDouble();
                if (!money.isNaN()) {
                    System.out.println("Your current money is: " + money);
                    counter++;
                    commandRouter.route(CommandConstant.INSERT_MONEY_COMMAND_KEY);
                } else {
                    System.out.println("Money with type not supported");
                }
            } else {
                commandRouter.route(scanner.nextLine());
            }
        }

        // Cronjob time setting
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        // at the end of the day
        Timer timer = new Timer();
        timer.schedule(new EndDayScheduler(), calendar.getTime());
    }
}
