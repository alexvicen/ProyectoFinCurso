package dam.teide.com.juegoprincipal.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

import dam.teide.com.juegoprincipal.dbHelper.DBHelperMOS;
import dam.teide.com.juegoprincipal.entidades.Personaje;

public class PersonajeDao extends DBHelperMOS{

    static Dao<Personaje, Integer> dao;
    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getPersonajeDAO();
    }
    //FUNCIONES DE CREACION
    public static boolean newPersonaje(Context context){
        Personaje p = montarPersonaje();
        return crearPersonaje(p,context);
    }
    public static boolean crearPersonaje(Personaje p,Context context){
        try {
            cargarDao(context);
            dao.create(p);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static Personaje montarPersonaje(){
        Personaje p = new Personaje(1);
        return p;
    }
    //FUNCIONES DE BORRADO
    public static void borrarPersonaje(Context context)throws SQLException{
        cargarDao(context);
        DeleteBuilder<Personaje, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }

    public static List<Personaje> buscarTodosLosUsuarios(Context context) throws SQLException {
        cargarDao(context);
        List<Personaje> listadoPersonaje= dao.queryForAll();
        if(listadoPersonaje.isEmpty()) {
            return null;
        }else{
            return listadoPersonaje;
        }
    }
    //FUNCIONES ACTUALIZAR

}
