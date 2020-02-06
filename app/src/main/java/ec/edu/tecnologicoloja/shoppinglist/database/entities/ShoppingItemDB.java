package ec.edu.tecnologicoloja.shoppinglist.database.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import ec.edu.tecnologicoloja.shoppinglist.database.helper.ShoppingElementHelper;
import ec.edu.tecnologicoloja.shoppinglist.database.model.ShoppingItem;



public class ShoppingItemDB {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private ShoppingElementHelper dbHelper;

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ShoppingItemDB(Context context) {
        // Create new helper
        dbHelper = new ShoppingElementHelper(context);
    }

    /* Inner class that defines the table contents */
    public static abstract class ShoppingElementEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_NAME_TITLE + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


    /**
     * Method to create new element in the database
     *
     * @param productName
     */
    public void insertElement(String productName) {
        //TODO: add all the needed code to insert one item in database
        SQLiteDatabase bd = dbHelper.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put(ShoppingElementEntry.COLUMN_NAME_TITLE, productName );

        bd.insert(ShoppingElementEntry.TABLE_NAME, null, registro);

        bd.close();
    }

    /**
     * Method to get all the shopping elements of the database
     *
     * @return ShoppingItem array
     */
    public ArrayList<ShoppingItem> getAllItems() {
        ArrayList<ShoppingItem> shoppingItems = new ArrayList<>();

        SQLiteDatabase bd = dbHelper.getReadableDatabase();

        Cursor cursor = bd.query(ShoppingElementEntry.TABLE_NAME,new String[]{ShoppingElementEntry._ID,ShoppingElementEntry.COLUMN_NAME_TITLE},null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                ShoppingItem s = new ShoppingItem(cursor.getInt(0),cursor.getString(1));
                shoppingItems.add(s);
            }while (cursor.moveToNext());
        }
        bd.close();
        cursor.close();
        return shoppingItems;
    }

    /**
     * Method to clear all the elements
     */
    public void clearAllItems() {
        //TODO: add all the needed code to clear all the database items

    }

    /**
     * Method to update a database item
     *
     * @param shoppingItem
     */
    public void updateItem(ShoppingItem shoppingItem) {
        //TODO: add the needed code to update a database item
        SQLiteDatabase bd = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ShoppingElementEntry.TABLE_NAME, String.valueOf(shoppingItem));
        bd.update(ShoppingElementEntry.TABLE_NAME,valores,ShoppingElementEntry._ID + shoppingItem.getId(), null);

    }

    /**
     * Method to delete one item
     *
     * @param shoppingItem
     */
    public void deleteItem(ShoppingItem shoppingItem) {
        //TODO: add all the needed code to delete a database item
        SQLiteDatabase bd = dbHelper.getWritableDatabase();
        bd.delete(ShoppingElementEntry.TABLE_NAME,ShoppingElementEntry._ID + shoppingItem.getId(),null);

    }
}
