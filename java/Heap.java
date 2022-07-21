public class Heap {
    int[] input;
    int[] heap;
    int size = 0;

    public Heap(int[] input) {
        this.input = input;
        this.heap = new int[input.length+1];
        for(int in: input) {
            add(in);
        }
        // Heap starts from index 1.
        System.out.println(String.format("heap=[%s]", toString(heap, 1, heap.length-1)));
    }

    private void add(int in) {
        heap[++size] = in;
        int target = size;

        int parent;
        while((parent=target>>1)>=0) {
            if(target==parent) {
                return;
            }
            if(heap[parent]>heap[target]) {
                // Swap and retarget.
                int tmp = heap[parent];
                heap[parent] = heap[target];
                heap[target] = tmp;
                target = parent;
            } else {
                return;
            }
        }
    }

    public int peek() {
        int peek = heap[1];
        heap[1] = heap[size--];

        swapDown(1);
        System.out.println(String.format("peek=%d heap=[%s]", peek, toString(heap, 1, size)));
        return peek;
    }

    public void swapDown(int node) {
        if(node==size) {
            return;
        }
        int left = node*2;
        if(left>size) {
            return;
        }

        int right = node*2+1;
        if(right>size) {
            // compare with left
            if(heap[node]>heap[left]) {
                int tmp = heap[node];
                heap[node] = heap[left];
                heap[left] = tmp;
                swapDown(left);
            }
        } else if(heap[left]>heap[right]) {
            if(heap[node]>heap[right]) {
                int tmp = heap[node];
                heap[node] = heap[right];
                heap[right] = tmp;
                swapDown(right);
            }
        } else {
            if(heap[node]>heap[left]) {
                int tmp = heap[node];
                heap[node] = heap[left];
                heap[left] = tmp;
                swapDown(left);
            }
        }
    }

    private String toString(int[] arr, int start, int end) {
        String[] tmp = new String[end-start+1];
        for (int i=0; i<tmp.length; ++i) {
            tmp[i] = Integer.toString(arr[start+i]);
        }
        return String.join(",", tmp);
    }
}
