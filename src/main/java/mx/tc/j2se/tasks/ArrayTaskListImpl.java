package mx.tc.j2se.tasks;

public class ArrayTaskListImpl implements ArrayTaskList {
    //Attributes
    Task[] taskList;
    int size=0;
    boolean success;

    //Constructor
    public ArrayTaskListImpl(){}

    //Methods

    @Override
    public void add (Task task) throws IllegalArgumentException{
        /**
         * The method adds the given Task to a taskList array;
         * For this to work, it is necessary to introduce a Task object.
         * If we try to add and empty task, an Exception will be thrown.
         */
        if (task==null){
            throw new IllegalArgumentException("The task to add cannot be empty");
        }
        else {
            if(size==0){
                this.taskList = new Task[1];
                this.taskList[size]=task;
                this.size=1;
            }
            else {
                Task[] tempList = new Task[size+1];
                for (int i=0; i<size;i++){
                    tempList[i]=getTask(i);
                }
                tempList[size]=task;
                this.taskList=null;
                System.gc();
                this.taskList=tempList;
                this.size++;
            }
        }
    }

    @Override
    public boolean remove(Task task) {
        /**
         * The method removes the given Task from the taskList array; for this to work, the input
         * value needs to be a Task object. For the removing to be a success, the task must exist
         * within the array, then the return value will be true. If not, it will be false.
         */

        //For checking the existence of the input task in the Linked List
        boolean exist=false;
        int index=0;
        for (int i=0; i<size;i++){
            if(task==getTask(i)){
                exist=true;
                index=i;
            }
        }

        if(exist){
            Task[] newList = new Task[size-1];
            if (index==size-1){ //Last task of the list.
                for(int j=0;j<size-1;j++){
                    newList[j]=getTask(j);
                }
            }
            else if (index==0) { //First task of the list.
                for(int j=1;j<size;j++){
                    newList[j-1]=getTask(j);
                }
            }
            else { //The task is located in a middle position.
                for(int j=0;j<index;j++){//Tasks located before the task to remove
                    newList[j]=getTask(j);
                }
                for(int k=index+1;k<size;k++){//Tasks located after the task to remove.
                    newList[k-1]=getTask(k);
                }
            }
            success=true;
            this.taskList=null;
            System.gc();
            this.taskList=newList;
            this.size--;
        }
        else{
            success=false;
        }
        return success;
    }

    @Override
    public int size() {
        /**
         * The method returns the number of tasks included in the array.
         */
        return size;
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        /**
         * The method returns the task located in the input index, which must a positive integer
         * between 0 and the size of the array. If we try to access to an index out of this interval,
         * an Exception will be thrown.
         *
         * For getting the first task, the input must be 0. The last task is located in size-1.
         */
        if (index>=size || index<0){
            int finalIndex=size-1;
            throw new IllegalArgumentException("The index must be a number between 0 and" + " " +finalIndex);
        }
        else {
            return taskList[index];
        }
    }

    @Override
    public ArrayTaskList incoming (int from, int to) throws IllegalArgumentException{
        /**
         * This method returns an array with the tasks executed within the given interval.
         * The tasks included in the created array must be ACTIVE. As the input values refer
         * to time, they must be positive integers. If not, an exception will be thrown.
         */
        if (from<=0 || to<=0) {
            throw new IllegalArgumentException("Both inputs arguments must be positive integers.");
        }
        else{
            ArrayTaskList incomingTasks = new ArrayTaskListImpl();
            for(int i=0; i<size; i++) {
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
