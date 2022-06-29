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
    public TaskImpl(){
        /**
         * This constructor builds a task without any properties.
         */
    }

    public TaskImpl(String title, int time){
        /**
         * This constructor builds an inactive, non-repetitive task. In order to do this,
         * it needs a title (string) for the task to build and the beginning time.
         * This number needs to be positive integer, because it refers to time.
         */
        this.title = title;
        this.time = time;
        repeat=false;
        active=false;
    }

    public TaskImpl(String title, int start, int end, int interval){
        /**
         * This constructor builds an inactive, repetitive task. In order to do this, it needs a title (string)
         * for the task. Also, it needs the date for starting and ending the task, as well as an interval
         * expressed in hours. All of these numbers need to be positive integers, because they refer to time.
         */
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeat=true;
        active=false;
    }


    // Methods
    @Override
    public String getTitle(){
        /**
         * This method return the title of the task, given as a string.
         */
        return title;
    }

    @Override
    public void setTitle(String title){
         /**
          * This method allows to give a name to the task. The title must be given as a string.
          */
        this.title = title;
    }

    @Override
    public boolean isActive() {
        /**
         * This method returns the status of the task, as a boolean.
         * True means the task is active. False means the task is inactive.
         */
        return active;
    }

    @Override
    public void setActive(boolean active) {
        /**
         * This method allows to change the task status, to active or inactive.
         * The value must be given as a boolean. True for active, false for inactive.
         */
        this.active = active;
    }

    @Override
    public int getTime() {
        /**
         * If the task is repetitive, this method returns the time when the task starts.
         * If the task is non-repetitive, it returns the time of execution.
         */
        if (repeat){
            return start;
        }
        else{
            return time;
        }

    }

    @Override
    public void setTime(int time) {
        /**
         * This method allows to set the time of execution for a non-repetitive task. The time
         * given must be a positive integer. If the task was built as repetitive, this method
         * will change it to a non-repetitive one.
         */
        this.time = time;
        repeat=false;

    }

    @Override
    public int getStartTime() {
        /**
         * If the task is repetitive, this method returns the time when the task starts.
         * If the task is non-repetitive, it returns the time of execution.
         */
        if (repeat){
            return start;
        }
        else{
            return time;
        }
    }

    @Override
    public int getEndTime() {
        /**
         * If the task is repetitive, this method returns the time when the task ends.
         * If the task is non-repetitive, it returns the time of execution.
         */
        if (repeat){
            return end;
        }
        else{
            return time;
        }
    }

    @Override
    public int getRepeatInterval() {
        /**
         * If the task is repetitive, this method returns the repeat interval expressed in hours.
         * If the task is non-repetitive, it returns 0.
         */
        if (repeat){
            return interval;
        }
        else{
            return 0;
        }
    }

    @Override
    public void setTime(int start, int end, int interval) {
        /**
         * This method allows to set the time for starting and ending the task, as well as the repeat
         * interval, for repetitive tasks. All of these values must be given as positive integers.
         * If the task was built as non-repetitive, this method will change it to a repetitive one.
         */
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeat=true;

    }

    @Override
    public boolean isRepeated() {
        /**
         * This method allows to know if the task is repetitive or non-repetitive
         * It returns a boolean. A true value means the task is repetitive.
         * A false value means the task is non-repetitive.
         */
        return repeat;
    }

    @Override
    public int nextTimeAfter(int current) {
        /**
         * This method allows to know the nearest moment when the task is going to be executed,
         * for a current given time, which needs to be a positive integer. For this, the task
         * needs to be active. If not, the return value will be -1.
         *
         * If the task is non-repetitive and the current time is located before the execution time,
         * then the method will return the time value. If current time is located after execution time
         * it will return -1, since the task has already been completed.
         *
         * If the task is repetitive and the current time occurs before the starting time, the method will
         * return the starting time. If current time occurs after or at the same time that the ending time,
         * the method will return -1. However, if the current time is located between the starting and
         * ending time, the method will return the nearest moment when the task is going to be executed,
         * according to the given interval.
         */

        if (active){
            if (!repeat){
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
                else if (start<current && current<end) {
                    int next=start;
                    while (next<current){
                        next+=interval;
                    }
                    if (next>=end){
                        return -1;
                    }
                    else {
                        return next;
                    }
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
