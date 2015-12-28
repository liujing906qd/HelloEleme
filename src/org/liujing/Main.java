package org.liujing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    /**
     * 目标价格(店铺的折扣的目标价格)
     */
    private static final int desPrice = 25;

    /**
     * 达到目标价格后的折扣价格
     */
    private static final int discountPrice = 6;

    /**
     * 米饭价格
     */
    private static final int ricePrice = 0;

    /**
     * 按照菜的份数计算米饭(即:一份菜自动计算一份饭)
     */
    private static final boolean isRiceCountSameByMenuSize = false;


    public static void main(String[] param) {
        List<List<Integer>> resultOrderList = new ArrayList<>();
        List<Integer> menuPriceList = new ArrayList<>();

        for (int i = 0; i < param.length; i++) {
            menuPriceList.add(Integer.parseInt(param[i]));
        }

        menuPriceList.add(26);
        menuPriceList.add(26);
        menuPriceList.add(21);
        menuPriceList.add(4);
        menuPriceList.add(23);
        menuPriceList.add(2);
        menuPriceList.add(isRiceCountSameByMenuSize ? menuPriceList.size() * ricePrice : 0);
        menuPriceList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        getListForMinDesPrice(menuPriceList, resultOrderList);

        //落单分组(即:没有和其他订单匹配成功,但是根据距离目标价格还差几块钱时,可以通过饮料拼单)
        int singeOrderList = 0;
        for (int price : menuPriceList) {
            singeOrderList += price;
        }

        System.out.println("饿了么目标价 = " + desPrice);
        System.out.println("饿了么折扣价 = " + discountPrice);
        System.out.println("米饭数量和菜的数量一致吗？ = " + isRiceCountSameByMenuSize);
        System.out.println("----------------计算结果----------------");
        System.out.println("最优订单分组 = " + resultOrderList);
        System.out.println("落单分组 = " + menuPriceList);
        System.out.println("落单分组再多加" + (desPrice - singeOrderList) + "元享受折扣价");

    }

    private static void getListForMinDesPrice(List<Integer> menuPriceList, List<List<Integer>> resultOrderList) {
        for (int i = 0; i < menuPriceList.size(); i++) {
            Integer price1 = menuPriceList.get(i);
            if (price1 >= desPrice) {
                menuPriceList.remove(price1);

                List<Integer> list = new ArrayList<>();
                list.add(price1);
                resultOrderList.add(list);

                getListForMinDesPrice(menuPriceList, resultOrderList);
                break;
            }
        }
        for (int j1 = 0; j1 < menuPriceList.size(); j1++) {
            for (int j = j1 + 1; j < menuPriceList.size(); j++) {
                Integer price1 = menuPriceList.get(j1);
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
        for (int k1 = 0; k1 < menuPriceList.size(); k1++) {
            for (int k2 = k1 + 1; k2 < menuPriceList.size(); k2++) {
                for (int k = k2 + 1; k < menuPriceList.size(); k++) {
                    Integer price1 = menuPriceList.get(k1);
                    Integer price2 = menuPriceList.get(k2);
                    Integer price3 = menuPriceList.get(k);
                    if (price1 + price2 + price3 >= desPrice) {
                        menuPriceList.remove(price1);
                        menuPriceList.remove(price2);
                        menuPriceList.remove(price3);

                        List<Integer> list = new ArrayList<>();
                        list.add(price1);
                        list.add(price2);
                        list.add(price3);
                        resultOrderList.add(list);

                        getListForMinDesPrice(menuPriceList, resultOrderList);
                        break;
                    }

                }
            }
        }
        for (int l1 = 0; l1 < menuPriceList.size(); l1++) {
            for (int l2 = l1 + 1; l2 < menuPriceList.size(); l2++) {
                for (int l3 = l2 + 1; l3 < menuPriceList.size(); l3++) {
                    for (int l = l3 + 1; l < menuPriceList.size(); l++) {
                        Integer price1 = menuPriceList.get(l1);
                        Integer price2 = menuPriceList.get(l2);
                        Integer price3 = menuPriceList.get(l3);
                        Integer price4 = menuPriceList.get(l);
                        if (price1 + price2 + price3 + price4 >= desPrice) {
                            menuPriceList.remove(price1);
                            menuPriceList.remove(price2);
                            menuPriceList.remove(price3);
                            menuPriceList.remove(price4);

                            List<Integer> list = new ArrayList<>();
                            list.add(price1);
                            list.add(price2);
                            list.add(price3);
                            list.add(price4);
                            resultOrderList.add(list);

                            getListForMinDesPrice(menuPriceList, resultOrderList);
                            break;
                        }
                    }
                }
            }
        }
    }
}