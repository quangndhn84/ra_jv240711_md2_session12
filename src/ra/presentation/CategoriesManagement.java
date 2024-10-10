package ra.presentation;

import ra.bussinessImp.Categories;

import java.util.Scanner;

public class CategoriesManagement {
    public static void displayCategoriesMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("*****************CATEGORIES MENU****************");
            System.out.println("1. Danh sách danh mục");
            System.out.println("2. Thêm mới danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Thoát");
            int choice = Main.inputChoice(scanner);
            switch (choice) {
                case 1:
                    displayCategories();
                    break;
                case 2:
                    addCategories(scanner);
                    break;
                case 3:
                    updateCategories(scanner);
                    break;
                case 4:
                    deleteCategories(scanner);
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-5");
            }
        } while (isExit);
    }

    public static void displayCategories() {
        Main.listCategories.stream().forEach(categories -> categories.displayData());
    }

    public static void addCategories(Scanner scanner) {
        System.out.println("Nhập vào số lượng danh mục cần thêm:");
        int cnt = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < cnt; i++) {
            Categories catalog = new Categories();
            catalog.inputData(scanner);
            Main.listCategories.add(catalog);
        }
    }

    public static void updateCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhât:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int index = getIndexByCatalogId(catalogId);
        if (index > 0) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhât tên danh mục");
                System.out.println("2. Cập nhật mô tả danh mục");
                System.out.println("3. Cập nhật trạng thái danh mục");
                System.out.println("4. Thoát");
                int choice = Main.inputChoice(scanner);
                switch (choice) {
                    case 1:
                        Main.listCategories.get(index).setName(scanner.nextLine());
                        break;
                    case 2:
                        Main.listCategories.get(index).setDescription(scanner.nextLine());
                        break;
                    case 3:
                        Main.listCategories.get(index).setStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-4");

                }
            } while (isExit);
        }
        System.err.println("Không tìm thấy danh mục, vui lòng thực hiện lại");
    }

    public static int getIndexByCatalogId(int catalogId) {
        for (int i = 0; i < Main.listCategories.size(); i++) {
            if (Main.listCategories.get(i).getId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int index = getIndexByCatalogId(catalogId);
        if (index > 0) {
            int cnt = (int) Main.listProducts.stream().filter(product -> product.getCatalogId() == catalogId).count();
            if (cnt == 0) {
                Main.listCategories.remove(index);
            }
            System.err.println("Danh mục đã chứa sản phẩm, không thể xóa được");
        } else {
            System.err.println("Mã danh mục không tồn tại, vui lòng thực hiện lại");
        }
    }
}
