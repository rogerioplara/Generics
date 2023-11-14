import entities.Product;
import services.CalculationService;
import services.PrintService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        GENERICS
            Permitem que classes, interfaces e métodos possam ser parametrizados por tipo.
            Seus benefícios são:
                - Reuso
                - Type safety
                - Performance

        Uso comum: coleções


         */

//        lerValores();
        lerProdutos();
    }

    public static void lerValores(){
        Scanner sc = new Scanner(System.in);

        // Instância de uma lista genérica do tipo desejado
        PrintService<String> ps = new PrintService<>();

        System.out.print("How many values? ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++){
            String value = sc.next();
            ps.addValue(value);
        }

        ps.print();
        System.out.println("First: " + ps.first());

        sc.close();
    }

    public static void lerProdutos() {

        List<Product> list = new ArrayList<>();

        String path = "C:\\temp\\in.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");
                list.add(new Product(fields[0], Double.parseDouble(fields[1])));
                line = br.readLine();
            }

            Product x = CalculationService.max(list);
            System.out.println("Most expensive: ");
            System.out.println(x);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}