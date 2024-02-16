package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static String DB_NAME = "EFinder.db";
    private static final int DB_VERSION = 1; // Incrementar según sea necesario para nuevas versiones
    private final Context context;
    private String DB_PATH;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        this.DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        try {
            this.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException("Error creating database", e);
        }
    }

    // Crea la base de datos si no existe y la copia desde assets
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase();
            this.close(); // Cerrarla antes de copiar la nueva
            try {
                copyDataBase();
                Log.d(TAG, "Database created successfully");
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    // Verifica si la base de datos ya existe para evitar copiar cada vez que se abre la aplicación
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    // Copia la base de datos desde assets
    private void copyDataBase() throws IOException {
        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // No se necesita ya que la DB viene pre-poblada
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes manejar las actualizaciones de la base de datos
    }

    // Agrega una función para abrir la base de datos
    public SQLiteDatabase openDataBase() {
        String myPath = DB_PATH + DB_NAME;
        return SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
