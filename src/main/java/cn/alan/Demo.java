package cn.alan;

public class Demo {

    public static void main(String[] args) {
        String str1 = new String("xxx");
        String str2 = "xxx";

        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
    }
}
