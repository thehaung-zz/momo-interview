package scheduler;

import datasource.InMemoryDatabaseImpl;

import java.util.TimerTask;

/**
 * @author : Hau Nguyen
 * @created : 6/2/22
 **/

public class EndDayScheduler extends TimerTask {

    InMemoryDatabaseImpl inMemoryDatabase;

    @Override
    public void run() {
        int limitBudgetToday = inMemoryDatabase.getLIMIT_BUDGET_TODAY();

        if (limitBudgetToday > 0) {
            inMemoryDatabase.setWIN_RATE_PENDING_PERCENT(50);
        }
    }
}
