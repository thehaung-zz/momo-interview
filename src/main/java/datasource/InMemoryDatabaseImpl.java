package datasource;

import lombok.Data;
import model.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import utils.Constant;
import utils.Util;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

/**
 * @author : Hau Nguyen
 * @created : 6/1/22
 **/

@Singleton
@Data
public class InMemoryDatabaseImpl implements InMemoryDatabase {

    private int LIMIT_BUDGET_TODAY = 50000;

    private final int LUCKY_RATE_PERCENT = 10;

    private int WIN_RATE_PENDING_PERCENT = 0;

    private final int CONSECUTIVE_PURCHASES_COUNT = 3;

    private double clientMoney = 0;

    private double currentMoney = 0;

    private int countSameType = 0;

    private String previousProductKey = "";

    private Map<String, Integer> productKeyConsecutive = new HashMap<>();

    private Order currentOrder = null;

    private List<Order> orders = new ArrayList<>();

    private List<Session> sessions = new ArrayList<>();

    private List<PromotionHistory> promotionHistories = new ArrayList<>();

    /**
     * Mock storage refesh when program exit
     */
    private Map<String, Cash> cashMap = new HashMap<>();

    private Map<String, Product> productMap = new HashMap<>();


    @Inject
    public InMemoryDatabaseImpl() {
        // initialize product data
        productMap.put(Constant.PRODUCT_PEPSI, new Product(1L, Constant.PRODUCT_PEPSI, 10000.00, 50));
        productMap.put(Constant.PRODUCT_COKE, new Product(2L, Constant.PRODUCT_COKE, 10000.00, 100));
        productMap.put(Constant.PRODUCT_SODA, new Product(3L, Constant.PRODUCT_SODA, 20000.00, 150));

        // initialize cash data
        cashMap.put(Constant.CASH_KEY_10000VND, new Cash(1L, Constant.CASH_10000VND, 200));
        cashMap.put(Constant.CASH_KEY_20000VND, new Cash(1L, Constant.CASH_20000VND, 150));
        cashMap.put(Constant.CASH_KEY_50000VND, new Cash(1L, Constant.CASH_50000VND, 100));
        cashMap.put(Constant.CASH_KEY_100000VND, new Cash(1L, Constant.CASH_100000VND, 50));
        cashMap.put(Constant.CASH_KEY_200000VND, new Cash(1L, Constant.CASH_200000VND, 25));
    }

    @Override
    public void order(String productKey) {
        Product product = productMap.get(productKey);
        if (ObjectUtils.isEmpty(product)) {
            System.out.println(String.format("Product key \"%s\" not found. please try again.", productKey));
            return;
        }

        if (this.currentMoney < product.getPrice()) {
            System.out.println(String.format("Product key \"%s\" not found. please try again.", productKey));
            return;
        }

        List<Product> products = this.currentOrder.getProducts();
        // check type
        if (this.previousProductKey.equalsIgnoreCase(product.getName())) {
            countSameType++;
            if (countSameType >= this.CONSECUTIVE_PURCHASES_COUNT) {
                this.productKeyConsecutive.put(
                        this.previousProductKey,
                        this.productKeyConsecutive.getOrDefault(this.previousProductKey, 0) + 1
                );
                this.countSameType = 0;
            }
        } else {
            this.previousProductKey = product.getName();
        }
        this.currentMoney -= product.getPrice();
        products.add(product);
        this.currentOrder.setProducts(products);
    }

    @Override
    public void insert(Double money) {
        String cashKey = getCashKeyByMoney(money);


        if (getCurrentCash() < money) {
            System.out.println(String.format("Cash not engouh for refund to this money type"));
            return;
        }

        if (StringUtils.isBlank(cashKey)) {
            System.out.println(String.format("Money \"%f\" is not supported. please try again.", money));
            return;
        }

        Cash cash = cashMap.get(cashKey);
        cash.setQuantity(cash.getQuantity() + 1);

        cashMap.put(cashKey, cash);
        this.currentMoney = money;
        this.clientMoney = money;
    }

    @Override
    public Session createSession() {
        Session session = Session.builder().build();

        sessions.add(session);
        return session;
    }

