package tr.xip.unimarnf.Models;

public class Materh {

    String aula;
    String codigo;
    String primernomp;
    String primerap;
    String minutoinicio;
    String minutofinal;
    String materia;
    String dia;

    String horainicio;
    String horafinal;
    String tamano;
    String mi;
    String mf;

    public Materh() {
    }

    public Materh(String aula, String codigo, String primernomp, String primerap, String minutoinicio, String minutofinal, String materia, String dia,  String horainicio, String horafinal, String tamano, String mi, String mf) {
        this.aula = aula;
        this.codigo = codigo;
        this.primernomp = primernomp;
        this.primerap = primerap;
        this.minutoinicio = minutoinicio;
        this.minutofinal = minutofinal;
        this.materia = materia;
        this.dia = dia;
        this.horainicio = horainicio;
        this.horafinal = horafinal;
        this.tamano = tamano;
        this.mi = mi;
        this.mf = mf;
    }

    public String getAula() {
        return aula;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPrimernomp() {
        return primernomp;
    }

    public String getPrimerap() {
        return primerap;
    }

    public String getMinutoinicio() {
        return minutoinicio;
    }

    public String getMinutofinal() {
        return minutofinal;
    }

    public String getMateria() {
        return materia;
    }

    public String getDia() {
        return dia;
    }



    public String getHorainicio() {
        return horainicio;
    }

    public String getHorafinal() {
        return horafinal;
    }

    public String getTamano() {
        return tamano;
    }

    public String getMi() {
        return mi;
    }

    public String getMf() {
        return mf;
    }
}
