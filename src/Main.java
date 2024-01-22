import entities.Circle;
import entities.Product;
import entities.Rectangle;
import entities.Shape;
import services.CalculationService;
import services.PrintService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

        TIPOS CURINGA (wildcards)

        Generics são invariantes
        List<Object> não é o supertipo de qualquer tipo de lista

        O supertipo de qualquer tipo de lista é List<?>. É um tipo curinga

        Porém, não é possível adicionar dados a uma coleção de tipos curinga

        TIPOS CURINGA DELIMITADOS

        é possível delimitar os tipos curinga, ou tornar um método genérico para os tipos curinga.

         */

//        lerValores();
//        lerProdutos();

//        List<Integer> myInts = Arrays.asList(5,2,10);
//        printList(myInts);
//
//        List<String> myStrs = Arrays.asList("Maria", "Alex", "Bob");
//        printList(myStrs);

        List<Shape> myShapes = new ArrayList<>();
        myShapes.add(new Rectangle(3.0, 2.0));
        myShapes.add(new Circle(2.0));

        List<Circle> myCircles = new ArrayList<>();
        myCircles.add(new Circle(2.0));
        myCircles.add(new Circle(3.0));

        System.out.println("total area: " + totalArea(myCircles));

        putGetProblem();
    }

    // Essa declaração define que a lista pode ser de shape ou de qualquer tipo de shape
    public static double totalArea(List<? extends Shape> list){
        double sum = 0.0;
        for (Shape s : list){
            sum += s.area();
        }
        return sum;
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

    public static void wildcards(){
        List<?> myObjs = new ArrayList<Objects>();
        List<Integer> myNumbers = new ArrayList<Integer>();
        myObjs = myNumbers;

        // List<?> é o supertipo de qualquer tipo de lista

        Object obj;
        Integer x = 10;
        obj = x;
        // assim funciona

    }

    public static void printList(List<?> list) {
        // método para imprimir qualquer tipo de lista
        for (Object obj : list){
            System.out.println(obj);
        }
        System.out.println();
    }

    public static void covarianciaContravariancia(){
        // Covariância - Operação get é permitida, porém não o put
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(10);
        intList.add(5);

        List<? extends Number> list = intList;

        Number x = list.get(0);

//        list.add(20); // erro de compilação

        // Contravariância - Operação put é permitida, porém não o get
        List<Object> myObjs = new ArrayList<Object>();
        myObjs.add("Maria");
        myObjs.add("Alex");

        List<? super Number> myNums = myObjs;
        myNums.add(10);
        myNums.add(3.14);

//        Number x = myNums.get(0); // erro de compilação
    }

    public static void putGetProblem() {
        List<Integer> myInts = Arrays.asList(1, 2, 3, 4);
        List<Double> myDoubles = Arrays.asList(3.14, 6.28);
        List<Object> myObjs = new ArrayList<Object>();

        copy(myInts, myObjs);
        printList(myObjs);
        copy(myDoubles, myObjs);
        printList(myObjs);
    }

    // Método que vai realizar a cópia. Para isso, é necessário que o argumento possa receber qualquer tipo de number
    public static void copy(List<? extends Number> source, List<? super Number> destiny){
        // método do collections para adicionar todos os elementos da lista em uma nova - poderia ser feito com um foreach
        destiny.addAll(source);
    }
}