    @Override
    public void endSession() {
        Session session = getCurrentSession();
        session.setIsEnded(true);

        sessions.set(sessions.size() - 1, session);
        boolean isCanRefund = this.refund(currentMoney, true);
        if (!isCanRefund) {
            System.out.println("Sorry this Machine not enough to refund your payment. Sorry and try again with lower money type!");
            return;
        }
        releaseProduct();
        refund(currentMoney, false);
        resetAllData();
    }


    Session getCurrentSession() {
        // current is the last value from session array
        return sessions.get(sessions.size() - 1);
    }

    private String getCashKeyByMoney(Double money) {
        String moneyString = String.valueOf(money);

        switch (moneyString) {
            case "10000.00":
                return Constant.CASH_KEY_10000VND;
            case "20000.00":
                return Constant.CASH_KEY_20000VND;
            case "50000.00":
                return Constant.CASH_KEY_50000VND;
            case "100000.00":
                return Constant.CASH_KEY_100000VND;
            case "200000.00":
                return Constant.CASH_KEY_200000VND;
            default:
                return null;
        }
    }


    private boolean refund(Double money, boolean tracking) {
        Map<String, Integer> moneyCounter = new HashMap<>();

        while (money > 0) {
            if (money > Constant.CASH_100000VND && getCashQuantity(Constant.CASH_KEY_100000VND) > 1) {
                money -= Constant.CASH_10000VND;
                moneyCounter.put(Constant.CASH_KEY_100000VND, moneyCounter.getOrDefault(Constant.CASH_KEY_100000VND, 0) + 1);
            } else if (money < Constant.CASH_100000VND && money >= Constant.CASH_50000VND && getCashQuantity(Constant.CASH_KEY_50000VND) > 1) {
                money -= Constant.CASH_50000VND;
                moneyCounter.put(Constant.CASH_KEY_50000VND, moneyCounter.getOrDefault(Constant.CASH_KEY_50000VND, 0) + 1);
            } else if (money < Constant.CASH_50000VND && money > Constant.CASH_20000VND && getCashQuantity(Constant.CASH_KEY_20000VND) > 1) {
                money -= Constant.CASH_20000VND;
                moneyCounter.put(Constant.CASH_KEY_20000VND, moneyCounter.getOrDefault(Constant.CASH_KEY_20000VND, 0) + 1);
            } else if (getCashQuantity(Constant.CASH_KEY_100000VND) > (money / 10000)) {
                money -= Constant.CASH_10000VND;
                moneyCounter.put(Constant.CASH_KEY_100000VND, moneyCounter.getOrDefault(Constant.CASH_KEY_10000VND, 0) + 1);
            } else {
                return false;
            }
        }

        if (tracking == false) {
            moneyCounter.forEach((s, integer) -> {
                Cash cash = cashMap.get(s);
                cash.setQuantity(cash.getQuantity() - integer);

                cashMap.put(s, cash);
            });
        }

        return true;
    }

    private Double getCurrentCash() {
        Double total = 0.0;
        for (Cash cash : cashMap.values()) {
            total += cash.getPrice() * cash.getQuantity();
        }
        return total;
    }

    private int getCashQuantity(String cashKey) {
        return cashMap.get(cashKey).getQuantity();
    }

    private void handlePromotion() {
        List<Product> products = this.currentOrder.getProducts();

        if (!this.productKeyConsecutive.isEmpty()) {
            this.productKeyConsecutive.forEach((s, integer) -> {
               for (int i = 0; i <= integer; i++) {
                   products.add(productMap.get(s));
               }
            });
        }
    }


    private void releaseProduct() {
        List<Product> products = this.currentOrder.getProducts();

        // put promotion into orders
        handlePromotion();

        if (!isEnoughQuantity()) {
            refund(this.clientMoney, false);
            return;
        }

        // fully release
        for (Product product : products) {
            Product p = productMap.get(product.getName());

            p.setQuantity(p.getQuantity() - 1);
            productMap.put(p.getName(), p);
        }
        return;
    }

    private boolean isEnoughQuantity() {
        List<Product> products = this.currentOrder.getProducts();

        for (Product product : products) {
            boolean condition = productMap.get(product.getName()).getQuantity() > 0;
            if (!condition) {
                return false;
            }
        }

        return true;
    }


    private void resetAllData() {
        // reset
        this.currentMoney = 0;
        this.clientMoney = 0;
        this.countSameType = 0;
        this.currentOrder = null;
    }

}
