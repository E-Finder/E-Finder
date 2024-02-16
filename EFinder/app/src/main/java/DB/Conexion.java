    package DB;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class Conexion extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "usuarios.db";
        private static final int DATABASE_VERSION = 1;
        private SQLiteDatabase database;

        public Conexion(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Crear la tabla usuario
            String createTableQuery = "CREATE TABLE usuario (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "vip INTEGER DEFAULT 0)";
            db.execSQL(createTableQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Manejar la actualización de la base de datos si es necesario
            db.execSQL("DROP TABLE IF EXISTS usuario");
            onCreate(db);
        }

        // Método para obtener la conexión a la base de datos
        public SQLiteDatabase getDatabase() {
            if (database == null || !database.isOpen()) {
                database = this.getWritableDatabase();
            }
            return database;
        }

        @Override
        public synchronized void close() {
            if (database != null) {
                database.close();
            }
            super.close();
        }
    }
