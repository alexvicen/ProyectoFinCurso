package dam.teide.com.juegoprincipal.hilos;

        import android.os.AsyncTask;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.sql.SQLException;

        import dam.teide.com.juegoprincipal.nucleo.Index;
        import dam.teide.com.juegoprincipal.nucleo.Login;

public class HiloConexion extends AsyncTask<Void,Void,Void> {
    private Login login;
    private Index index;
    private String mensaje;
    private String metodo;
    private String usuario;
    private String contraseña;

    private String nombre_personaje;
    private int fk_usuario;
    private int nivel;
    private int nivel_casco;
    private int nivel_arco;
    private int nivel_escudo;
    private int nivel_guantes;
    private int nivel_botas;
    private int nivel_flecha;
    private int pepita;
    private int roca;
    private int tronco;
    private int hierro;
    private int gema_bruto;
    private int lingote_oro;
    private int lingote_hierro;
    private int gema;
    private int piedra;
    private int tabla_madera;

    public HiloConexion(Login login,String metodo) {
        this.login = login;
        this.metodo = metodo;
    }

    public HiloConexion(Login login, String metodo, String usuario, String contraseña) {
        this.login = login;
        this.metodo = metodo;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public HiloConexion(Index index,String metodo,String nombre_personaje,int fk_usuario, int nivel, int nivel_casco,
             int nivel_arco,int nivel_escudo,int nivel_guantes,int nivel_botas,int nivel_flecha,int pepita,int roca,
             int tronco,int hierro,int gema_bruto, int lingote_oro,int lingote_hierro,int gema,int piedra,int tabla_madera){
        this.index=index;
        this.metodo=metodo;
        this.nombre_personaje=nombre_personaje;
        this.fk_usuario=fk_usuario;
        this.nivel=nivel;
        this.nivel_casco=nivel_casco;
        this.nivel_arco=nivel_arco;
        this.nivel_escudo=nivel_escudo;
        this.nivel_guantes=nivel_guantes;
        this.nivel_botas=nivel_botas;
        this.nivel_flecha=nivel_flecha;
        this.pepita=pepita;
        this.roca=roca;
        this.tronco=tronco;
        this.hierro=hierro;
        this.gema_bruto=gema_bruto;
        this.lingote_oro=lingote_oro;
        this.lingote_hierro=lingote_hierro;
        this.gema=gema;
        this.piedra=piedra;
        this.tabla_madera=tabla_madera;
    }


    @Override
    protected Void doInBackground(Void... params) {
        try {
            switch (metodo){
                case "login":
                    mensaje = parsearPersonaje();
                    break;
                case "update":
                    mensaje = actualizaPersonaje();
                    break;
                default:
                    mensaje = parsearPersonaje();
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        switch (metodo){
            case "login":
                if (mensaje.isEmpty()) {
                    login.errorDatos();
                }else {
                    try {
                        login.recogerJsonPersonaje(mensaje);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "update":
                if (mensaje.isEmpty()) {
                    index.errorDatos();
                }else {
                    try {
                        index.recogerJsonActualizarPersonaje(mensaje);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {

    }
    private String parsearPersonaje() throws IOException, JSONException {
        JSONObject msg = new JSONObject("{\"login_usuario\":\""+usuario+"\",\"pass_usuario\":\""+contraseña+"\"}");
        URL urlws = new URL("http://192.168.0.150:99/practicas/fin_curso_api/v1/usuarios/login");
        HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        uc.setRequestMethod("POST");
        uc.connect();
        OutputStream os = uc.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(msg.toString());
        osw.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        String contenido = "";
        while ((inputLine = in.readLine()) != null) {
            contenido += inputLine + "\n";
        }
        in.close();
        osw.close();
        return contenido;
    }

    public String actualizaPersonaje()throws IOException, JSONException{

            JSONObject msg = new JSONObject("{" +
                    "\"nombre_personaje\":\""+nombre_personaje+"\"," +
                    "\"fk_usuario\":\""+fk_usuario+"\"," +
                    "\"nivel\":\""+nivel+"\"," +
                    "\"nivel_casco\":\""+nivel_casco+"\"," +
                    "\"nivel_arco\":\""+nivel_arco+"\"," +
                    "\"nivel_escudo\":\""+nivel_escudo+"\"," +
                    "\"nivel_guantes\":\""+nivel_guantes+"\"," +
                    "\"nivel_botas\":\""+nivel_botas+"\"," +
                    "\"nivel_flecha\":\""+nivel_flecha+"\"," +
                    "\"pepita\":\""+pepita+"\"," +
                    "\"roca\":\""+roca+"\"," +
                    "\"tronco\":\""+tronco+"\"," +
                    "\"hierro\":\""+hierro+"\"," +
                    "\"gema_bruto\":\""+gema_bruto+"\"," +
                    "\"lingote_oro\":\""+lingote_oro+"\"," +
                    "\"lingote_hierro\":\""+lingote_hierro+"\"," +
                    "\"gema\":\""+gema+"\"," +
                    "\"piedra\":\""+piedra+"\"," +
                    "\"tabla_madera\":\""+tabla_madera+"\"}");
            URL urlws = new URL("http://192.168.0.150:99/practicas/fin_curso_api/v1/personajes/actualizar");
            HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestMethod("PUT");
            uc.connect();
            OutputStream os = uc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(msg.toString());
            osw.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            String contenido = "";
            while ((inputLine = in.readLine()) != null) {
                contenido += inputLine + "\n";
            }
            in.close();
            osw.close();
            return contenido;

    }
}