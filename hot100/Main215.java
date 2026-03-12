class Solution {
    // 数组中第k个最大元素
    // 方法一: 快速排序
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        return nums[len - k];
    }
    
    // 快速排序
    public void quickSort(int[] nums, int l, int r){
        if (l < r){
            // 随机化
            int randIdx = l + (int) (Math.random()*(r - l + 1));
            swap(nums, randIdx, r);
            int[] p = partition(nums, l, r);
            quickSort(nums, l, p[0]);
            quickSort(nums, p[1], r);
        }
    }

    public void swap(int[] nums, int l, int r){
        if (l == r)
            return ;
        nums[l] ^= nums[r];
        nums[r] ^= nums[l];
        nums[l] ^= nums[r];
    }

    public int[] partition(int[] nums, int l, int r){
        int less = l - 1, more = r;
        while (l < more){
            if (nums[l] < nums[r]){
                swap(nums, ++less, l++);
            } else if (nums[l] > nums[r]){
                swap(nums, --more, l);
            } else{
                ++l;
            }
        }

        // more区域第一个和r交换
        swap(nums, more, r);
        return new int[]{
            less, more + 1
        };
    }


    // 方法二: 堆排序 
    public int findKthLargest2(int[] nums, int k) {
        // 堆化
        int n = nums.length, heapSize = n;
        for (int i = n - 1; i >= 0; --i){
            heapify(nums, i, heapSize);
        }

        // 排序
        while (heapSize > 0){
            swap(nums, 0, --heapSize);
            heapify(nums, 0, heapSize);
        }

        return nums[n - k];
    }

    public void heapInsert(int[] nums, int idx){
        // 堆末尾插入一个元素
        while (nums[idx] > nums[(idx - 1) / 2]){
            // 交换元素，idx回升
            swap(nums, idx, (idx - 1)/2);
            idx = (idx - 1) / 2;
        }
    }

    public void heapify(int[] nums, int idx, int heapSize){
        int left = 2 * idx + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && nums[left + 1] > nums[left]?left + 1: left;

            largest = nums[idx] > nums[largest] ? idx : largest;
            if (largest == idx){
                break;
            }
            // 还要向下找
            swap(nums, largest, idx);
            idx = largest;
            left = 2 * idx + 1;
        }
    }
}
