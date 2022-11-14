package com.sakanal.LeetCode;

public class 两数之和 {
    public static int[] twoSum(int[] nums, int target) {
        int[] totals=new int[2];
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                int sum=nums[i]+nums[j];
                if(target==sum){
                    totals[0]=i;
                    totals[1]=j;
                    return totals;
                }
            }
        }
        return totals;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{2,7,11,15};
        int target=9;
        int[] totals=twoSum(nums,target);
        for (int total : totals) {
            System.out.println("total = " + total);
        }
    }
}
