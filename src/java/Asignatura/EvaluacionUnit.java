package Asignatura;


public class EvaluacionUnit {
    String topic;
    int preguntas;
    int buenas;

    public int getMalas(){
    return preguntas-buenas;
    }
    
    public EvaluacionUnit(String topic, int preguntas, int buenas) {
        this.topic = topic;
        this.preguntas = preguntas;
        this.buenas = buenas;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(int preguntas) {
        this.preguntas = preguntas;
    }

    public int getBuenas() {
        return buenas;
    }

    public void setBuenas(int buenas) {
        this.buenas = buenas;
    }
    
    
    
}
