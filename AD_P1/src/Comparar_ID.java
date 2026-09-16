import java.util.Comparator;

public class Comparar_ID implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        return ((Cliente)o1).getId() - ((Cliente)o2).getId();
    }
}
