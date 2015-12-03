package com.tianzh.cm.util.weight;


import com.tianzh.cm.service.model.ThPayProperty;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-26
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class WeightUtil {

    public static ThPayProperty getThPayProperty(ArrayList<ThPayProperty> usefullThPay) {
        int sumWeight = 0;

        int[] taskWeights = new int[usefullThPay.size()];

        for (int i = 0; i < usefullThPay.size(); i++) {
            taskWeights[i] = (int) usefullThPay.get(i).getWeight();
            sumWeight += taskWeights[i];
        }

        Random random = new Random();
        int temp = random.nextInt(sumWeight);
        int sum = 0;

        for (int i = 0; i < taskWeights.length; i++) {
            sum += taskWeights[i];

            if (temp <= sum) {
                return usefullThPay.get(i);
            }
        }

        return usefullThPay.get(0);
    }


    public static void main(String[] args) {

        //[{"weight":60.0,"thPayId":"4"},{"weight":60.0,"thPayId":"3"},{"weight":60.0,"thPayId":"2"},{"weight":60.0,"thPayId":"1"}]
        ThPayProperty thPayProperty1 = new ThPayProperty();
        ThPayProperty thPayProperty2 = new ThPayProperty();
        ThPayProperty thPayProperty3 = new ThPayProperty();
        ThPayProperty thPayProperty4 = new ThPayProperty();

        thPayProperty1.setThPayId("4");
        thPayProperty1.setWeight(60f);

        thPayProperty2.setThPayId("3");
        thPayProperty2.setWeight(60f);

        thPayProperty3.setThPayId("2");
        thPayProperty3.setWeight(60f);

        thPayProperty4.setThPayId("1");
        thPayProperty4.setWeight(10f);


        ArrayList<ThPayProperty> thPayProperties = new ArrayList<ThPayProperty>();

        thPayProperties.add(thPayProperty1);
        thPayProperties.add(thPayProperty2);
        thPayProperties.add(thPayProperty3);
        thPayProperties.add(thPayProperty4);

        int count1 = 0, count2 = 0, count3 = 0, count4 = 0;

        for (int i = 0; i < 100000; i++) {
            ThPayProperty thPayProperty = getThPayProperty(thPayProperties);

            if (thPayProperty.getThPayId().equals("1")) count1++;
            if (thPayProperty.getThPayId().equals("2")) count2++;
            if (thPayProperty.getThPayId().equals("3")) count3++;
            if (thPayProperty.getThPayId().equals("4")) count4++;
        }


        System.out.println("count1 = " + count1);
        System.out.println("count2 = " + count2);
        System.out.println("count3 = " + count3);
        System.out.println("count4 = " + count4);
    }
}
