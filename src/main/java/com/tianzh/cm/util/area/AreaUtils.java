package com.tianzh.cm.util.area;

import java.util.HashMap;

/**
 * Created by pig on 2015-10-16.
 */
public class AreaUtils {
    static HashMap<String, Integer> provinceMap = new HashMap<String, Integer>();

    static {
        provinceMap.put("北京", 1);
        provinceMap.put("天津", 2);
        provinceMap.put("重庆", 3);
        provinceMap.put("上海", 4);
        provinceMap.put("河北", 5);
        provinceMap.put("山西", 6);
        provinceMap.put("辽宁", 7);
        provinceMap.put("吉林", 8);
        provinceMap.put("黑龙江", 9);
        provinceMap.put("江苏", 10);
        provinceMap.put("浙江", 11);
        provinceMap.put("安徽", 12);
        provinceMap.put("福建", 13);
        provinceMap.put("江西", 14);
        provinceMap.put("山东", 15);
        provinceMap.put("河南", 16);
        provinceMap.put("湖北", 17);
        provinceMap.put("湖南", 18);
        provinceMap.put("广东", 19);
        provinceMap.put("海南", 20);
        provinceMap.put("四川", 21);
        provinceMap.put("贵州", 22);
        provinceMap.put("云南", 23);
        provinceMap.put("陕西", 24);
        provinceMap.put("甘肃", 25);
        provinceMap.put("青海", 26);
        provinceMap.put("台湾", 27);
        provinceMap.put("内蒙古", 28);
        provinceMap.put("广西", 29);
        provinceMap.put("宁夏", 30);
        provinceMap.put("新疆", 31);
        provinceMap.put("西藏", 32);
        provinceMap.put("香港", 33);
        provinceMap.put("澳门", 34);
    }


    public static Integer getProvinceId(String provinceName) {
        return provinceMap.get(provinceName) == null ? 0 : provinceMap.get(provinceName);
    }


    public static void main(String[] args) {
        String province = "内蒙古自治区";

        if (province.contains("省")) {
            province = province.substring(0, province.lastIndexOf("省"));
        } else if (province.endsWith("壮族自治区")) {
            province = province.substring(0, province.lastIndexOf("壮族自治区"));
        } else if (province.endsWith("回族自治区")) {
            province = province.substring(0, province.lastIndexOf("回族自治区"));
        } else if (province.endsWith("维吾尔自治区")) {
            province = province.substring(0, province.lastIndexOf("维吾尔自治区"));
        } else if (province.endsWith("市")) {
            province = province.substring(0, province.lastIndexOf("市"));
        } else if (province.endsWith("自治区")) {
            province = province.substring(0, province.lastIndexOf("自治区"));
        }

        System.out.println(province);
    }
}
