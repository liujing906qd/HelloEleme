package org.liujing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final int desPrice = 30;
    private static final int discountPrice = 12;

    public static void main(String[] param) {
        List<List<Integer>> resultOrderList = new ArrayList<>();
        List<Integer> menuPriceList = new ArrayList();

        for (int i = 0; i < param.length; i++) {
            menuPriceList.add(Integer.parseInt(param[i]));
        }

        menuPriceList.add(13);
        menuPriceList.add(18);
        menuPriceList.add(16);
        menuPriceList.add(22);
        menuPriceList.add(12);

        menuPriceList.add(12);
        menuPriceList.add(12);
        menuPriceList.add(12);
        menuPriceList.add(12);

        menuPriceList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        getListForMinDesPrice(menuPriceList, resultOrderList);

        int singeOrderList = 0;
        for (int price : menuPriceList) {
            singeOrderList += price;
        }

        System.out.println("饿了么目标价 = " + desPrice);
        System.out.println("饿了么折扣价 = " + discountPrice);
        System.out.println("----------------计算结果----------------");
        System.out.println("最优订单分组 = " + resultOrderList);
        System.out.println("落单分组 = " + menuPriceList);
        System.out.println("落单分组再多加" + (desPrice - singeOrderList - 12) + "元享受折扣价");

    }

    private static void getListForMinDesPrice(List<Integer> menuPriceList, List<List<Integer>> resultOrderList) {
        for (int i = 0; i < menuPriceList.size(); i++) {
            Integer price1 = menuPriceList.get(i);
            for (int j = 0; j < menuPriceList.size(); j++) {
                if (i == j)
                    continue;
                Integer price2 = menuPriceList.get(j);
                if (price1 + price2 >= desPrice) {
                    menuPriceList.remove(price1);
                    menuPriceList.remove(price2);

                    List<Integer> list = new ArrayList<>();
                    list.add(price1);
                    list.add(price2);
                    resultOrderList.add(list);

                    getListForMinDesPrice(menuPriceList, resultOrderList);
                    break;
                }
            }
        }
    }
}