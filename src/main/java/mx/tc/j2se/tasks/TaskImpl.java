package mx.tc.j2se.tasks;

public class TaskImpl implements Task{
    //Attributes
    String title;
    boolean active;
    int time;
    int start;
    int end;
    int interval;
    boolean repeat;


    //Constructors
    public TaskImpl(){}

    public TaskImpl(String title, int time){
        this.title = title;
        this.time = time;
        repeat=false;
        active=false;
    }

    public TaskImpl(String title, int start, int end, int interval){
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeat=true;
        active=false;
    }


    // Methods
    public String getTitle(){
        return title;
    }

     public void setTitle(String title){
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (repeat==false){
            return time;
        }
        else{
            return start;
        }

    }

    public void setTime(int time) {
        this.time = time;
        repeat=false;

    }

    public int getStartTime() {
        if (repeat==false){
            return time;
        }
        else{
            return start;
        }
    }

    public int getEndTime() {
        if (repeat==false){
            return time;
        }
        else{
            return end;
        }
    }

    public int getRepeatInterval() {
        if (repeat==false){
            return 0;
        }
        else{
            return interval;
        }
    }

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeat=true;

    }

    public boolean isRepeated() {
        return repeat;
    }

    public int nextTimeAfter(int current) {
        if (active==true){
            if (repeat==false){
                if(current<time){
                    return time;
                }
                else{
                    return -1;
                }
            }

            else{
                if(current<start){
                    return start;
                }
                else if (current>=start && current<end){
                    return start+interval;
                }
                else{
                    return -1;
                }
            }

        }

        else{
            return -1;
        }
    }

}
