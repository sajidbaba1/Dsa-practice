class Solution:
    def containsDuplicate(self, nums: list[int]) -> bool:
        """
        Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
        """
        hashset = set()
        for n in nums:
            if n in hashset:
                return True
            hashset.add(n)
        return False

if __name__ == '__main__':
    s = Solution()
    # Test case 1: Contains duplicates
    nums1 = [1, 2, 3, 1]
    print(f"Test case 1: {nums1}, Expected: True, Got: {s.containsDuplicate(nums1)}")

    # Test case 2: No duplicates
    nums2 = [1, 2, 3, 4]
    print(f"Test case 2: {nums2}, Expected: False, Got: {s.containsDuplicate(nums2)}")
