inversionCounter = 0

def merge(part1, part2):
    global inversionCounter
    mergedList = []
    i, j = 0, 0
    while i < len(part1) and j < len(part2):
        if int(part1[i]) > int(part2[j]):
            mergedList.append(part2[j])
            inversionCounter += len(part1) - i
            j += 1
        else:
            mergedList.append(part1[i])
            i += 1

    while i < len(part1):
        mergedList.append(part1[i])
        i += 1
    while j < len(part2):
        mergedList.append(part2[j])
        j += 1
    return mergedList

def mergeSort(arr):
    if len(arr) <= 1:
        return arr
    else:
        mid = len(arr)//2
        a1 = mergeSort(arr[0: mid:])
        a2 = mergeSort(arr[mid: len(arr):])
        return merge(a1, a2)
    
arrLength = int(input())
numStringArr = input().split(" ")
sortedArray = mergeSort(numStringArr)
stringRepresentation = " ".join(sortedArray)
print(inversionCounter)
print(stringRepresentation)

