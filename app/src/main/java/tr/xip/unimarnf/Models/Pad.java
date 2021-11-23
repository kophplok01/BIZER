package tr.xip.unimarnf.Models;

public class Pad {


    String  cedula;
    String primerN;
    String segundoN;
    String primerA;
    String segundoA;
    String primerT;

    public Pad() {
    }

    public Pad(String cedula, String primerN, String segundoN, String primerA, String segundoA, String primerT) {
        this.cedula = cedula;
        this.primerN = primerN;
        this.segundoN = segundoN;
        this.primerA = primerA;
        this.segundoA = segundoA;
        this.primerT = primerT;

    }

    public String getCedula() {
        return cedula;
    }

    public String getPrimerN() {
        return primerN;
    }

    public String getSegundoN() {
        return segundoN;
    }

    public String getPrimerA() {
        return primerA;
    }

    public String getSegundoA() {
        return segundoA;
    }

    public String getPrimerT() {
        return primerT;
    }

}
