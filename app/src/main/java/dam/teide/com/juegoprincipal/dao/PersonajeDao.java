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
        dao = getHelper(context).getPersonajeDao();
    }
    //FUNCIONES DE CREACION
    public static boolean newPersonaje(Context context,String nombre_personaje,int nivel,int nivCasco, int nivArco, int nivEscudo, int nivGuantes, int nivBotas, int nivFlecha,int pepita,int hierro,int gema_bruto,int roca,int tronco,int lingote_oro, int lingote_hierro, int gema, int piedra, int tabla_madera){
        Personaje p = montarPersonaje(nombre_personaje,nivel,nivCasco, nivArco, nivEscudo, nivGuantes, nivBotas, nivFlecha,pepita,hierro,gema_bruto,roca,tronco,lingote_oro,lingote_hierro,gema, piedra, tabla_madera);
        return crearPersonaje(p,context);
    }
    public static boolean crearPersonaje(Personaje p,Context context){
        try {
            cargarDao(context);
            dao.create(p);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Personaje montarPersonaje(String nombre_personaje,int nivel,int nivCasco, int nivArco, int nivEscudo, int nivGuantes, int nivBotas, int nivFlecha,int pepita,int hierro,int gema_bruto,int roca,int tronco,int lingote_oro, int lingote_hierro, int gema, int piedra, int tabla_madera){
        Personaje p = new Personaje(nombre_personaje,nivel, nivCasco, nivArco, nivEscudo, nivGuantes, nivBotas, nivFlecha,pepita,hierro,gema_bruto,roca,tronco,lingote_oro,lingote_hierro,gema,piedra,tabla_madera);
        return p;
    }
    //FUNCIONES DE BORRADO
    public static void borrarPersonaje(Context context)throws SQLException{
        cargarDao(context);
        DeleteBuilder<Personaje, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }

    public static Personaje buscarPersonaje(Context context) throws SQLException {
        cargarDao(context);
        List<Personaje> listadoPersonaje= dao.queryForAll();
        if(listadoPersonaje.isEmpty()) {
            return null;
        }else{
            return listadoPersonaje.get(0);
        }
    }
    //FUNCIONES ACTUALIZAR

}
