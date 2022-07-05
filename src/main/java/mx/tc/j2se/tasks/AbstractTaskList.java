package mx.tc.j2se.tasks;

abstract class AbstractTaskList {

    public int size=0;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);

    public int size(){
        return this.size;
    }

    public AbstractTaskList incoming (int from, int to) throws IllegalArgumentException{
        /**
         * This method returns an array with the tasks executed within the given interval.
         * The tasks included in the created array must be ACTIVE. As the input values refer
         * to time, they must be positive integers. If not, an exception will be thrown.
         */
        if (from<=0 || to<=0) {
            throw new IllegalArgumentException("Both inputs arguments must be positive integers.");
        }
        else{
            AbstractTaskList incomingTasks = new LinkedTaskListImpl();
            int length=size();
            for(int i=0;i<length;i++){
                Task currentTask=getTask(i);
                if (currentTask.isActive()){
                    int nextMoment=currentTask.nextTimeAfter(from);
                    if (currentTask.isRepeated()){//for repetitive tasks;
                        while (nextMoment>from && nextMoment<to){
                            incomingTasks.add(currentTask);
                            nextMoment=currentTask.nextTimeAfter(nextMoment);
                        }
                    }
                    else{//for non-repetitive tasks;
                        if (nextMoment>from && nextMoment<to){
                            incomingTasks.add(currentTask);
                        }
                    }
                }
            }
            return incomingTasks;
        }
    }

}
