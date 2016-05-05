package dam.teide.com.juegoprincipal.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

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
    public void actualizarNombrePersonaje( Context context, String nombre_personaje) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.NOMBRE_PERSONAJE, nombre_personaje);
        updateBuilder.update();

    }
    public void actualizarNivel( Context context, int nivel) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.NIVEL, nivel);
        updateBuilder.update();
        actualizarVida(context);
        actualizarAtaque(context);
        actualizarDefensa(context);
        actualizarVelocidad(context);
        actualizarCritico(context);
    }
    public void actualizarNivelCasco( Context context, int nivelCasco) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.NIVEL_CASCO, nivelCasco);
        updateBuilder.update();

    }
    public void actualizarNivelArco( Context context, int nivelArco) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.NIVEL_ARCO, nivelArco);
        updateBuilder.update();

    }
    public void actualizarNivelEscudo( Context context, int nivelEscudo) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.NIVEL_ESCUDO, nivelEscudo);
        updateBuilder.update();

    }
    public void actualizarNivelGuantes( Context context, int nivelGuantes) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.NIVEL_GUANTES, nivelGuantes);
        updateBuilder.update();

    }
    public void actualizarNivelBotas( Context context, int nivelBotas) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.NIVEL_BOTAS, nivelBotas);
        updateBuilder.update();

    }
    public void actualizarNivelFlecha( Context context, int nivelFlecha) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.NIVEL_FLECHA, nivelFlecha);
        updateBuilder.update();

    }
    public void actualizarPepita( Context context, int pepita) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.PEPITA, pepita);
        updateBuilder.update();

    }
    public void actualizarHierro( Context context, int hierro) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.HIERRO, hierro);
        updateBuilder.update();

    }
    public void actualizarGemaBruto( Context context, int gema_bruto) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.GEMA_BRUTO, gema_bruto);
        updateBuilder.update();

    }
    public void actualizarRoca( Context context, int roca) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.ROCA, roca);
        updateBuilder.update();

    }
    public void actualizarTronco( Context context, int tronco) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.TRONCO, tronco);
        updateBuilder.update();

    }
    public void actualizarLingoteOro( Context context, int lingote_oro) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.LINGOTE_ORO, lingote_oro);
        updateBuilder.update();

    }
    public void actualizarLingoteHierro( Context context, int lingote_hierro) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.LINGOTE_HIERRO, lingote_hierro);
        updateBuilder.update();

    }
    public void actualizarGema( Context context, int gema) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.GEMA, gema);
        updateBuilder.update();

    }
    public void actualizarPiedra( Context context, int piedra) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.PIEDRA, piedra);
        updateBuilder.update();

    }
    public void actualizarTablaMadera( Context context, int tabla_madera) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        updateBuilder.updateColumnValue(Personaje.TABLA_MADERA, tabla_madera);
        updateBuilder.update();

    }
    private void actualizarVida( Context context) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        int nivel = PersonajeDao.buscarPersonaje(context).getNivel();
        int casco = PersonajeDao.buscarPersonaje(context).getNivCasco();
        int vida = (nivel*100)+(casco*5);
        updateBuilder.updateColumnValue(Personaje.NIVEL_CASCO, vida);
        updateBuilder.update();

    }
    private void actualizarAtaque( Context context) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        int nivel = PersonajeDao.buscarPersonaje(context).getNivel();
        int arco = PersonajeDao.buscarPersonaje(context).getNivArco();
        int flecha = PersonajeDao.buscarPersonaje(context).getNivFlecha();
        int ataque = (nivel*3)+(arco*2)+(flecha);
        updateBuilder.updateColumnValue(Personaje.NIVEL_CASCO, ataque);
        updateBuilder.update();

    }
    private void actualizarDefensa( Context context) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        int nivel = PersonajeDao.buscarPersonaje(context).getNivel();
        int escudo = PersonajeDao.buscarPersonaje(context).getNivEscudo();
        int defensa = (nivel*3)+(escudo*2);
        updateBuilder.updateColumnValue(Personaje.NIVEL_CASCO, defensa);
        updateBuilder.update();

    }
    private void actualizarVelocidad( Context context) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        int nivel = PersonajeDao.buscarPersonaje(context).getNivel();
        int botas = PersonajeDao.buscarPersonaje(context).getNivBotas();
        int guantes = PersonajeDao.buscarPersonaje(context).getNivGuantes();
        int velocidad =(nivel*3)+(botas*2)+(guantes);;
        updateBuilder.updateColumnValue(Personaje.NIVEL_CASCO, velocidad);
        updateBuilder.update();

    }
    private void actualizarCritico( Context context) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Personaje, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Personaje.ID_PERSONAJE,0);
        int nivel = PersonajeDao.buscarPersonaje(context).getNivel();
        int arco = PersonajeDao.buscarPersonaje(context).getNivArco();
        int guantes = PersonajeDao.buscarPersonaje(context).getNivGuantes();
        int flecha = PersonajeDao.buscarPersonaje(context).getNivFlecha();
        int critico =(nivel*3)+arco+guantes+flecha;
        updateBuilder.updateColumnValue(Personaje.NIVEL_CASCO, critico);
        updateBuilder.update();

    }
}
