package dam.teide.com.juegoprincipal.entidades;

public class Personaje {

    private int vida;
    private int ataque;
    private int defensa;
    private int velocidad;
    private int critico;
    private int nivel;
    private int nivCasco;
    private int nivArco;
    private int nivEscudo;
    private int nivGuantes;
    private int nivBotas;
    private int nivFlecha;

    public Personaje(int nivel,int nivCasco, int nivArco, int nivEscudo, int nivGuantes, int nivBotas, int nivFlecha) {
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
