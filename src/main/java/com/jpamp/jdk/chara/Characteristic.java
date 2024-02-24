package com.jpamp.jdk.chara;

import com.jpamp.jdk.entity.DataRecord;
import com.jpamp.jdk.entity.SelectRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author xyb
 * @Description
 * @Date 2022/4/1 22:23
 */
public class Characteristic {


    public static void main(String[] args) {
        streamFun();
    }
    /*************9**************/
    /**
     * map特性的修改
     */
    public static void mapFun (){
        //java6及以前
        Map<String,String> map7 = new HashMap<String,String>();
        //java7和8 <>没有了数据类型
        Map<String,String> map8 = new HashMap<>();
        //java9 添加了匿名内部类的功能 后面添加了大括号{}  可以做一些细节的操作
        Map<String,String> map9 = new HashMap<>(){};
    }

    /**
     * 异常处理try升级
     */
    public static void tryFun (){
        //java7及以前
        InputStreamReader reader = null;
        try{
            reader = new InputStreamReader(System.in);
            reader.read();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //java7和8 <>没有了数据类型
        try(InputStreamReader reader8 =new InputStreamReader(System.in)){
            reader8.read();
        }catch (IOException e){
            e.printStackTrace();
        }
        //java9
        InputStreamReader reader9 =new InputStreamReader(System.in);
        try(reader9){
            reader9.read();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     *  JDK8之前 String _ ="hello";  这样的标识符可以用，JDK9就用不到。
     *  String 底层的数据结构有 char[] 改为byte[] 优化节省空间
     */
    /**
     *  Stream 新添加了4个方法，takeWhile dropWhile ofNullable iterate
     */
    public static void streamFun (){
        List<Integer> list = Arrays.asList(66, 45, 99, 23, 66);
        // takeWhile 遇到第一个不满足的就跳出
        list.stream().takeWhile(x -> x < 50)
                .forEach(System.out::print);
        System.out.println();
        list.stream().dropWhile(x -> x > 50)
                .forEach(System.out::print);
    }

    /*************14**************/
    /**
     * 允许将switch用作语句或表达式
     */
    public static void switchFun(){
        String str = "A";
        // Jdk之前的版本
        switch (str) {
            case "A":
                System.out.println("A");break;
            case "B":
                System.out.println("B");break;
            default:
                System.out.println("default");
        }
        // 新的
        switch (str) {
            case "A", "C" -> System.out.println("A || C");
            case "B" -> System.out.println("B");
            default -> System.out.println("default");
        }
        // yield用于返回值
        int score = switch (str) {
            case "A" -> 90;
            case "B" -> 80;
            default -> {
                System.out.println("default");
                yield 70;
            }
        };
    }
    /**
     * 货币格式（优化）
     */
    public static void numberFormatFun (){

    }
    /*************15**************/
    /**
     * CharSequence中添加了isEmpty默认方法
     */
    public static void emptyFun (){
        CharSequence c = "CharSequence";
        boolean b = c.isEmpty();
    }

    /**
     * TreeMap重新实现压倒性一切的如：putIfAbsent、computeIfAbsent、computeIfPresent、compute、merage
     */
    public static void treeMapFun (){
        Map<String, String> map = new TreeMap<>();
        map.putIfAbsent("123", "v213");

    }

    /**
     * 文本块
     */
    public static String textBlock(){
        String  html = """
                            <html>
                                <body> 
                                    <p>Hello, world</p> 
                                </body> 
                            </html> 
                            """;
        return html;
    }
    /**
     * 货币分组分隔符
     */
    public static void currencyFun (){
        double num = (111111.111D);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.println(nf.format(num));
        NumberFormat usnf = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(usnf.format(num));
    }
    /**
     * time用默认值覆盖本地化值
     * 旧版的输出 جمعه 1 مهٔ 2020
     * 新的输出为 جمعه ۱ آوریل ۲۰۲۲
     */
    public static void timeLocalFun (){
        String str = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .localizedBy(Locale.forLanguageTag("fa"))
                .format(LocalDate.now());
        System.out.println(str);
    }

    /**
     * 如果最小值大于最小大值，则将引发异常
     * 仅当最小值大于最大最大值时才会发生例外
     */
    public static void valueRangeFun (){
        long min = 0;
        long maxLargest = 0;
        long maxSmallest = 0;
        ValueRange vr = ValueRange.of(min, maxSmallest, maxLargest);

    }

    /*************16**************/
    /**
     * record 的用法
     * 包含 构造方法、toString()、equals() 和 hashCode()
     */
    public static void recordFun (){
        List<DataRecord> list = records();
        List<SelectRecord> selectRecordList = list.stream().map(e-> new SelectRecord(String.valueOf(e.getId()),e.getName())).collect(Collectors.toList());
        selectRecordList.forEach(System.out::println);
    }
    /**
     * Stream新增toList方法
     */
    public static void streamToListFun (){
        List<DataRecord> list = records();
        List<SelectRecord> list1 = list.stream().map(e-> new SelectRecord(String.valueOf(e.getId()),e.getName())).collect(Collectors.toList());
        List<SelectRecord> list2 = list.stream().map(e-> new SelectRecord(String.valueOf(e.getId()),e.getName())).toList();
    }

    /**
     *可以表示一天中的时段，例如“上午”或“晚上”，而不仅仅是am / pm
     */
    public static void timeFun (){
        LocalTime lt = LocalTime.now();
        String date1 = DateTimeFormatter.ofPattern("a").format(lt);
        String date2 = DateTimeFormatter.ofPattern("B").format(lt);
        String date3 = DateTimeFormatter.ofPattern("k").format(lt);
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
    }

    /**
     * 模式匹配instanceof
     */
    public static void instanceofFun (Object o){
        // old
        if(o instanceof String){
            String s = (String) o;
            System.out.println("舊版的string" + s);
        }
        // new
        if(o instanceof String s){
            System.out.println("舊版的string" + s);
        }
    }

    private static List<DataRecord> records (){
        List<DataRecord> list = Arrays.asList(
                new DataRecord("1","中秋活动"),
                new DataRecord("2","春节活动"),
                new DataRecord("3","元旦活动"),
                new DataRecord("4","清明活动"),
                new DataRecord("5","重阳节")
        );
        return list;
    }

    /******** 17 *******/
    /**
     * switch新增模式 可直接用instanceof模式匹配选择
     */
    public static void switchInstanceFun (Object o){
        // OLD
        if (o instanceof String s) {
            // 直接使用 s拼接字符串
            s += "String";
        } else if (o instanceof Integer i){
            // 直接使用i进行整型逻辑运算
            i += 1;
        }

        // 新的
//        switch (o) {
//            case null      -> System.out.println("首先判断对象是否为空，走空指针逻辑等后续逻辑");
//            case Integer i -> System.out.println("判断是否为Intger对象,i:" + i);
//            case String s  -> System.out.println("判断是否为字符串，s:" + s);
//            case Record p  -> System.out.println("判断是否为Record类型: " + p.toString());
//            case int[] arr -> System.out.println("判断是否为数组，展示int数组的长度" + arr.length);
//
//            case UserInf s   -> System.out.println("判断是否为具体学生对象，UserInf:" + s.toString());
//            default   -> System.out.println("Something else");
//        }
    }
}
