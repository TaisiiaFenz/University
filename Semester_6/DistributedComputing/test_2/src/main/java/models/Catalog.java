package models;

public class Catalog {
    private int id;
    private String name;
    private String catalog;

    public Catalog() {}

    public Catalog(String name, String catalog) {
        this.name = name;
        this.catalog = catalog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
