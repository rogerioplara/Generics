package services;

import java.util.List;

public class CalculationService<T> {

    // Declaração do método max
    // precisa ser declarado o tipo <T> e utilizar a interface Comparable para usar o comparTo();
    public static <T extends Comparable<T>> T max(List<T> list){
        if (list.isEmpty()){
            throw new IllegalStateException("List can't be empty");
        }
        T max = list.get(0);
        for (T item : list){
            if(item.compareTo(max) > 0){
                max = item;
            }
        }
        return max;
    }
}
