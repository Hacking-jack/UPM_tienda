package es.upm.etsisi.poo.dataBase;

import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private Path dbPath;
    private Connection connection;

    private DatabaseConnection() {
        Path jarDir = getJarDirectory();
        this.dbPath = jarDir.resolve("database.db");
    }

    //lazy init de la conexion a la base de datos
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    /* =======================
       CONEXIÓN SQLITE
       ======================= */

    private void openConnection() {
        try {
            // Si no existe, SQLite la crea automáticamente
            String url = "jdbc:sqlite:" + dbPath.toAbsolutePath();
            connection = DriverManager.getConnection(url);
            //TODO quitar esto una vez finalizado
            System.out.println("Base de datos: " + dbPath);
            initTables(); // opcional, crea tablas si no existen
        } catch (SQLException e) {
            throw new RuntimeException("Error al abrir la base de datos", e);
        }
    }
//TODO tablas de base de datos , en diseño
    private void initTables() {
        String sql = "CREATE TABLE IF NOT EXISTS productos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nombre TEXT NOT NULL, "
                + "precio REAL NOT NULL"
                + ");";


        try (var stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /* =======================
       UTILIDAD
       ======================= */

    private Path getJarDirectory() {
        try {
            return Paths.get(
                    DatabaseConnection.class
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            ).getParent();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo localizar el ejecutable", e);
        }
    }
}
