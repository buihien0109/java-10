package vn.techmaster;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Nhập vào số a = ");
//        int a = sc.nextInt();
//
//        System.out.print("Nhập vào số b = ");
//        int b = sc.nextInt();
//
//        System.out.printf("%d + %d = %d\n", a, b, a + b);
//        System.out.printf("%d - %d = %d\n", a, b, a - b);
//        System.out.printf("%d * %d = %d\n", a, b, a * b);
//
//        System.out.printf("%d / %d = %.2f\n", a, b, (double) a / b);
//        System.out.printf("%d^%d = %d\n", a, b, (int) Math.pow(a, b));
//        System.out.printf("%d %% %d = %d\n", a, b, a % b);

//        1. In ra chuỗi in hoa của chuỗi vừa nhập
//        2. In ra chuỗi in thường của chuỗi vừa nhập
//        3. Đếm số ký tự trong chuỗi
//        4. In ra ký tự cuối cùng trong chuỗi
//        5. In ra **5 ký tự đầu tiên** của chuỗi
//        6. Kiểm tra chuỗi vừa nhập có chứa chuỗi "**hello**" hay không?
//        in ra **true** nếu chứa, ngược lại thì in ra **false** nếu không chứa
        System.out.print("Nhập vào chuỗi : ");
        String str = sc.nextLine();

        System.out.println("1. Chuỗi in hoa của chuỗi \"" + str + "\" là : " + str.toUpperCase());
        System.out.println("2. Chuỗi in thường của chuỗi \"" + str + "\" là : " + str.toLowerCase());
        System.out.println("3. Số ký tự của chuỗi \"" + str + "\" là : " + str.length());
        System.out.println("4. Ký tự cuối cùng của chuỗi \"" + str + "\" là : " + str.charAt(str.length() - 1));
        System.out.println("5. 5 ký tự cuối cùng của chuỗi \"" + str + "\" là : " + str.substring(0, 5));
        System.out.println("6. Chuỗi \"" + str + "\" có chứa chuỗi \"hello\" hay không : " + str.contains("hello"));
    }
}
