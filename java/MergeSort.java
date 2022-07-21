public class MergeSort {
    final int[] input;
    final int[] output;
    int inversion = 0;

    public MergeSort(int[] input) {
        this.input = input;
        this.output = new int[input.length];
    }

    public void merge(int start, int end) {
        if(end<=start) {
            return;
        }
        int middle = (start+end) >> 1;
        merge(start, middle);
        merge(middle+1, end);

        int p = start;      // a pointer on output array.
        int a = start;      // left array index
        int b = middle+1;   // right array index

        while(p<=end) {
            if(b<=end && (a>middle || input[a]>input[b])) {
                output[p++] = input[b++];
                inversion += b-p;
            } else {
                output[p++] = input[a++];
            }
        }

        System.out.println(String.format("input = [%s], output = [%s]",
                toString(input, start, end), toString(output, start, end)));

        for (int i = start; i <= end; ++i) {
            input[i] = output[i];
        }
    }

    private String toString(int[] arr, int start, int end) {
        String[] tmp = new String[end-start+1];
        for (int i=0; i<tmp.length; ++i) {
            tmp[i] = Integer.toString(arr[start+i]);
        }
        return String.join(",", tmp);
    }

    public int[] sort() {
        System.out.println(String.format("+input = [%s]", toString(input, 0, input.length-1)));
        merge(0, input.length-1);
        System.out.println("inversion="+inversion);
        return output;
    }
}
