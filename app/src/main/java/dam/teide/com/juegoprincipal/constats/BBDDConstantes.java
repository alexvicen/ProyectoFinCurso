package dam.teide.com.juegoprincipal.constats;

import android.content.Context;

import java.sql.SQLException;

import dam.teide.com.juegoprincipal.dao.PersonajeDao;
import dam.teide.com.juegoprincipal.entidades.Personaje;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class BBDDConstantes {
    public static final String DATABASE_NAME = "proyectoFinCurso";
    public static final int DATABASE_VERSION = 1;
    public static Dao<Personaje,Integer> personajeDao;

    public static void cerrarDao(){
        personajeDao = null;
    }

    public static void crearTablas(ConnectionSource connectionSource)throws SQLException{
        TableUtils.createTable(connectionSource,Personaje.class);
    }

    public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
        TableUtils.dropTable(connectionSource, Personaje.class, true);
    }

    public static void borrarDatosTablas(Context context) throws SQLException {
        PersonajeDao.borrarPersonaje(context);
    }
}
