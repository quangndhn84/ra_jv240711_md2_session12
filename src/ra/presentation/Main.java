package ra.presentation;

import ra.bussinessImp.Categories;
import ra.bussinessImp.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Categories> listCategories = new ArrayList<>();
    public static List<Product> listProducts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("**************SHOP MENU*****************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            int choice = inputChoice(scanner);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-3");
            }
        } while (true);
    }

    public static int inputChoice(Scanner scanner) {
        System.out.print("Lưa chọn của bạn:");
        do {
            try {
                return Integer.parseInt(scanner.nextLine());

            } catch (Exception ex) {
                System.err.println("Lựa chọn là số nguyên, vui lòng nhập lại");
            }
        } while (true);
    }
}
