class Solution {
    private Map<Integer, Integer> map = new HashMap<>();
    // 前k个高频元素 nlogn
    public int[] topKFrequent(int[] nums, int k) {
        // 哈希表记录每个元素出现的次数，然后对每个元素而言，针对次数建堆
        for (int val : nums){
            int times = map.getOrDefault(val, 0);
            map.put(val, times + 1);
        }

        int[] arr = map.keySet().stream().mapToInt(v -> v).toArray();
        
        // 自己建堆排序
        int n = arr.length, heapSize = n;
        
        for (int i = n - 1; i >= 0; --i){
            heapify(arr, i, heapSize);
        }

        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }

        return IntStream.of(arr).skip(n - k).limit(k).toArray();

    }
    
    public void swap(int[] nums, int l, int r){
        if (l == r)
            return ;
        nums[l] ^= nums[r];
        nums[r] ^= nums[l];
        nums[l] ^= nums[r];
    }

    public void heapInsert(int[] nums, int idx){
        // 堆末尾插入一个元素
        while (getVal(nums[idx]) > getVal(nums[(idx - 1) / 2])){
            // 交换元素，idx回升
            swap(nums, idx, (idx - 1)/2);
            idx = (idx - 1) / 2;
        }
    }

    public int getVal(int key){
        return map.get(key);
    }

    public void heapify(int[] nums, int idx, int heapSize){
        int left = 2 * idx + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && getVal(nums[left + 1]) > getVal(nums[left]) ? left + 1: left;

            largest = getVal(nums[idx]) > getVal(nums[largest]) ? idx : largest;
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
