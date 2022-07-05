package mx.tc.j2se.tasks;

public class LinkedTaskListImpl extends AbstractTaskList {
    //Attributes
    Node startingNode;
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
            super.size++;
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
            super.size--;
        }
        else{
            this.success=false;
        }
        return this.success;
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
