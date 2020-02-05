package ec.edu.tecnologicoloja.shoppinglist.database.model;


public class ShoppingItem {

    private long id;
    private String name;

    public ShoppingItem(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Method to get the id of the item
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Method to get the name of the item
     *
     * @return item name
     */
    public String getName() {

        return name;
    }

    /**
     * Method to set the name of the item
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
