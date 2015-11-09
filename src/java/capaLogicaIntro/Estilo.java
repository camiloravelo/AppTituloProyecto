package capaLogicaIntro;

public class Estilo {

    private int dimension1;
    private int dimension2;
    private int dimension3;
    private int dimension4;

    public static final int ACTIVO = 1;
    public static final int REFLEXIVO = 2;
    public static final int SENSITIVO = 1;
    public static final int INTUITIVO = 2;
    public static final int VISUAL = 1;
    public static final int VERBAL = 2;
    public static final int SECUENCIAL = 1;
    public static final int GLOBAL = 2;

    public void setDimension1(int dimension1) {
        this.dimension1 = dimension1;
    }

    public void setDimension2(int dimension2) {
        this.dimension2 = dimension2;
    }

    public void setDimension3(int dimension3) {
        this.dimension3 = dimension3;
    }

    public void setDimension4(int dimension4) {
        this.dimension4 = dimension4;
    }

    public int getDimension1() {
        return dimension1;
    }

    public int getDimension2() {
        return dimension2;
    }

    public int getDimension3() {
        return dimension3;
    }

    public int getDimension4() {
        return dimension4;
    }

    public static String staticEstilo1(int e) {
        if (e == 1) {
            return "Sensitivo";
        } else {
            return "Intuitivo";
        }
    }

    public static String staticgetEstilo2(int e) {
        if (e == 1) {
            return "Visual";
        } else {
            return "Verbal";
        }
    }

    public static String staticgetEstilo3(int e) {
        if (e == 1) {
            return "Activo";
        } else {
            return "Reflexivo";
        }
    }

    public static String staticgetEstilo4(int e) {
        if (e == 1) {
            return "Secuencial";
        } else {
            return "Global";
        }
    }

    public String getDimension1Value() {
        if (dimension1 == 1) {
            return "Sensitivo";
        } else {
            return "Intuitivo";
        }
    }

    public String getDimension2Value() {
        if (dimension2 == 1) {
            return "Visual";
        } else {
            return "Verbal";
        }
    }

    public String getDimension3Value() {
        if (dimension3 == 1) {
            return "Activo";
        } else {
            return "Reflexivo";
        }
    }

    public String getDimension4Value() {
        if (dimension4 == 1) {
            return "Secuencial";
        } else {
            return "Global";
        }
    }

    public Estilo(int dimension1, int dimension2, int dimension3, int dimension4) {
        this.dimension1 = dimension1;
        this.dimension2 = dimension2;
        this.dimension3 = dimension3;
        this.dimension4 = dimension4;
    }

    public Estilo() {

    }

    public String devolverColor(int dimension, int dominio) {

        switch (dimension) {
            case 1:
                if (dimension1 == dominio) {
                    return "";
                } else {
                    return "text-muted";
                }

            case 2:
                if (dimension2 == dominio) {
                    return "";
                } else {
                    return "text-muted";
                }
            case 3:
                if (dimension3 == dominio) {
                    return "";
                } else {
                    return "text-muted";
                }

            case 4:
                if (dimension4 == dominio) {
                    return "";
                } else {
                    return "text-muted";
                }
        }

        return "text-sucsess";

    }

    public String devolverEtiquetaColor(int dimension, int dominio) {
        switch (dimension) {
            case 1:
                if (dimension1 == dominio) {
                    return "label label-info";
                } else {
                    return "label label-default";
                }

            case 2:
                if (dimension2 == dominio) {
                    return "label label-info";
                } else {
                    return "label label-default";
                }
            case 3:
                if (dimension3 == dominio) {
                    return "label label-info";
                } else {
                    return "label label-default";
                }

            case 4:
                if (dimension4 == dominio) {
                    return "label label-info";
                } else {
                    return "label label-default";
                }
        }

        return "label label-default";

    }

    public String Fuente(int dimension, int dominio) {

        switch (dimension) {
            case 1:
                if (dimension1 == dominio) {
                    return "BACKGROUND-COLOR: #D9EDF7;";
                } else {
                    return "";
                }

            case 2:
                if (dimension2 == dominio) {
                    return "BACKGROUND-COLOR: #D9EDF7;";
                } else {
                    return "";
                }
            case 3:
                if (dimension3 == dominio) {
                    return "BACKGROUND-COLOR: #D9EDF7;";
                } else {
                    return "";
                }

            case 4:
                if (dimension4 == dominio) {
                    return "BACKGROUND-COLOR: #D9EDF7;";
                } else {
                    return "";
                }
        }

        return "text-sucsess";
    }

    public String texto(int dimension) {
        switch (dimension) {
            case 1:
                if (dimension1 == 1) {
                    return "Concretos, prácticos, orientados hacia los hechos y los procedimientos";
                } else {
                    return "Conceptuales, innovadores, orientados hacia las teorías";
                }

            case 2:
                if (dimension2 == 1) {
                    return "Preferen la presentación visual del material tal como películas, tablas, o diagramas de flujo";
                } else {
                    return " Preferen las explicaciones escritas o habladas";
                }
            case 3:
                if (dimension3 == 1) {
                    return "Aprenden manipulando las cosas y trabajando con otros";
                } else {
                    return "Aprenden pensando acerca de las cosas y trabajando solos";
                }

            case 4:
                if (dimension4 == 1) {
                    return "Aprenden poco a poco en forma ordenada";
                } else {
                    return "Aprenden de forma holística";
                }
        }

        return "text-sucsess";

    }
}
