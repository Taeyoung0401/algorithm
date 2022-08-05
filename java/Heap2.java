import java.util.Arrays;

class Solution {
    public int solution(int[] scoville, int K) {
        
        Heap heap = new Heap(100);
        for(int s: scoville) {
            heap.add(s);
        }
        
        heap.print();
        
        int answer = 0;
        
        while(heap.heap[1]<K && heap.use>=2) {
            ++answer;
            heap.add(heap.peek()+heap.peek()*2);
            System.out.print("try "+answer+" :");
                
            heap.print();
        }
               
        
        return heap.heap[1]>=K?answer:-1;
    }
}

class Heap {
    int use = 0;
    int[] heap;
    
    public Heap(int max) {
        heap = new int[max+1];
    }
    
    public void add(int val) {
        heap[++use] = val;
        
        int cur = use;
        int parent;
        
        while((parent=cur>>1)>0 && heap[cur]<heap[parent]) {
            swapNode(parent, cur);
            cur = parent;
        }        
    }
    
    public int peek() {
        int ret = heap[1];
                
        heap[1] = heap[use--];
        swapDown(1);
        return ret;
    }
    
    private void swapDown(int cur) {
        int left = cur<<1;
        int right = left+1;
        
        if(left>use) {  // no child
            return;
        }
        
        if(right>use) { // left only
            if(heap[left]<heap[cur]) {
                swapNode(left, cur);
                swapDown(left);
            }
        } else if(heap[left]<heap[right]) {
            if(heap[left]<heap[cur]) {
                swapNode(left, cur);
                swapDown(left);
            }
        } else {
            if(heap[right]<heap[cur]) {
                swapNode(right, cur);
                swapDown(right);
            }
        }
    }
    
    private void swapNode(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    
    public void print() {
        for(int i=1; i<=use; ++i) {
            System.out.print(heap[i]+" ");
        }
        System.out.print("\n");
    }
}
