class Solution {
	/*
	* 移动0: 将所有0移动到数组的末尾
	*/
	// 方法一: 快慢指针，快指针先跑跑到一个非0元停下，慢指针跑到一个零元停下，如果快指针在前则两者交换
    public void moveZeroes(int[] nums) {
        int s = 0, f = 0;
        // 快指针先跑，跑到一个非0元停下；慢指针跑到第一个0元停下，快指针在前就交换元素
        int len = nums.length;
        while (f < len && s < len){
            if (nums[f] != 0){
                // 等待慢指针
                for (; s < len && nums[s] != 0; s++);
                if (f > s){
                    // 交换元素
                    int tmp = nums[s];
                    nums[s] = nums[f];
                    nums[f] = tmp;
                }
            }
            f++;
        }
    }

	11 1
	// 方法二: (更快更简洁)快慢指针操作，慢指针始终指向的是目前序列中0元素的最左边界。也就是慢指针向左的序列是无0序列，慢指针到快指针中间的序列是有0序列
	public void moveZeroes(int[] nums) { 
        int s = 0, f = 0;
        int len = nums.length;
        while (f < len){
            if (nums[f] != 0){
				// 快慢指针可能会指向同一位置，此时相当于没有进行交换
                swap(nums, s, f);
                s++;
            }
            f++;
        }

    }

    public void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

	
}
