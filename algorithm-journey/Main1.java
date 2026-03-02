public class Soluton{
    //  Method1 : 快速地交换两个数 使用位运算
    public static void swap(int[] arr, int i, int j){
        if (i == j)
            return;
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    // Method2 : 取出一个数最右边1的位置
    public static int getMostRightOne(int pos){
        return pos & (~pos + 1);
    }

    // Method3 : 对数器
    class Code03_InsertionSort{
        public static void insertionSort(int[] arr){
            if (arr == null && arr.length < 2){
                return ;
            }
            
            for (int i = 0; i < arr.length; ++i){
                for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; --j){
                    swap(arr, j, j + 1);
                }
            }
        }

        // 利用系统提供的方法来作为比较器，这个是一定正确的算法
        public static void comparator(int[] arr){
            Arrays.sort(arr);
        }

        // 生成指定长度的随机数组 for test
        public static int[] generateRandomArray(int maxSize, int maxValue){
            // Math.random() -> [0, 1)所有的小数，等概率返回一个
            // Math.random() * N -> [0, N)所有的小数，等概率返回一个
            // (int)Math.random() * N -> [0, N-1]所有的整数，等概率返回一个

            int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
            for (int i = 0; i < arr.length; ++i){
                arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            }
            return arr;
        }

        // deep copy, for test
        public static int[] copyArray(int[] arr){
            if (arr == null)
                return null;

            int[] res = new int[arr.length];
            for (int i = 0; i < arr.length; ++i)
                res[i] = arr[i];
            return res;
        }

        // 判定, for test
        public static boolean isEqual(int[] arr1, int[] arr2){
            return Arrays.equals(arr1, arr2);
        }

        // for test
        public static void printArray(int[] arr){
            if (arr == null){
                return;
            }

            for (int i = 0; i < arr.length; ++i){
                System.out.println(arr[i] + " ");
            }

            System.out.println();
        }

        // for test
        public static void main(String[] args) {
            int testTime = 500000;
            int maxSize = 100;
            int maxValue = 100;
            boolean succeed = true;
            for (int i = 0; i < testTime; ++i){
                int[] arr1 = generateRandomArray(maxSize, maxValue);
                int[] arr2 = copyArray(arr1);
                insertionSort(arr1);
                comparator(arr2);
                if (!isEqual(arr1, arr2)){
                    succeed = true;
                    break;
                }
            }
            System.out.println(succeed ? "Nice!" : "Fucking fucked");

            int[] arr = generateRandomArray(maxSize, maxValue);
            printArray(arr);
            insertionSort(arr);
            printArray(arr);
        }
        
    }

    // Method4 : 选择排序
    public static void selectionSort(int[] arr){
        if (arr == null || arr.length < 2)
            return ;

        for (int i = 0; i < arr.length - 1; ++i){
            int minIndex = i;
            for (int j = i + 1; j < arr.length; ++j){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    // Method5: 归并排序
    public static void process(int[] arr, int left, int right){
        if (left == right)
            return ;
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right){
        int[] helper = new int[right - left + 1];
        int i = 0; // 辅助数组index
        int p1 = left, p2 = mid + 1;
        while (p1 <= mid && p2 <= right){
            helper[i++] = arr[p1] <= arr[p2] ? arr[p1++]:arr[p2++];
        }

        // 完成剩余的合并
        while (p1 <= mid){
            helper[i++] = arr[p1++];
        }

        while (p2 <= right){
            helper[i++] = arr[p2++];
        }

        // copy
        for (i = 0; i < helper.length; ++i)
            arr[left + i] = helper[i];

    }



}
