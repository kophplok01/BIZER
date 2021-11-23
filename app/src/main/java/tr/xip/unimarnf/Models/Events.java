package tr.xip.unimarnf.Models;

public class Events {

    String contenido;
    int timeA;
    int timeMe;
    int timeD;
    int timeH;
    int timeMi;
    int timeS;
    int timeMs;

    public Events() {
    }

    public Events(String contenido, int timeA, int timeMe, int timeD, int timeH, int timeMi, int timeS, int timeMs) {
        this.contenido = contenido;
        this.timeA = timeA;
        this.timeMe = timeMe;
        this.timeD = timeD;
        this.timeH = timeH;
        this.timeMi = timeMi;
        this.timeS = timeS;
        this.timeMs = timeMs;
    }

    public String getContenido() {
        return contenido;
    }

    public int getTimeA() {
        return timeA;
    }

    public int getTimeMe() {
        return timeMe;
    }

    public int getTimeD() {
        return timeD;
    }

    public int getTimeH() {
        return timeH;
    }

    public int getTimeMi() {
        return timeMi;
    }

    public int getTimeS() {
        return timeS;
    }

    public int getTimeMs() {
        return timeMs;
    }
}
