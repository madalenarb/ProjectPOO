package event;

abstract class Event implements EventInterface{
    private double time;
    private EventManager pec;

    public Event(double time){
        this.time = time;
        this.pec = EventManager.getInstance();
    }

    public double getEventTime(){
        return this.time;
    }

    public double getPECTime(){
        return this.pec.getTime();
    }

    public EventManager getPec(){
        return this.pec;
    }
}