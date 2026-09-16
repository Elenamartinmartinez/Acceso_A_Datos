import java.util.Comparator;

public class Comparar_Nombres implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        return ((Cliente)o2).getNombre().compareToIgnoreCase(((Cliente)o1).getNombre());
    }
}
