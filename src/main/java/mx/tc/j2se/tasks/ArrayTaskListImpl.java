package mx.tc.j2se.tasks;

public class ArrayTaskListImpl implements ArrayTaskList {
    //Attributes
    Task[] taskList;
    int taskNumber=0;
    int size;
    boolean success;

    //Constructor
    public ArrayTaskListImpl(){}

    //Methods

    @Override
    public void add (Task task){
        /**
         * The method adds the given Task to a taskList array;
         * For this to work, it is necessary to introduce a Task object.
         */
        if(taskNumber==0){
            this.taskList = new Task[1];
            this.taskList[taskNumber]=task;
            this.taskNumber=1;

        }
        else {
            Task[] tempList = new Task[this.taskNumber+1];
            for (int i=0; i<this.taskNumber;i++){
                tempList[i]=this.taskList[i];}
            tempList[this.taskNumber]=task;

            this.taskList=null;
            System.gc();
            this.taskList = tempList;
            this.taskNumber++;

        }
    }

    @Override
    public boolean remove(Task task) {
        /**
         * The method removes the given Task from the taskList array; for this to work, the input
         * value needs to be a Task object. For the removing to be a success, the task must exist
         * within the array, then the return value will be true. If not, it will be false.
         */

        for (int i=0; i<this.taskNumber; i++) {
            if (this.taskList[i] == task) {//The task exists within the list.
                Task[] newList = new Task[this.taskNumber-1];
                if (i==this.taskNumber-1){ //Last task of the list.
                    for(int j=0;j<this.taskNumber-1;j++){
                        newList[j]=this.taskList[j];
                    }
                }
                else if (i==0) { //First task of the list.
                    for(int j=1;j<this.taskNumber;j++){
                        newList[j-1]=this.taskList[j];
                    }
                }
                else { //The task is located in a middle position.
                    for(int j=0;j<i;j++){//Tasks located before the task to remove
                        newList[j]=this.taskList[j];
                    }
                    for(int k=i+1;k<size;k++){//Tasks located after the task to remove.
                        newList[k-1]=this.taskList[k];
                    }
                }
                success=true;
                this.taskList=null;
                System.gc();
                this.taskList=newList;
                this.taskNumber--;
                break;
            }

            else{
                success=false;
            }
        }
        return success;
    }

    @Override
    public int size() {
        /**
         * The method returns the number of tasks included in the array.
         */
        this.size=this.taskNumber;
        return size;
    }

    @Override
    public Task getTask(int index) {
        /**
         * The method returns the task located in the input index.
         * For getting the first task, the input must be 0.
         * The last task is located in size-1.
         */
        return this.taskList[index];
    }

    @Override
    public ArrayTaskList incoming (int from, int to){
        /**
         * This method returns an array with the tasks executed within the given interval.
         * The tasks included in the created array must be ACTIVE.
         * As the input values refer to time, they must be positive integers.
         */
        ArrayTaskList incomingTasks = new ArrayTaskListImpl();
        for (int i=0; i<this.taskNumber; i++) {
            Task currentTask=taskList[i];
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
