def leftMax(arr):
    if len(arr) == 1:
        return int(arr[0])
    else:
        mid = len(arr) // 2
        left = arr[0: mid:]
        right = arr[mid: len(arr):]
        return max(leftMax(left), leftMax(right))
    
def rightMax(arr):
    if len(arr) == 1:
        return int(arr[0]) ** 2
    else:
        mid = len(arr) // 2
        left = arr[0: mid:]
        right = arr[mid: len(arr):]
        return max(rightMax(left), rightMax(right))
    
def pairMax(arr):
    if len(arr) == 1:
        return float('-inf')
    else:
        mid = len(arr) // 2
        left = arr[:mid:]
        right = arr[mid::]
        l_max = pairMax(left)
        r_max = pairMax(right)
        cross_max = leftMax(left) + rightMax(right)
        return max(l_max, r_max, cross_max)
    
arrLength = int(input())
numStringArr = input().split(" ")
print(pairMax(numStringArr))