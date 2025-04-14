def orderingBT(arr, left, right):
    if left > right:
        return []
    newArr = []
    mid = (left + right) // 2
    newArr = [arr[mid]]
    newArr += orderingBT(arr, left, mid - 1)
    newArr += orderingBT(arr, mid + 1, right)
    return newArr

num = int(input())
arr = input().split(" ")
newArr = " ".join(orderingBT(arr, 0, len(arr) - 1))
print(newArr)