package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";

    private static String DB_NAME = "EFinder.db";

    private final Context myContext;

    private SQLiteDatabase myDataBase;

    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);

        this.myContext = context;

        DB_PATH = "/data/data/"+context.getApplicationInfo().dataDir + "/databases/";

    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(!dbExist) {

            this.getReadableDatabase();

            this.close();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copiando base de datos");

            }

        }

    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {

            String myPath = DB_PATH + DB_NAME;

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch(SQLiteException e) {

            System.out.println("La base de datos no existe todavía.");

        }

        if(checkDB != null) {

            checkDB.close();

        }
        Log.e("checkDatabase",String.valueOf(checkDB!=null));
        return checkDB != null;


    }

    public void copyDataBase() throws IOException {

        InputStream myInput = myContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];

        int length;

        Log.e("copyDataBase","Base de datos copiada");

        while ((length = myInput.read(buffer)) > 0) {

            myOutput.write(buffer, 0, length);

        }

        myOutput.flush();

        myOutput.close();

        myInput.close();

    }

    public void openDataBase() throws SQLException {

        if(!checkDataBase()){
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                createDataBase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override

    public synchronized void close() {

        if(myDataBase != null)

            myDataBase.close();

        super.close();

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
