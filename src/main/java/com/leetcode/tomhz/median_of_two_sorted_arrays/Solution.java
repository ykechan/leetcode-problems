package leetCode4_median_of_two_sorted_arrays;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
        int length = nums1.length + nums2.length;
		int a = 0,b = 0;
		int rs = 0;
		set.add(length / 2); 
		if(length % 2 == 0) {
			set.add(length / 2 - 1); 
		}
		while(!set.isEmpty()) {
//			int tmp;
//			if(a >= nums1.length) {
//				if(set.contains(a + b)) {
//					rs += nums2[b];
//					set.remove(a + b);
//				}
//				b++;
//			}
//			if(b >= nums2.length) {
//				if(set.contains(a + b)) {
//					rs += nums1[a];
//					set.remove(a + b);
//				}
//				a++;
//			}
			
			if((a < nums1.length ? nums1[a] : Integer.MAX_VALUE) > (b < nums2.length ? nums2[b] : Integer.MAX_VALUE)) {
				if(set.contains(a + b)) {
					rs += nums2[b];
					set.remove(a + b);
				}
				b++;
			}else {
				if(set.contains(a + b)) {
					rs += nums1[a];
					set.remove(a + b);
				}
				a++;
			}
		}
		return length % 2 == 0 ? rs / 2.0 : rs;
    }
	
//	public int binarySearch(int arr[], int l, int r, int x)
//    {
//        if (r >= l) {
//            int mid = l + (r - l) / 2;
// 
//            // If the element is present at the
//            // middle itself
//            if (arr[mid] == x)
//                return mid;
// 
//            // If element is smaller than mid, then
//            // it can only be present in left subarray
//            if (arr[mid] > x)
//                return binarySearch(arr, l, mid - 1, x);
// 
//            // Else the element can only be present
//            // in right subarray
//            return binarySearch(arr, mid + 1, r, x);
//        }
// 
//        // We reach here when element is not present
//        // in array
//        return -1;
//    }
}
