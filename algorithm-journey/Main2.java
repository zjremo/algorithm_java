public class Solution{
    // Method1: 归并排序变种: 小和问题
    public static int process(int[] arr, int l, int r) {
        if (l == r)
            return 0;
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int[] helper = new int[r - l + 1];

        int i = 0;
        int p1 = l, p2 = mid + 1;
        int res = 0;

        while (p1 <= mid && p2 <= r) {
            res += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            helper[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p2 <= r) {
            helper[i++] = arr[p2++];
        }

        while (p1 <= mid) {
            helper[i++] = arr[p1++];
        }

        for (i = 0; i < helper.length; ++i) {
            arr[l + i] = helper[i];
        }
        return res;
    }

    // Method2: 归并排序变种：逆序对问题
    public static int process_reverse(int[] arr, int l, int r){
        if (l == r)
            return 0;

        int mid = l + ((r - l) >> 2);

        return process_reverse(arr, l, mid) + process_reverse(arr, mid + 1, r) + merge_reverse(arr, l, mid, r);
    }

    public static int merge_reverse(int[] arr, int l, int mid, int r){
        int[] helper = new int[r - l + 1];

        int i = 0;
        int p1 = l, p2 = mid + 1;
        int res = 0;

        while (p1 <= mid && p2 <= r){
            res += arr[p1] > arr[p2] ? (mid - p1 + 1) : 0;
            helper[i++] = arr[p1] <= arr[p2]? arr[p1++] : arr[p2++];
        }

        while (p2 <= r){
            helper[i++] = arr[p2++];
        }

        while (p1<= mid){
            helper[i++] = arr[p1++];
        }

        for (i = 0; i < helper.length; ++i){
            arr[l + i] = helper[i];
        }

        return res;
    }

    // Method3: quicksort 3.0版本，利用荷兰国旗问题配合随机数
    public static void swap(int[] arr, int l, int r){
        if (l == r)
            return ;
        arr[l] ^= arr[r];
        arr[r] ^= arr[l];
        arr[l] ^= arr[r];
    }

    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2)
            return ;
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int l, int r){
        if (l < r){
            // 随机取一个位置的元素与最后一个位置进行交换
            swap(arr, (int) (Math.random() * (r - l + 1)) + l, r);
            int[] p = partition(arr, l, r); 
            quickSort(arr, l, p[0]);// < 区域
            quickSort(arr, p[1], r);// > 区域
        }
    }

    public static int[] partition(int[] arr, int l, int r){
        int less = l - 1, more = r; // 定义< 区域边界，> 区域边界

        while (l < more){ // 移动指针与>区域边界撞上就退出
            if (arr[l] < arr[r]){
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]){
                swap(arr, --more, l);
            } else{
                l++;
            }
        }
        swap(arr, more, r); // 将>区域的第一个和target交换

        // 返回此时<区域的最右边界和>区域的最左边界
        return new int[]{
            less, more + 1
        };
    }

    // Method4: 堆排序
    // 堆插入元素
    public static void heapInsert(int[] arr, int index){
        while (arr[index] > arr[(index - 1) /2]){
            swap(arr, index, (index - 1) /2);
            index = (index - 1) /2;
        }
    }

    // 将index以下的弄成大根堆
    public static void heapify(int[] arr, int index, int heapSize){
        int left = index * 2 + 1; // 左孩子
        while (left < heapSize){
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]?left + 1:left;
            largest = arr[index] > arr[largest]?index : largest;

            if (largest == index)
                break;
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }

    }

    // 堆排序
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2){
            return ;
        }

        // 建堆 此方法比较繁琐, 复杂度为O(NLogN)
        // for (int i = 0; i < arr.length; ++i){
        //     heapInsert(arr, i);
        // }

        // 自底向上逐渐变为堆，复杂度为O(N)
        for (int i = arr.length - 1; i >= 0; --i){
            heapify(arr, i, arr.length);
        }

        // 定义heapsize
        int heapSize = arr.length;
        while (heapSize > 0){
            // 交换，把最大的元素赶到末尾
            swap(arr, 0, --heapSize);
            // 剩余的堆化
            heapify(arr, 0, heapSize);
        }
    }

    // Method5: 基数排序，优化之后的。count数组中之前存放的是每个位中数字出现的次数，此时数字出现次数的前缀和
    // https://zhuanlan.zhihu.com/p/431814918
    public static void radixSort(int[] arr){
        if (arr == null || arr.length < 2){
            return ;
        }

        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    // 最大值的十进制是几位数
    public static int maxbits(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; ++i)
            max = Math.max(max, arr[i]);
        int res = 0;
        while (max != 0){
            max /= 10;
            ++res;
        }
        return res;
    }

    // 获取十进制某一位的数字
    // 比如1234567 获取第4位 首先将4位及以上的右移 -> 1234 然后%10就可以得到4了
    public static int getDigit(int x, int d){
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }


    public static void radixSort(int[] arr, int l, int r, int digit){
        final int radix = 10;

        int i = 0, j = 0;
        // 辅助桶
        int[] bucket = new int[r - l + 1];
        for (int d = 1; d <= digit ; ++d){
            int[] count = new int[radix];

            // 统计个数
            for (i = l; i <= r; ++i){
                j = getDigit(arr[i], d);
                count[j]++;
            }

            // 转为前缀和
            for (i = 1; i < radix; ++i){
                count[i] = count[i] + count[i - 1];
            }

            // 注意这里一定要从右往左，因为第一轮我们是从左往右进入的桶，根据前缀和的规律我们应该从右往左遍历
            for (i = r; i >= l; --i){
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }

            // 拷贝
            for (i = l, j = 0; i <= r; ++i, ++j){
                arr[i] = bucket[j];
            }

        }

    }


}
