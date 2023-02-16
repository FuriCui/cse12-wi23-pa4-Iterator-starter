import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;
public class MyLinkedList<E> extends AbstractList<E>{
    int size;
    Node head;
    Node tail;
    protected class Node{
        E data;
        Node next;
        Node prev;
        public Node(E element){
            this.data=element;
            this.next=null;
            this.prev=null;
        }
        public void setPrev(Node prev){
            this.prev=prev;		
        }
        public void setNext(Node next){
            this.next=next;
        }
        public void setElement(E element){
            this.data=element;
        }
        public Node getNext(){
            return this.next;
        }
        public Node getPrev(){
            return this.prev;
        } 
        public E getElement(){
            return this.data;
        }
    }
    public MyLinkedList(){
        this.head=new Node(null);
        this.tail=new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        this.size=0;
    }
    public int size(){
        return this.size;
    }
    public E get(int index){
        if(0>index||index>=size)
            throw new IndexOutOfBoundsException();
        else{
            Node x=head;
            for(int i=0;i<=index;i++)
                x=x.next;
            return x.getElement();
        }
    }
    public void add(int index,E data){
        if(data==null)
            throw new NullPointerException();
        else if(0>index||index>size)
            throw new IndexOutOfBoundsException();
        else{
            if(index==0){
                size++;
                Node x=new Node(data);
                x.setPrev(head);
                x.setNext(head.next);
                head.next.setPrev(x);
                head.setNext(x);
            }else if(index==size-1)
                add(data);
            else{
                size++;
                Node x=new Node(data),r=getNth(index);
                r.prev.setNext(x);
                x.setPrev(r.prev);
                x.setNext(r);
                r.setPrev(x);
            }
        }
    }
    public boolean add(E data){
        if(data==null)
            throw new NullPointerException();
        else{
            Node x=new Node(data);
            tail.prev.setNext(x);
            x.setPrev(tail.prev);
            x.setNext(tail);
            tail.setPrev(x);
            size++;
        }
        return true;
    }
    public E set(int index,E data){
        if(data==null)
            throw new NullPointerException();
        else if(0>index||index>=size)
            throw new IndexOutOfBoundsException();
        else{
            Node x=new Node(data),r=getNth(index);
            x.setPrev(r.prev);
            x.setNext(r.next);
            r.prev.setNext(x);
            r.next.setPrev(x);
            return r.getElement();
        }
    }
    public E remove(int index){
        if(0>index||index>=size)
            throw new IndexOutOfBoundsException();
        else{
            Node x=getNth(index);
            size--;
            x.prev.setNext(x.next);
            x.next.setPrev(x.prev);
            return x.getElement();
        }
    }
    public void clear(){
        head.setNext(tail);
        tail.setPrev(head);
        size=0;
    }
    public boolean isEmpty(){
        if(size>0)
            return false;
        else
            return true;
    }
    protected Node getNth(int index){
        if(0>index||index>=size)
            throw new IndexOutOfBoundsException();
        else{
            Node x=head;
            for(int i=0;i<=index;i++)
                x=x.next;
            return x;
        }
    }
	protected class MyListIterator implements ListIterator<E> {
		Node left;
		Node right;
		int idx;
		boolean forward;
		boolean canRemoveOrSet;
		public MyListIterator(){
            this.left=head;
            this.right=this.left.next;    
            this.idx=0;
            this.forward=true;
            this.canRemoveOrSet=false;
        }
		public boolean hasNext(){
            if(this.right!=tail){
                return true;
			}else{
				return false;
			}
        }
		public E next(){
			if(!hasNext()){
				throw new NoSuchElementException();
			}else{
				this.idx++;
				this.left=this.right;
				this.right=this.right.next;
				this.forward=true;
				this.canRemoveOrSet=true;
				return this.left.data;
			}
        }
        public boolean hasPrevious(){
            if(this.idx>0){
                return true;
			}else{
				return false;
			}
        }
		public E previous(){
			if(!hasPrevious()){
				throw new NoSuchElementException();
			}else{
				this.idx--;
				this.right=this.left;
				this.left=this.left.prev;
				this.forward=false;
				this.canRemoveOrSet=true;
				return this.right.data;
			}
        }
		public int nextIndex(){
			return this.idx;
		}
		public int previousIndex(){
			return this.idx-1;
		}
		public void add(E element){
            if(element==null)
                throw new NullPointerException();
            else{
                Node x=new Node(element);
                this.left.setNext(x);
                x.setPrev(this.left);
                x.setNext(this.right);
                this.right.setPrev(x);
				this.left=x;
                this.idx++;
				size++;
                this.canRemoveOrSet=false;
            }
        }
		public void set(E element){
            if(element==null)
                throw new NullPointerException();
            else if(this.canRemoveOrSet==false)
                throw new IllegalStateException();
            else if(forward){
				this.left.data=element;
            }else{
                this.right.data=element;
            }
        }
		public void remove(){
            if(this.canRemoveOrSet==false)
                throw new IllegalStateException();
            else if(forward){
                this.left.prev.setNext(this.right);
                this.right.setPrev(this.left.prev);
				this.left=this.left.prev;
				this.idx--;
				size--;
                this.canRemoveOrSet=false;
            }else{
                this.left.setNext(this.right.next);
                this.right.next.setPrev(this.left);
				this.right=this.right.next;
				size--;
                this.canRemoveOrSet=false;
            }
        }
	}
}