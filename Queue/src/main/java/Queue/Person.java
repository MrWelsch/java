package Queue;

public class Person implements Comparable<Person>, Cloneable  {
    public Person() {}

    public Person( String n, String vn) {
        name = n;
        vorname = vn;
    }

    /**
     * Kopierkonstruktor: legt eine Kope des übergebenen Objekts an
     *
     * @param p zu kopierendes Person-Objekt
     */
    public Person(Person p) {
        name = p.name;
        vorname = p.vorname;
    }

    /**
     * Kopieren des Objektes, redefiniert die entsprechende Methode
     * von Object
     */
    @Override
    public Object clone() {
        return new Person(name, vorname);
    }

    /**
     * Vergleicht das Objekt mit dem uebergebenen;
     *
     * @return 0 bei Gleichheit,
     *         > 0 falls das Objekt groesser,
     *         < 0 falls das Objekt kleiner ist
     */
    @Override
    public int compareTo(Person p) {
        int ret = name.compareTo(p.name);
        if (ret == 0)
            return vorname.compareTo(p.vorname);
        else
            return ret;
    }

    public void ausgeben() {
        System.out.print(name + ", " + vorname);
    }

    public void setName(String n) {
        name = n;
    }

    public void setVorname(String vn) {
        vorname = vn;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    @Override
    public String toString() {
        return name + ", " + vorname;
    }

    protected String name;
    protected String vorname;
}
