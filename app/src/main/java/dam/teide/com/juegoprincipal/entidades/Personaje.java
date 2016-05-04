package dam.teide.com.juegoprincipal.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_personaje")
public class Personaje {

    private int vida;
    private int ataque;
    private int defensa;
    private int velocidad;
    private int critico;

    public static final String ID_PERSONAJE = "_id_personaje";
    public static final String NOMBRE_PERSONAJE = "nombre_personaje";
    public static final String NIVEL = "nivel";
    public static final String NIVEL_CASCO = "nivCasco";
    public static final String NIVEL_ARCO = "nivArco";
    public static final String NIVEL_ESCUDO = "nivEscudo";
    public static final String NIVEL_GUANTES = "nivGuantes";
    public static final String NIVEL_BOTAS = "nivBotas";
    public static final String NIVEL_FLECHA = "nivFlecha";

    @DatabaseField(generatedId = true, columnName = ID_PERSONAJE)
    private int id_personaje;
    @DatabaseField(columnName = NOMBRE_PERSONAJE)
    private String nombre_personaje;
    @DatabaseField(columnName = NIVEL)
    private int nivel;
    @DatabaseField(columnName = NIVEL_CASCO)
    private int nivCasco;
    @DatabaseField(columnName = NIVEL_ARCO)
    private int nivArco;
    @DatabaseField(columnName = NIVEL_ESCUDO)
    private int nivEscudo;
    @DatabaseField(columnName = NIVEL_GUANTES)
    private int nivGuantes;
    @DatabaseField(columnName = NIVEL_BOTAS)
    private int nivBotas;
    @DatabaseField(columnName = NIVEL_FLECHA)
    private int nivFlecha;

    public Personaje(){

    }

    public Personaje(String nombre_personaje,int nivel,int nivCasco, int nivArco, int nivEscudo, int nivGuantes, int nivBotas, int nivFlecha) {
        this.nombre_personaje=nombre_personaje;
        this.nivel = nivel;
        this.nivCasco = nivCasco;
        this.nivArco = nivArco;
        this.nivEscudo = nivEscudo;
        this.nivGuantes = nivGuantes;
        this.nivBotas = nivBotas;
        this.nivFlecha = nivFlecha;
        this.vida = Vida(nivel,nivCasco);
        this.ataque = Ataque(nivel,nivArco,nivFlecha);
        this.defensa = Defensa(nivel,nivEscudo);
        this.velocidad = Velocidad(nivel,nivBotas,nivGuantes);
        this.critico=Critico(nivel,nivArco,nivGuantes,nivFlecha);
    }
    public Personaje(int nivel){
        this.nivel = nivel;
        int nivCasco=0;
        this.nivCasco = nivCasco;
        int nivArco=0;
        this.nivArco = nivArco;
        int nivEscudo=0;
        this.nivEscudo = nivEscudo;
        int nivGuantes=0;
        this.nivGuantes = nivGuantes;
        int nivBotas=0;
        this.nivBotas = nivBotas;
        int nivFlecha=0;
        this.nivFlecha = nivFlecha;

        this.vida = Vida(nivel,nivCasco);
        this.ataque = Ataque(nivel,nivArco,nivFlecha);
        this.defensa = Defensa(nivel,nivEscudo);
        this.velocidad = Velocidad(nivel,nivBotas,nivGuantes);
        this.critico=Critico(nivel,nivArco,nivGuantes,nivFlecha);
    }
    private int Vida(int nivel, int casco){
        int resulvid;
        resulvid = (nivel*100)+(casco*5);
        return resulvid;
    }
    private int Ataque(int nivel, int arco,int flecha){
        int resulata;
        resulata = (nivel*3)+(arco*2)+(flecha);
        return resulata;
    }
    private int Defensa(int nivel,int escudo){
        int resuldef;
        resuldef = (nivel*3)+(escudo*2);
        return resuldef;
    }
    private int Velocidad(int nivel,int botas,int guantes){
        int resulvel;
        resulvel = (nivel*3)+(botas*2)+(guantes);
        return resulvel;
    }
    private int Critico(int nivel,int arco,int guantes,int flecha){
        int resulcrit;
        resulcrit = (nivel*3)+arco+guantes+flecha;
        return resulcrit;
    }

    public String getNombre_personaje() {
        return nombre_personaje;
    }
    public void setNombre_personaje(String nombre_personaje) {
        this.nombre_personaje = nombre_personaje;
    }
    public int getId_personaje() {
        return id_personaje;
    }
    public void setId_personaje(int id_personaje) {
        this.id_personaje = id_personaje;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public int getNivCasco() {
        return nivCasco;
    }
    public void setNivCasco(int nivCasco) {
        this.nivCasco = nivCasco;
    }
    public int getNivArco() {
        return nivArco;
    }
    public void setNivArco(int nivArco) {
        this.nivArco = nivArco;
    }
    public int getNivEscudo() {
        return nivEscudo;
    }
    public void setNivEscudo(int nivEscudo) {
        this.nivEscudo = nivEscudo;
    }
    public int getNivGuantes() {
        return nivGuantes;
    }
    public void setNivGuantes(int nivGuantes) {
        this.nivGuantes = nivGuantes;
    }
    public int getNivBotas() {
        return nivBotas;
    }
    public void setNivBotas(int nivBotas) {
        this.nivBotas = nivBotas;
    }
    public int getNivFlecha() {
        return nivFlecha;
    }
    public void setNivFlecha(int nivFlecha) {
        this.nivFlecha = nivFlecha;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    public int getCritico() {
        return critico;
    }
    public void setCritico(int critico) {
        this.critico = critico;
    }
}
