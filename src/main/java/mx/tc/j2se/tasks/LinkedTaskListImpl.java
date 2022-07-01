package mx.tc.j2se.tasks;

public class LinkedTaskListImpl implements LinkedTaskList {
    //Attributes
    Node startingNode;
    int size=0;
    boolean success;

    //Constructor
    public LinkedTaskListImpl() {
        this.startingNode=null;
    }

    //Methods
    @Override
    public void add(Task task) throws IllegalArgumentException{
        /**
         * Adds non-null tasks to the linked list.
         */
        Node newNode=new Node(task);
        if (task==null){
            throw new IllegalArgumentException("You cannot add and empty Task.");
        }
        else {
            if(startingNode==null){ //The linked list is empty.
                this.startingNode=newNode;
            }
            else{
                Node movingNode=startingNode;
                while(movingNode.linkedTo!=null){
                    movingNode=movingNode.getLinkedTo();
                }
                movingNode.setLinkedTo(newNode);
            }
            this.size++;
        }


    }

    @Override
    public boolean remove(Task task) {
        /**
         * Removes the given task after checking the existence of it in the list.
         * "True" will be return if the process is a success.
         */
        //For checking the existence of the input task in the Linked List
        boolean exist=false;
        Node testingNode=startingNode;
        while (!exist && testingNode!=null){
            if(testingNode.getData()==task){
                exist=true;
            }
            else{
                testingNode=testingNode.getLinkedTo();
            }
        }

        if(exist){
            if (startingNode.getData()==task) {//removing the first task
                this.startingNode=startingNode.getLinkedTo();
            }
            else{ //removing any other element in the list
                Node movingNode=startingNode;
                Node futureNode=movingNode.linkedTo;
                while(futureNode.getData()!=task){
                    movingNode=movingNode.getLinkedTo();
                    futureNode=movingNode.linkedTo;
                }
                Node newNode=futureNode.getLinkedTo();
                movingNode.setLinkedTo(newNode);
            }
            this.success=true;
            this.size--;
        }
        else{
            this.success=false;
        }
        return this.success;
    }

    @Override
    public int size() {
        /**
         * Returns the number of elements in the list*/
        return this.size;
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        /**
         * The index must be a value located between 0 and the size-1 od the list.
         * 0 is used to get the first task if the list. Size-1 is used for the last one.
         */
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index does not exist.");
        }
        else {
            Node movingNode = startingNode;
            for (int i = 0; i < index; i++) {
                movingNode=movingNode.getLinkedTo();
            }
            return movingNode.getData();
        }
    }

    @Override
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {
        /**
         * Returns a linked list containing the tasks that are going to be executed within the given interval.
         * Both values must be positive integers, since it refers to time. The tasks must be active to be considered.
         */
        if (from<=0 || to<=0){
            throw new IllegalArgumentException("Both input arguments must be positive integers.");
        }
        else{
            LinkedTaskList incomingTasks=new LinkedTaskListImpl();
            for(int i=0;i<size;i++){
                Task currentTask=getTask(i);
                if(currentTask.isActive()){
                    int nextExecution=currentTask.nextTimeAfter(from);
                    if (currentTask.isRepeated()){
                        while (nextExecution>from && nextExecution<to) {
                            incomingTasks.add(currentTask);
                            nextExecution = currentTask.nextTimeAfter(nextExecution);
                        }
                    }
                    else{
                        if(nextExecution>from && nextExecution<to){
                            incomingTasks.add(currentTask);
                        }
                    }
                }
            }
            return incomingTasks;
        }
    }


    // ****** Class Node *******
    public static class Node{
        //Attributes
        Task data;
        Node linkedTo;

        //Constructor
        public Node(){
            this.linkedTo = null;
        }

        public Node(Task data){
            this.data = data;
            this.linkedTo = null;
        }

        public Node(Task data, Node linkedTo){
            this.data = data;
            this.linkedTo = linkedTo;
        }

        //Methods
        public void setData(Task data) {
            this.data = data;
        }

        public void setLinkedTo(Node linkedTo) {
            this.linkedTo = linkedTo;
        }

        public Task getData() {
            return data;
        }

        public Node getLinkedTo() {
            return linkedTo;
        }
    }

}
