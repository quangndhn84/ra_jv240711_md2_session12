package ra.presentation;

import ra.bussinessImp.Product;

import java.util.Comparator;
import java.util.Scanner;

public class ProductManagement {
    public static void displayProduct(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("*****************PRODUCT MENU****************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhât thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Sắp xếp sản phẩm theo giá bán tăng dần");
            System.out.println("6. Sắp xếp sản phâẩm theo giá nhập giảm dần");
            System.out.println("7. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("8. Thoát");
            int choice = Main.inputChoice(scanner);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    sortProductByExportPriceASC();
                    break;
                case 6:
                    sortProductByImportPriceDESC();
                    break;
                case 7:
                    searchProductByName(scanner);
                    break;
                case 8:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-8");

            }
        } while (isExit);
    }

    public static void sortProductByImportPriceDESC() {
        Main.listProducts.stream().sorted(Comparator.comparing(Product::getImportPrice).reversed())
                .forEach(Product::displayData);
    }

    public static void sortProductByExportPriceASC() {
        Main.listProducts.stream().sorted(Comparator.comparing(Product::getExportPrice))
                .forEach(Product::displayData);
    }

    public static void searchProductByName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm:");
        String productName = scanner.nextLine();
        System.out.println("Các sản phẩm thỏa mãn là: ");
        Main.listProducts.stream().filter(product -> product.getName().toLowerCase().contains(productName.toLowerCase()))
                .forEach(Product::displayData);
    }
}
